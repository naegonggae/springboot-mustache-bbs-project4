package com.mustache.bbsproject4.dto;

import com.mustache.bbsproject4.entity.Article;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@AllArgsConstructor
@EqualsAndHashCode // 오브젝트의 hash로 같은지 비교 하는 것이 아닌 값으로 비교하게 하는 어노테이션
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
