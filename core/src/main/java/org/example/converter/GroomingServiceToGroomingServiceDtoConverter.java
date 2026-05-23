package org.example.converter;

import lombok.RequiredArgsConstructor;
import org.example.dto.AnimalTypeDto;
import org.example.dto.GroomingServiceDto;
import org.example.model.GroomingService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GroomingServiceToGroomingServiceDtoConverter {

    private final AnimalTypeToAnimalTypeDtoConverter animalTypeToAnimalTypeDtoConverter;

    @Value("${app.service-photo-path}")
    private String basePhotoPath;

    public GroomingServiceDto convert(GroomingService groomingService) {
        if (groomingService == null) {
            return null;
        }
        Locale currentLocale = LocaleContextHolder.getLocale();
        Set<AnimalTypeDto> animalTypes = groomingService.getAnimalTypes().stream()
            .map(animalTypeToAnimalTypeDtoConverter::converter)
            .collect(Collectors.toSet());

        GroomingServiceDto.GroomingServiceDtoBuilder builder = GroomingServiceDto.builder()
            .price(groomingService.getPrice())
            .duration(groomingService.getDuration())
            .animalTypes(animalTypes)
            .photoPath(basePhotoPath + "/" + groomingService.getPhotoName())
            .isPopular(groomingService.isPopular());

        return switch (currentLocale.getLanguage()) {
            case "ru" -> builder
                .name(groomingService.getNameRu())
                .description(groomingService.getDescriptionRu())
                .category(groomingService.getCategory().getNameRu())
                .build();
            default -> builder
                .name(groomingService.getNameEn())
                .description(groomingService.getDescriptionEn())
                .category(groomingService.getCategory().getNameEn())
                .build();
        };
    }
}
