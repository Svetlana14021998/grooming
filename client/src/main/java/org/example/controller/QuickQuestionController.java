package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.QuickQuestionDto;
import org.example.enums.QuestionCategory;
import org.example.service.QuickQuestionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class QuickQuestionController {

    private final QuickQuestionService service;

    @GetMapping("/faq")
    public String getPage(Model model) {

        Map<QuestionCategory.CategoryDto, List<QuickQuestionDto>> questionsByCategory =
            service.findAll().stream()
                .collect(Collectors.groupingBy(QuickQuestionDto::getCategory));

        model.addAttribute("questionsByCategory", questionsByCategory);
        return "faq";
    }
}
