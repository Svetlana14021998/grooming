package org.example.model.enums;

import lombok.Getter;
import org.example.exception.UnknowTypeException;

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

    public static GroomingServiceCategory fromLocalizedName(String name, Locale locale) {
        return switch (locale.getLanguage()) {
            case "ru" -> Arrays.stream(GroomingServiceCategory.values())
                .filter(x -> x.nameRu.equals(name))
                .findFirst()
                .orElseThrow(() -> new UnknowTypeException("Unknown category: " + name));
            default -> Arrays.stream(GroomingServiceCategory.values())
                .filter(x -> x.nameEn.equals(name))
                .findFirst()
                .orElseThrow(() -> new UnknowTypeException("Unknown category: " + name));
        };
    }

    public static List<String> geAllForLocale(Locale locale) {
        return switch (locale.getLanguage()) {
            case "ru" -> Arrays.stream(GroomingServiceCategory.values())
                .map(GroomingServiceCategory::getNameRu)
                .toList();
            default -> Arrays.stream(GroomingServiceCategory.values())
                .map(GroomingServiceCategory::getNameEn)
                .toList();
        };
    }
}

