package org.example.repository;

import org.example.enums.QuestionCategory;
import org.example.model.QuickQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuickQuestionRepository extends JpaRepository<QuickQuestion, Long> {

    List<QuickQuestion> findByCategory(QuestionCategory category);
}
