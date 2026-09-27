package com.merkit.service.impl;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.merkit.dto.req.ArticleRequest;
import com.merkit.dto.res.ArticleCreateResponse;
import com.merkit.dto.res.ArticleResponse;
import com.merkit.entity.Article;
import com.merkit.entity.User;
import com.merkit.enums.ArticleResponseStatus;
import com.merkit.exception.UserNotFoundException;
import com.merkit.repo.ArticleRepository;
import com.merkit.repo.UserRepository;
import com.merkit.service.AIChatService;
import com.merkit.service.ArticleService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

	private final ArticleRepository articleRepo;
    private final UserRepository userRepository;
    private final CloudinaryServiceImpl cloudinaryService;
    private final AIChatService aiService;



	@Override
    public ArticleCreateResponse save( ArticleRequest art, Authentication authentication) {
		if (art == null) throw new RuntimeException("Article request is required. It is null");
    	String artx = art.getArticle();
    	String titx = art.getArticle();
        if (authentication == null)   throw new RuntimeException("User is not authenticated");
        if (titx == null || titx.isBlank()) throw new RuntimeException("Article title is required");
        if (artx == null || artx.isBlank()) throw new RuntimeException("Article content is required");
        
		String result = aiService.getAnswer("""
        		Note: {reply only in String}
        		check:{only check content not related to  sexual, abusable, and voilance  }
        		Give: {["BOTH_OK" if both article and title is acceptable],
        			  ["ARTICLE_OK" if only article is acceptable],
        			  ["TITLE_OK" if only title is acceptable],
        			  ["NOT_OK" if both article and title is not acceptable]}""" +
        			  "Article: "+ artx +
        			  "Title: "+ titx);
		System.out.println("------ checking Article: "+result);
		if (result.equals("NOT_OK")) 
			return ArticleCreateResponse.builder()
			.status(ArticleResponseStatus.NOT_OK)
			.message("The article could not be published because both the title and content contain unacceptable material.").build();
	
		else if (result.equals("TITLE_OK")) 
			return ArticleCreateResponse.builder()
			.status(ArticleResponseStatus.TITLE_OK)
			.message("The article could not be published because the title contains inappropriate or unacceptable content.").build();

		else if (result.equals("ARTICLE_OK")) 
			return ArticleCreateResponse.builder()
			.status(ArticleResponseStatus.ARTICLE_OK)
			.message("The article could not be published because the content contains inappropriate or unacceptable material.").build();
        
        if (art.getTag() == null || art.getTag().isBlank())  throw new RuntimeException("Article hashtag is required");
         
        String username = authentication.getName();
        User user = userRepository.findByUsername(username).orElseThrow(() ->new UserNotFoundException("User not found with username: " + username) );
        List<String[]> imageUrls = new ArrayList<>();

        if (art.getImages() != null && !art.getImages().isEmpty()) {
        	
            for (MultipartFile image : art.getImages()) {
                if (image == null || image.isEmpty()) {  continue;}
                try {
                    String[] url = cloudinaryService.uploadImage(image,"merkit/article");
                    imageUrls.add(new String[]{url[0],url[1]});
                } catch (IOException e) {
                    throw new RuntimeException("Failed to upload image in article",e);
                }
            }
        }

        LocalDateTime now = LocalDateTime.now();

        boolean published;
        LocalDateTime publishedAt;
        String msg;

        if (art.isPublish()) {
            published = true;
            publishedAt = now;
            msg = "Your article has been successfully published and is now available to readers.";

        } else {
            if (art.getPublishTime() == null) throw new RuntimeException("Publish date-time is required for scheduled article");
            if (art.getPublishTime().isBefore(now)) throw new RuntimeException("Publish date-time cannot be in the past");
            published = false;
            publishedAt = art.getPublishTime();
            msg = "Your article has been successfully scheduled and will be published automatically at the selected date and time.";
        }

        Article article = Article.builder()
                .title(art.getTitle().trim())
                .article(art.getArticle())
                .tag(art.getTag().trim())
                .images(imageUrls)
                .user(user)
                .views(new ArrayList<>())
                .likes(new ArrayList<>())
                .published(published)
                .update(false)
                .deleted(false)
                .createdAt(now)
                .publishedAt(publishedAt)
                .build();

        articleRepo.save(article);
        return ArticleCreateResponse.builder()
    			.status(ArticleResponseStatus.BOTH_OK)
    			.message(msg).build();

    }

	@Override
	public Page<ArticleResponse> getAllArticlePage(int page, int size) {
		
		Page<Article> rawArticle = articleRepo.findAll( 
					PageRequest.of(page, size, 
			    	Sort.by((String)"createdAt").descending())
		);
		
		Page<ArticleResponse> finalArticle = rawArticle.map(
				one -> 
				ArticleResponse.builder()
				//---- article ----
				.id(one.getId())
				.article(one.getArticle())
				.title(one.getTitle())
				//----- extra ------
				.date(one.getCreatedAt())
				.imgs(one.getImages())
				.hashtag(one.getTag())
				
				.comments( one.getComments().size())
				.likes(one.getLikes().size())
				.views(one.getViews().size())
				//----- user ---------
				.username(one.getUser().getUsername())
				.name(one.getUser().getName())
				.userimg(one.getUser().getProfilepic())
				
				.build()
	   
	  );
		 
		 return finalArticle ;
	}
}