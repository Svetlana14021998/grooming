package org.example.enums;

import lombok.Getter;
import org.example.exception.UnknownTypeException;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Getter
public enum GroomingServiceCategory {

    GROOMING("Груминг", "Grooming"),
    HEALTH("Здоровье и гигиена", "Health & Hygiene"),
    SPA("СПА", "SPA");

    private final String nameRu;

    private final String nameEn;

    GroomingServiceCategory(String nameRu, String nameEn) {
        this.nameRu = nameRu;
        this.nameEn = nameEn;
    }

    public static GroomingServiceCategory fromLocalizedName(String name) {
        Locale currentLocale = LocaleContextHolder.getLocale();
        return switch (currentLocale.getLanguage()) {
            case "ru" -> Arrays.stream(GroomingServiceCategory.values())
                .filter(x -> x.nameRu.equals(name))
                .findFirst()
                .orElseThrow(() -> new UnknownTypeException("Unknown category: " + name));
            default -> Arrays.stream(GroomingServiceCategory.values())
                .filter(x -> x.nameEn.equals(name))
                .findFirst()
                .orElseThrow(() -> new UnknownTypeException("Unknown category: " + name));
        };
    }

    public static List<String> geAllForLocale() {
        Locale currentLocale = LocaleContextHolder.getLocale();
        return switch (currentLocale.getLanguage()) {
            case "ru" -> Arrays.stream(GroomingServiceCategory.values())
                .map(GroomingServiceCategory::getNameRu)
                .toList();
            default -> Arrays.stream(GroomingServiceCategory.values())
                .map(GroomingServiceCategory::getNameEn)
                .toList();
        };
    }
}

