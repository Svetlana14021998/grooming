package org.example.converter;

import org.example.dto.MasterDto;
import org.example.model.Master;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class MasterToMasterDtoConverter {

    @Value("${app.photo-path}")
    private String basePhotoPath;

    public MasterDto convert(Master master, Double rating, Long reviewScore) {
        if (master == null) {
            return null;
        }
        Locale currentLocale = LocaleContextHolder.getLocale();

        MasterDto.MasterDtoBuilder builder = MasterDto.builder()
            .id(master.getId())
            .pathToPhoto(basePhotoPath + "/" + master.getPhotoName())
            .rating(rating)
            .reviewsCount(reviewScore);
        return switch (currentLocale.getLanguage()) {
            case "ru" -> builder
                .specialisation(master.getRuSpecialisation())
                .experience(master.getRuExperience())
                .fullName(master.getRuFullName())
                .build();
            default -> builder
                .specialisation(master.getEnSpecialisation())
                .experience(master.getEnExperience())
                .fullName(master.getEnFullName())
                .build();
        };
    }
}
