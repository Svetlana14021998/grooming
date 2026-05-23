package org.example.repository.spec;

import org.example.model.AnimalType;
import org.example.model.GroomingService;
import org.example.enums.GroomingServiceCategory;
import org.springframework.data.jpa.domain.Specification;

public class GroomingServiceSpecifications {

    public static Specification<GroomingService> priceGreaterThan(Integer minPrice){
        return  (root, criteriaQuery, criteriaBuilder) ->
            criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice);
    }

    public static Specification<GroomingService> priceLessThan(Integer maxPrice){
        return  (root, criteriaQuery, criteriaBuilder) ->
            criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice);
    }

    public static Specification<GroomingService> categoryEqualsTo(GroomingServiceCategory category){
        return  (root, criteriaQuery, criteriaBuilder) ->
            criteriaBuilder.equal(root.get("category"), category);
    }

    public static Specification<GroomingService> animalTypeEqualsTo(AnimalType type){
        return  (root, criteriaQuery, criteriaBuilder) ->
            criteriaBuilder.isMember(type,root.get("animalTypes"));
    }
}
