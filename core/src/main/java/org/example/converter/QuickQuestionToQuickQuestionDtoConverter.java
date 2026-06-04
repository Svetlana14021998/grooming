package org.example.converter;

import org.example.dto.QuickQuestionDto;
import org.example.model.QuickQuestion;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class QuickQuestionToQuickQuestionDtoConverter {

    public QuickQuestionDto convert(QuickQuestion question) {
        if (question == null) {
            return null;
        }
        Locale currentLocale = LocaleContextHolder.getLocale();
        QuickQuestionDto.QuickQuestionDtoBuilder builder = QuickQuestionDto.builder()
            .id(question.getId())
            .category(question.getCategory().toDto());
        return switch (currentLocale.getLanguage()) {
            case "ru" -> builder
                .answer(question.getRusAnswer())
                .question(question.getRusQuestion())
                .build();
            default -> builder
                .answer(question.getEngAnswer())
                .question(question.getEngQuestion())
                .build();
        };
    }
}
