package org.example.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AnimalTypeDto {

    private Long id;

    private String name;

    private String icon;
}
