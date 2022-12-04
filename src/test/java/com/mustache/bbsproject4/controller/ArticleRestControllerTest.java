package com.mustache.bbsproject4.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.mustache.bbsproject4.dto.ArticleAddRequest;
import com.mustache.bbsproject4.dto.ArticleAddResponse;
import com.mustache.bbsproject4.dto.ArticleDto;
import com.mustache.bbsproject4.service.ArticleService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@WebMvcTest(ArticleRestController.class)
class ArticleRestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ArticleService articleService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("해당 id의 글이 조회가 잘 되는지")
    void findSingle() throws Exception {
        Long id = 1l;

        given(articleService.getArticleById(id))
                .willReturn(new ArticleDto(1l, "첫 번째 글.", "내용입니다"));

        mockMvc.perform(get("/api/v1/articles/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.title").value("첫 번째 글."))
                .andExpect(jsonPath("$.content").value("내용입니다"))
                .andDo(print());

        verify(articleService).getArticleById(id);
    }

    @Test
    @DisplayName("글 등록이 잘 되는지")
    void add() throws Exception {

        ArticleAddRequest dto = new ArticleAddRequest("제목입니다", "내용입니다");

        // any()를 사용해서 어떤 것이 들어가도 뒤의 것을 리턴하게 가짜객체를 만들어준다
        given(articleService.add(any())).willReturn(new ArticleAddResponse(1l, dto.getTitle(), dto.getContent()));

        mockMvc.perform(post("/api/v1/articles")
                    .contentType(MediaType.APPLICATION_JSON) // json형태로 변경해서 위의 url에 연결해주는 역할
                    .content(objectMapper.writeValueAsBytes(dto)) // json형태로 변경해서 위의 url에 연결해주는 역할
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.title").exists())
                .andExpect(jsonPath("$.title").value("제목입니다"))
                .andExpect(jsonPath("$.content").exists())
                .andDo(print());

        //verify(articleService).add(dto);
    }
}