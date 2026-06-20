package org.example.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
public class UsefulArticleDto {

    private Long id;
    private String type;
    private String title;
    private String text;
    private MasterShortFormDto master;
    private LocalDate creatingDate;

    private String shortDescription;
    private String coverImageUrl;
    private Long viewsCount;
    private Integer readingTimeMinutes;
}
