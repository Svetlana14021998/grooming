package org.example.repository.spec;

import org.example.enums.ArticleType;
import org.example.model.UsefulArticle;
import org.springframework.data.jpa.domain.Specification;

public class UsefulArticleSpecifications {

    public static Specification<UsefulArticle> articleTypeEqualsTo(ArticleType type) {
        return (root, criteriaQuery, criteriaBuilder) ->
            criteriaBuilder.equal(root.get("type"), type);
    }

    public static Specification<UsefulArticle> masterIdIs(Long id) {
        return (root, criteriaQuery, criteriaBuilder) ->
            criteriaBuilder.equal(root.get("master").get("id"), id);
    }

    public static Specification<UsefulArticle> titleContainsIgnoreCase(String title) {
        return (root, query, criteriaBuilder) -> {
            if (title == null || title.trim().isEmpty()) {
                return criteriaBuilder.conjunction();
            }

            String searchTerm = "%" + title.trim().replaceAll("\\s+", " ") + "%";

            return criteriaBuilder.like(
                criteriaBuilder.lower(root.get("title")),
                searchTerm.toLowerCase());
        };
    }
}
