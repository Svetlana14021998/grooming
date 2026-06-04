package org.example.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.enums.QuestionCategory;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(exclude = "id")
@Table(name = "quick_questions")
public class QuickQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rus_question")
    private String rusQuestion;

    @Column(name = "eng_question")
    private String engQuestion;

    @Column(name = "rus_answer")
    private String rusAnswer;

    @Column(name = "eng_answer")
    private String engAnswer;

    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    private QuestionCategory category;
}
