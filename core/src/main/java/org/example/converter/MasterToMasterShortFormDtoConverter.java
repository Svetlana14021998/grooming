package org.example.converter;

import org.example.dto.MasterShortFormDto;
import org.example.model.Master;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class MasterToMasterShortFormDtoConverter {

    public MasterShortFormDto convert(Master master) {
        if (master == null) {
            return null;
        }
        Locale currentLocale = LocaleContextHolder.getLocale();

        MasterShortFormDto.MasterShortFormDtoBuilder builder = MasterShortFormDto.builder()
            .id(master.getId());

        return switch (currentLocale.getLanguage()) {
            case "ru" -> builder
                .fullName(master.getRuFullName())
                .build();
            default -> builder
                .fullName(master.getEnFullName())
                .build();
        };
    }
}
