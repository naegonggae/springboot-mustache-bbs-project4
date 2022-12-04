package com.mustache.bbsproject4.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
// 공유하고 싶지 않은것들은 response에 만들지 않는다.
@Getter
@AllArgsConstructor
public class ArticleAddResponse {
    private Long id;
    private String title;
    private String content;
}
