package com.mustache.bbsproject4.dto;

import com.mustache.bbsproject4.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ArticleAddRequest {
    private String title;
    private String content;

    public Article toEntity() {
        Article article = Article.builder()
                .title(this.title)
                .content(this.content)
                .build();
        return article;
    }
}
