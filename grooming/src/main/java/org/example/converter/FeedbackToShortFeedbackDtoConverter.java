package org.example.converter;

import org.example.dto.FeedbackShortDto;
import org.example.model.Feedback;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class FeedbackToShortFeedbackDtoConverter {

    public FeedbackShortDto convert(Feedback feedback) {
        if (feedback == null) {
            return null;
        }
        FeedbackShortDto.FeedbackShortDtoBuilder builder = FeedbackShortDto.builder()
            .id(feedback.getId())
            .visitDate(feedback.getVisitDate())
            .clientName(feedback.getClientName())
            .review(feedback.getReview())
            .score(feedback.getScore())
            .masterId(feedback.getMaster().getId());
        Locale currentLocale = LocaleContextHolder.getLocale();
        return switch (currentLocale.getLanguage()) {
            case "ru" -> builder
                .masterName(feedback.getMaster().getRuFullName())
                .build();
            default -> builder
                .masterName(feedback.getMaster().getEnFullName())
                .build();
        };
    }
}
