package org.example.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MasterDto {

    private Long id;

    private String fullName;

    private String experience;

    private List<String> services;

    private String pathToPhoto;

    private Double rating;

    private Long reviewsCount;
}
