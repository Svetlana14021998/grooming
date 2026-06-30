package org.example.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.config.AppConfig;
import org.example.dto.UsefulArticleDto;
import org.example.model.AppUser;
import org.example.service.UsefulArticleService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class UsefulArticleController {

    private final UsefulArticleService usefulArticleService;

    private final AppConfig config;

    @PostMapping("/article")
    public String saveArticle(@Valid @ModelAttribute("article") UsefulArticleDto articleDto,
        BindingResult bindingResult,
        @RequestParam(value = "coverImage", required = false) MultipartFile coverImage,
        Authentication authentication,
        RedirectAttributes redirectAttributes,
        Model model) {

        boolean isCoverageIsNullOrEmpty = coverImage == null || coverImage.isEmpty();
        if (isCoverageIsNullOrEmpty) {
            bindingResult.addError(new FieldError("article", "coverImage",
                "Пожалуйста, загрузите обложку статьи"));
        }
        if (!isCoverageIsNullOrEmpty && coverImage.getSize() > config.getMaxFileSize().toBytes()) {
            bindingResult.addError(new FieldError("article", "coverImage",
                "Размер файла не должен превышать " + config.getMaxFileSize().toKilobytes() + " килобайт"));
        }

        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();

            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }

            redirectAttributes.addFlashAttribute("errors", errors);
            redirectAttributes.addFlashAttribute("article", articleDto);
            redirectAttributes.addFlashAttribute("errorMessage", "Пожалуйста, исправьте ошибки в форме");

            return "redirect:/article/create";
        }
        AppUser currentUser = (AppUser) authentication.getPrincipal();

        usefulArticleService.save(articleDto, currentUser.getMaster().getId(), coverImage);
        redirectAttributes.addFlashAttribute("successMessage",
            "✅ Статья \"" + articleDto.getTitle() + "\" успешно создана!");

        return "redirect:/article";
    }
}
