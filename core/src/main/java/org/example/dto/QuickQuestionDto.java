package org.example.dto;

import lombok.Builder;
import lombok.Data;
import org.example.enums.QuestionCategory;

@Data
@Builder
public class QuickQuestionDto {
    private Long id;
    private String question;
    private String answer;
    private QuestionCategory.CategoryDto category;
}
