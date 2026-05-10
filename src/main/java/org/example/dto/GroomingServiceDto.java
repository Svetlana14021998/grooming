package org.example.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

@Data
@Builder
public class GroomingServiceDto {

    private String name;

    private String description;

    private BigDecimal price;

    private Integer duration;

    private String category;

    private Set<AnimalTypeDto> animalTypes;

    private String photoPath;

    private boolean isPopular;
}
