package org.example.converter;

import lombok.RequiredArgsConstructor;
import org.example.dto.UsefulArticleDto;
import org.example.enums.ArticleType;
import org.example.exception.EntityNotFoundException;
import org.example.exception.IncorrectDataException;
import org.example.model.Master;
import org.example.model.UsefulArticle;
import org.example.repository.MasterRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@RequiredArgsConstructor
public class UsefulArticleConverter {

    private final MasterToMasterShortFormDtoConverter masterShortFormDtoConverter;

    private final MasterRepository masterRepository;

    @Value("${app.article-photo-path}")
    private String baseUrl;

    public UsefulArticleDto toDto(UsefulArticle article) {
        if (article == null) {
            return null;
        }
        Locale locale = LocaleContextHolder.getLocale();
        UsefulArticleDto.UsefulArticleDtoBuilder builder = UsefulArticleDto.builder()
            .id(article.getId())
            .title(article.getTitle())
            .text(article.getText())
            .master(masterShortFormDtoConverter.convert(article.getMaster()))
            .shortDescription(article.getShortDescription())
            .coverImageUrl(baseUrl + "/" + article.getCoverImageUrl())
            .viewsCount(article.getViewsCount())
            .readingTimeMinutes(article.getReadingTimeMinutes())
            .creatingDate(article.getCreatingDate());
        return switch (locale.getLanguage()) {
            case "ru" -> builder
                .type(article.getType().getNameRu())
                .build();
            default -> builder.type(article.getType().getNameEn())
                .build();
        };
    }

    public UsefulArticle toEntity(UsefulArticleDto dto) {
        if (dto == null) {
            return null;
        }
        Master master = masterRepository.findById(dto.getMaster().getId())
            .orElseThrow(() -> new EntityNotFoundException("Master with id=%d not found"));

        return UsefulArticle.builder()
            .id(dto.getId())
            .type(ArticleType.valueOf(dto.getType()))
            .title(dto.getTitle())
            .text(dto.getText())
            .master(master)
            .creatingDate(dto.getCreatingDate())
            .shortDescription(dto.getShortDescription())
            .coverImageUrl(getImageName(dto.getCoverImageUrl()))
            .viewsCount(dto.getViewsCount())
            .readingTimeMinutes(dto.getReadingTimeMinutes())
            .build();
    }

    private String getImageName(String url) {
        String[] split = url.split("/");
        if (split.length != 4) {
            throw new IncorrectDataException("Incorrect path to photo");
        }
        return split[3];
    }
}
