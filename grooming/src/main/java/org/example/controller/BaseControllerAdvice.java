package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.config.AppConfig;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
@RequiredArgsConstructor
public class BaseControllerAdvice {

    private final AppConfig appConfig;

    @ModelAttribute
    public void addGlobalAttributes(Model model) {
        model.addAttribute("email", appConfig.getEmail());
        model.addAttribute("phone", appConfig.getPhone());
        model.addAttribute("work_hours", appConfig.getWorkHours());
    }
}
