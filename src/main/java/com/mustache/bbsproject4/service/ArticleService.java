package com.mustache.bbsproject4.service;

import com.mustache.bbsproject4.dto.ArticleDto;
import com.mustache.bbsproject4.entity.Article;
import com.mustache.bbsproject4.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    // ArticleDto 만드는 기능
    public ArticleDto getArticleById(Long id) {
        Optional<Article> optArticle = articleRepository.findById(id);
        ArticleDto articleDto = Article.of(optArticle.get());
        return articleDto;
    }
}
