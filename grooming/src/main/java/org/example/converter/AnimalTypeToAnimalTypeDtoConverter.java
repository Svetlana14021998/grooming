package org.example.converter;

import org.example.dto.AnimalTypeDto;
import org.example.model.AnimalType;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class AnimalTypeToAnimalTypeDtoConverter {

    public AnimalTypeDto converter(AnimalType animalType) {
        if (animalType == null) {
            return null;
        }
        Locale currentLocale = LocaleContextHolder.getLocale();
        AnimalTypeDto.AnimalTypeDtoBuilder builder = AnimalTypeDto.builder()
            .id(animalType.getId())
            .icon(animalType.getIcon());

        return switch (currentLocale.getLanguage()) {
            case "ru" -> builder
                .name(animalType.getNameRu())
                .build();
            default -> builder
                .name(animalType.getNameEn())
                .build();
        };
    }
}
