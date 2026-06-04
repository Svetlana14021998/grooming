package org.example.service;

import org.example.dto.QuickQuestionDto;
import org.example.enums.QuestionCategory;

import java.util.List;

public interface QuickQuestionService {

    List<QuickQuestionDto> findAll();

    List<QuickQuestionDto> findByCategory(QuestionCategory category);
}
