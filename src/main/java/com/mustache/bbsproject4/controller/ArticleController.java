package com.mustache.bbsproject4.controller;

import com.mustache.bbsproject4.entity.Article;
import com.mustache.bbsproject4.dto.ArticleDto;
import com.mustache.bbsproject4.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/articles")
@Slf4j
public class ArticleController {

    private final ArticleRepository articleRepository;

    public ArticleController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    // new 화면보여주기
    @GetMapping("/new")
    public String createArticleForm() {
        return "new";
    }
    // 아이디 1개로 조회하기
    @GetMapping ("/{id}")
    public String selectId(@PathVariable Long id, Model model) {
        Optional<Article> optArticle = articleRepository.findById(id);
        if (!optArticle.isEmpty()) {
            model.addAttribute("article", optArticle.get());
            return "show";
        } else {
            return "error";
        }
    }
    // 글 추가해서 view에 저장하기 이게 add라고 봐야겠네
    @PostMapping("")
    public String createArticle(ArticleDto articleDto) {
        Article savedArticle = articleRepository.save(articleDto.toEntity());
        return String.format("redirect:/articles/%d", savedArticle.getId());
    }
    //리스트 기능 추가
    @GetMapping("")
    public String list(Model model) {
        List<Article> article = articleRepository.findAll();
        model.addAttribute("article", article);
        return "list";
    }
    // 수정하기
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        Optional<Article> optionalArticle = articleRepository.findById(id);
        if (!optionalArticle.isEmpty()) {
            model.addAttribute("article", optionalArticle.get());
            return "edit";
        }else {
            return "error";
        }
    }
    //수정값 입력하기
    @PostMapping("/{id}/update")
    public String update(@PathVariable Long id, ArticleDto articleDto, Model model) {
        Article editedArticle = articleRepository.save(articleDto.toEntity());
        model.addAttribute("article", editedArticle);
        return String.format("redirect:/articles/%d", editedArticle.getId());
    }
    // 삭제하기
    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id, Model model) {
        articleRepository.deleteById(id);
        model.addAttribute("article", articleRepository.findAll());
        return "list";
    }
}
