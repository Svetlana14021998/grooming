package org.example.repository.spec;

import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;
import org.example.enums.UserRole;
import org.example.model.AppUser;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserSpecification {

    public static Specification<AppUser> searchByFullName(String searchTerm) {
        return (root, query, cb) -> {
            if (searchTerm == null || searchTerm.isBlank()) {
                return cb.conjunction();
            }

            String normalized = searchTerm.trim().toLowerCase().replaceAll("\\s+", " ");
            List<String> words = Arrays.stream(normalized.split(" "))
                .filter(word -> !word.isEmpty())
                .toList();

            List<Predicate> wordPredicates = new ArrayList<>();
            for (String word : words) {
                Predicate lastNameMatch = cb.like(
                    cb.lower(cb.trim(root.get("lastName"))),
                    "%" + word + "%"
                );
                Predicate firstNameMatch = cb.like(
                    cb.lower(cb.trim(root.get("firstName"))),
                    "%" + word + "%"
                );
                wordPredicates.add(cb.or(lastNameMatch, firstNameMatch));
            }

            return cb.and(wordPredicates.toArray(new Predicate[0]));
        };
    }

    public static Specification<AppUser> searchByRole(UserRole role) {
        return (root, criteriaQuery, criteriaBuilder) ->
            criteriaBuilder.equal(root.get("role"), role);
    }

    public static Specification<AppUser> birthdayInCurrentMonth() {
        return (root, query, cb) -> {
            Expression<Double> monthFromBirthday =
                cb.function("DATE_PART", Double.class,
                    cb.literal("month"),
                    root.get("birthday"));

            Expression<Double> monthFromCurrentDate =
                cb.function("DATE_PART", Double.class,
                    cb.literal("month"),
                    cb.currentDate());

            return cb.equal(monthFromBirthday, monthFromCurrentDate);
        };
    }
}

