package com.merkit.service.impl;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.merkit.dto.req.ArticleRequest;
import com.merkit.dto.res.ApiResponse;
import com.merkit.entity.Article;
import com.merkit.entity.User;
import com.merkit.exception.UserNotFoundException;
import com.merkit.repo.ArticleRepository;
import com.merkit.repo.UserRepository;
import com.merkit.service.ArticleService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepo;
    private final UserRepository userRepository;
    private final CloudinaryServiceImpl cloudinaryService;

    @Override
    public ApiResponse save(
            ArticleRequest art,
            Authentication authentication) {

        if (authentication == null) {
            throw new RuntimeException("User is not authenticated");
        }

        if (art == null) {
            throw new RuntimeException("Article request cannot be null");
        }

        if (art.getTitle() == null || art.getTitle().isBlank()) {
            throw new RuntimeException("Article title is required");
        }

        if (art.getArticle() == null || art.getArticle().isBlank()) {
            throw new RuntimeException("Article content is required");
        }

        if (art.getTag() == null || art.getTag().isBlank()) {
            throw new RuntimeException("Article hashtag is required");
        }

        String username = authentication.getName();

        User user = userRepository
                .findByUsername(username)
                .orElseThrow(() ->
                        new UserNotFoundException(
                                "User not found with username: " + username
                        )
                );

        Map<String, String> imageUrls = new HashMap<>();

        if (art.getImages() != null && !art.getImages().isEmpty()) {

            for (MultipartFile image : art.getImages()) {

                if (image == null || image.isEmpty()) {
                    continue;
                }

                try {

                    String[] url =
                            cloudinaryService.uploadImage(
                                    image,
                                    "merkit/article"
                            );

                    imageUrls.put(
                            url[0],
                            url[1]
                    );

                } catch (IOException e) {

                    throw new RuntimeException(
                            "Failed to upload image in article",
                            e
                    );
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
            msg = "Article is published.";

        } else {

            if (art.getPublishTime() == null) {
                throw new RuntimeException(
                        "Publish date-time is required for scheduled article"
                );
            }

            if (art.getPublishTime().isBefore(now)) {
                throw new RuntimeException(
                        "Publish date-time cannot be in the past"
                );
            }

            published = false;
            publishedAt = art.getPublishTime();
            msg = "Article is scheduled for publishing.";
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

        return ApiResponse.builder()
                .success(true)
                .message(msg)
                .statusCode(201)
                .build();
    }

    @Override
    public List<Article> getAllArticle(
            Authentication authentication) {

        return articleRepo.findAll();
    }
}