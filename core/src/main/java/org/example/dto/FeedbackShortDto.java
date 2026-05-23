package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
/**
 * Отдаем на UI
 * Не содержит конфиденциальную информацию пользователя
 * (телефон, почта, дата визита, имя питомца)
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackShortDto {

    private Long id;

    private LocalDate visitDate;

    private String clientName;

    private String review;

    private Integer score;

    private String masterName;

    private Long masterId;
}
