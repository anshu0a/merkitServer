package com.merkit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.merkit.entity.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {

}