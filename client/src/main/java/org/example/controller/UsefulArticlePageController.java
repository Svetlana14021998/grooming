package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.MasterDto;
import org.example.dto.UsefulArticleDto;
import org.example.enums.ArticleType;
import org.example.enums.GroomingServiceCategory;
import org.example.service.MasterService;
import org.example.service.UsefulArticleService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UsefulArticlePageController {

    private final UsefulArticleService articleService;

    private final MasterService masterService;

    @GetMapping("/article")
    public String getArticlesPage(@RequestParam(name = "masterId", required = false) Long masterId,
        @RequestParam(name = "sort", required = false) String sort,
        @RequestParam(name = "type", required = false) String type,
        @RequestParam(name = "title", required = false) String title,
        @RequestParam(name = "page", required = false, defaultValue = "0") Integer pageNumber,
        @RequestParam(name = "size", required = false, defaultValue = "5") Integer pageSize,
        Model model) {
        Page<UsefulArticleDto> articles = articleService.findAll(masterId, sort, type, title, pageNumber, pageSize);
        List<MasterDto> masters = masterService.findAll();
        List<String> allType = ArticleType.geAllForLocale();
        model.addAttribute("articles", articles.getContent());
        model.addAttribute("types",allType);
        model.addAttribute("masters",masters);
        model.addAttribute("currentPage", articles.getNumber());
        model.addAttribute("totalPages", articles.getTotalPages());
        model.addAttribute("totalItems", articles.getTotalElements());

        return "articles";
    }

    @GetMapping("/article/{id}")
    public String getArticlePage(@PathVariable Long id, Model model) {
        UsefulArticleDto article = articleService.findById(id);
        model.addAttribute("article", article);
        return "article-details";
    }
}
