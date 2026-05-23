package org.example.service;

import org.example.dto.GroomingServiceDto;

import java.util.List;

public interface GroomingServiceService {

    List<GroomingServiceDto> findAll(Long animalTypeId, String category, Integer priceFrom, Integer priceTo);

    List<GroomingServiceDto> findPopular();
}
