package com.merkit.service;

import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;

import com.merkit.dto.req.ArticleRequest;
import com.merkit.dto.res.ArticleCreateResponse;
import com.merkit.dto.res.ArticleResponse;

public interface ArticleService {
	ArticleCreateResponse save(ArticleRequest art, Authentication authentication);
	Page<ArticleResponse> getAllArticlePage(int page , int size);
}
