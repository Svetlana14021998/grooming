package org.example.enums;

import lombok.Getter;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;

@Getter
public enum QuestionCategory {

    ABOUT_DOGS("Вопросы о собаках", "Questions about dogs", "🐕"),
    ABOUT_CATS("Вопросы о кошках", "Questions about cats", "🐈"),
    PRICES_AND_SERVICES("Цены и услуги", "Prices and services", "💰"),
    HEALTH_AND_SECURITY("Здоровье и безопасность", "Health and security", "🩺"),
    RECORD_AND_TIME("Запись и время", "Record and time", "📅"),
    SALON_RULES("Правила салона", "Salon rules", "📋"),
    VACCINATION_REQUIREMENTS("Требования к прививкам", "Vaccination requirements", "💉"),
    PREPARATION_FOR_VISIT("Подготовка к визиту", "Preparation for visit", "✅"),
    GROOMING_PROCESS("Процесс груминга", "Grooming process", "✂️"),
    AFTER_GROOMING_CARE("Уход после груминга", "After grooming care", "🛁"),
    PUPPY_AND_KITTEN_FIRST_VISIT("Первый визит щенков и котят", "Puppy and kitten first visit", "🐾");

    private final String rusName;
    private final String engName;
    private final String icon;

    QuestionCategory(String rusName, String engName, String icon) {
        this.rusName = rusName;
        this.engName = engName;
        this.icon = icon;
    }

    public String getLocalizedName() {
        Locale locale = LocaleContextHolder.getLocale();
        return getLocalizedName(locale);
    }

    public String getLocalizedName(Locale locale) {
        if (locale.getLanguage().equals("ru")) {
            return rusName;
        }
        return engName;
    }

    public CategoryDto toDto() {
        return new CategoryDto(name(), getLocalizedName(), icon);
    }

    public record CategoryDto(String code, String name, String icon) {
    }
}

