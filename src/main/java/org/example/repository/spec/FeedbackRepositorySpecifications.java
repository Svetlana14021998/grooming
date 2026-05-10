package org.example.repository.spec;

import org.example.model.Feedback;

import org.springframework.data.jpa.domain.Specification;

public class FeedbackRepositorySpecifications {

    public static Specification<Feedback> masterIdIs(Long id) {
        return (root, criteriaQuery, criteriaBuilder) ->
            criteriaBuilder.equal(root.get("master").get("id"), id);
    }

    public static Specification<Feedback> scoreIsMoreOrEqualsThan(Integer score) {
        return (root, criteriaQuery, criteriaBuilder) ->
            criteriaBuilder.greaterThanOrEqualTo(root.get("score"), score);
    }
}
