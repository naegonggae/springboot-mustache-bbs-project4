package com.mustache.bbsproject4.repository;

import com.mustache.bbsproject4.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
