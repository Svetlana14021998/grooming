package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.converter.QuickQuestionToQuickQuestionDtoConverter;
import org.example.dto.QuickQuestionDto;
import org.example.enums.QuestionCategory;
import org.example.repository.QuickQuestionRepository;
import org.example.service.QuickQuestionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuickQuestionServiceImpl implements QuickQuestionService {

    private final QuickQuestionRepository repository;

    private final QuickQuestionToQuickQuestionDtoConverter converter;

    @Override
    public List<QuickQuestionDto> findAll() {
        return repository.findAll().stream().map(converter::convert).toList();
    }

    @Override
    public List<QuickQuestionDto> findByCategory(QuestionCategory category) {
        return repository.findByCategory(category).stream().map(converter::convert).toList();
    }
}
