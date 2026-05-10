package org.example.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Получаем при создании отзыва
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackDto {

    @NotNull(message = "{visit_date_not_empty}")
    private LocalDate visitDate;

    @NotBlank(message = "{name_not_empty}")
    private String clientName;

    @NotBlank(message = "{pet_name_not_empty}")
    private String petName;

    @NotBlank(message = "{phone_not_empty}")
    @Pattern(regexp = "^(?=.*\\d)[\\d+\\-()]+$", message = "{incorrect_phone}")
    private String phone;

    @NotBlank(message = "{email_not_empty}")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", message = "{incorrect_email}")
    private String email;

    private String review;

    @NotNull(message = "{score_not_empty}")
    private Integer score;

    @NotNull(message = "{master_not_empty}")
    private Long masterId;
}
