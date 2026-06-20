package org.example.enums;

import lombok.Getter;
import org.example.exception.UnknownTypeException;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Getter
public enum ArticleType {

    EDUCATIONAL_PROGRAM("Экспертный ликбез", "Education Program"),
    HOME_CARE("Домашний уход. Инструкции", "Home care"),
    BREADS_AND_STYLES("Породы и стили", "Breads and styles"),
    LIFESTYLE_ADN_CARE("Лайфстайл и забота", "Lifestyle and care");

    private final String nameRu;
    private final String nameEn;

    ArticleType(String nameRu, String nameEn) {
        this.nameRu = nameRu;
        this.nameEn = nameEn;
    }

    public static ArticleType fromLocalizedName(String name) {
        Locale currentLocale = LocaleContextHolder.getLocale();
        return switch (currentLocale.getLanguage()) {
            case "ru" -> Arrays.stream(ArticleType.values())
                .filter(x -> x.nameRu.equals(name))
                .findFirst()
                .orElseThrow(() -> new UnknownTypeException("Unknown category: " + name));
            default -> Arrays.stream(ArticleType.values())
                .filter(x -> x.nameEn.equals(name))
                .findFirst()
                .orElseThrow(() -> new UnknownTypeException("Unknown category: " + name));
        };
    }

    public static List<String> geAllForLocale() {
        Locale currentLocale = LocaleContextHolder.getLocale();
        return switch (currentLocale.getLanguage()) {
            case "ru" -> Arrays.stream(ArticleType.values())
                .map(ArticleType::getNameRu)
                .toList();
            default -> Arrays.stream(ArticleType.values())
                .map(ArticleType::getNameEn)
                .toList();
        };
    }
}
