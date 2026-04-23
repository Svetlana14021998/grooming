package org.example.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MasterDto {

    private Long id;

    private String fullName;

    private String experience;

    private String specialisation;

    private String pathToPhoto;

    private Double rating;

    private Long reviewsCount;
}
