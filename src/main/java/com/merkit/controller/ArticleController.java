package com.merkit.controller;


import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.merkit.dto.req.ArticleRequest;
import com.merkit.dto.res.ArticleCreateResponse;
import com.merkit.dto.res.ArticleResponse;
import com.merkit.service.ArticleService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/article")
@Validated
public class ArticleController {

    private final ArticleService articlesvs;

    @PostMapping
    public ResponseEntity<ArticleCreateResponse> postArticle( @Valid @ModelAttribute ArticleRequest req, Authentication auth) {
    	ArticleCreateResponse res = articlesvs.save(req, auth);
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }
    
    @GetMapping("/all")
    public ResponseEntity<Page<ArticleResponse>> postArticle(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
    	
    	
        return ResponseEntity.ok(articlesvs.getAllArticlePage(page, size));
        
        
        
        
    }
}