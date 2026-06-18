package org.example.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.example.config.AppConfig;
import org.example.exception.CantSaveFileException;
import org.example.exception.EntityNotFoundException;
import org.example.exception.IncorrectPasswordException;
import org.example.exception.SendMessagingException;
import org.example.exception.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@RequiredArgsConstructor
@Log4j2
public class BaseControllerAdvice {

    private final AppConfig appConfig;

    @ModelAttribute
    public void addGlobalAttributes(Model model) {
        model.addAttribute("email", appConfig.getEmail());
        model.addAttribute("phone", appConfig.getPhone());
        model.addAttribute("work_hours", appConfig.getWorkHours());
    }

    @ExceptionHandler(CantSaveFileException.class)
    public ResponseEntity<?> catchCantSaveFileException(CantSaveFileException e) {
        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body("{\"error\": \"Не удалось сохранить файл\"}");
    }

    @ExceptionHandler(IncorrectPasswordException.class)
    public ResponseEntity<?> catchPasswordException(IncorrectPasswordException e) {
        Map<String, String> response = new HashMap<>();
        response.put("message", e.getMessage());
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> catchEntityNotFoundException(EntityNotFoundException e) {
        return ResponseEntity
            .badRequest()
            .body(e.getMessage());
    }

    @ExceptionHandler(SendMessagingException.class)
    public ResponseEntity<?> catchSendMessagingException(SendMessagingException e) {
        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body("Не удалось отправить письмо с паролем. Повторите попытку позднее. В случае необходимости свяжитесь с поддержкой");
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<?> catchValidationException(ValidationException e) {
        return ResponseEntity
            .badRequest()
            .body(e.getErrors());
    }
}
