package org.example.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.example.config.AppConfig;
import org.example.exception.EntityNotFoundException;
import org.example.exception.IncorrectRequestParamException;
import org.example.exception.SendMessagingException;
import org.example.exception.UnknownTypeException;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
@RequiredArgsConstructor
@Log4j2
public class BaseControllerAdvice {

    private final AppConfig appConfig;

    private final MessageSource messageSource;

    @ModelAttribute
    public void addGlobalAttributes(Model model) {
        model.addAttribute("email", appConfig.getEmail());
        model.addAttribute("phone", appConfig.getPhone());
        model.addAttribute("work_hours", appConfig.getWorkHours());
    }

    @ExceptionHandler(value = {UnknownTypeException.class, EntityNotFoundException.class})
    public String catchUnknownTypeException(RuntimeException e, Model model) {
        log.error("Error: {}", e.getMessage());
        model.addAttribute("errorCode", 404);
        return "error";
    }

    @ExceptionHandler(IncorrectRequestParamException.class)
    public String catchIncorrectRequestParamException(IncorrectRequestParamException e, Model model) {
        log.error("Incorrect request: {}", e.getMessage(),e);
        model.addAttribute("errorCode", 400);
        return "error";
    }

    @ExceptionHandler(SendMessagingException.class)
    public String catchSendMessagingException(SendMessagingException e, RedirectAttributes redirectAttributes) {
        log.error("Can`t send message: {}", e.getMessage());
        String message = messageSource.getMessage("send_error", null, LocaleContextHolder.getLocale());
        redirectAttributes.addFlashAttribute("errorMessage", message);
        return "redirect:/contacts";
    }
}
