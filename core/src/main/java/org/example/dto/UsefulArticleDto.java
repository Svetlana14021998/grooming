package org.example.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsefulArticleDto {

    private Long id;

    @NotBlank(message = "Необходимо выбрать тип статьи")
    private String type;

    @NotBlank(message = "Заголовок должен быть заполнен")
    @Size(max = 255, message = "Заголовок не должен быть больше 255 символов")
    private String title;

    @NotBlank(message = "Текст статьи должен быть заполнен")
    private String text;

    private MasterShortFormDto master;

    private LocalDate creatingDate;

    @NotBlank(message = "Краткое описание должно быть заполнено")
    @Size(max = 500, message = "Краткое описание не должно превышать 500 символов")
    private String shortDescription;

    private String coverImageUrl;

    private Long viewsCount;

    @NotNull(message = "Необходимо указать время прочтения статьи")
    private Integer readingTimeMinutes;
}
