package org.example.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.dto.FeedbackDto;
import org.example.dto.FeedbackShortDto;
import org.example.service.FeedbackService;
import org.example.service.MasterService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class FeedbackPageController {

    private final FeedbackService feedbackService;

    private final MasterService masterService;

    @PostMapping("/feedback/save")
    public String saveFeedback(@Valid @ModelAttribute FeedbackDto feedbackDto,
        BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("feedbackDto", feedbackDto);
            model.addAttribute("masters", masterService.findAll());
            model.addAttribute("scrollToForm", true);
            return "add-feedback";
        }
        feedbackService.save(feedbackDto);
        redirectAttributes.addFlashAttribute("successMessage", "Ваш отзыв успешно отправлен! Спасибо за обратную связь!");
        return "redirect:/feedback";
    }

    @GetMapping("/feedback")
    public String getFeedback(@RequestParam(name = "masterId", required = false) Long masterId,
        @RequestParam(name = "score", required = false) Integer score,
        @RequestParam(name = "sort", required = false) String sort,
        @RequestParam(name = "page", required = false, defaultValue = "0") Integer pageNumber,
        @RequestParam(name = "size", required = false, defaultValue = "10") Integer pageSize,
        Model model) {

        Page<FeedbackShortDto> feedback = feedbackService.findAll(masterId, score, sort, pageNumber, pageSize);

        model.addAttribute("fb", feedback.getContent());
        model.addAttribute("currentPage", feedback.getNumber());
        model.addAttribute("totalPages", feedback.getTotalPages());
        model.addAttribute("totalItems", feedback.getTotalElements());
        model.addAttribute("masters", masterService.findAll());

        return "feedback";
    }

    @GetMapping("/feedback/add")
    public String getFeedbackAddPage(Model model) {
        FeedbackDto feedbackDto = new FeedbackDto();

        model.addAttribute("feedbackDto", feedbackDto);
        model.addAttribute("masters", masterService.findAll());
        return "add-feedback";
    }
}
