package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.converter.GroomingServiceToGroomingServiceDtoConverter;
import org.example.dto.GroomingServiceDto;
import org.example.enums.GroomingServiceCategory;
import org.example.exception.UnknownTypeException;
import org.example.model.AnimalType;
import org.example.model.GroomingService;
import org.example.repository.AnimalTypeRepository;
import org.example.repository.GroomingServiceRepository;
import org.example.repository.spec.GroomingServiceSpecifications;
import org.example.service.GroomingServiceService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroomingServiceServiceImpl implements GroomingServiceService {

    private final GroomingServiceRepository groomingServiceRepository;

    private final AnimalTypeRepository animalTypeRepository;

    private final GroomingServiceToGroomingServiceDtoConverter groomingServiceDtoConverter;

    @Transactional
    @Override
    public List<GroomingServiceDto> findAll(Long animalTypeId, String category, Integer priceFrom, Integer priceTo) {
        Specification<GroomingService> spec = Specification.allOf();
        if (animalTypeId != null) {
            AnimalType animalType = animalTypeRepository.findById(animalTypeId)
                .orElseThrow(() -> new UnknownTypeException("AnimalType with id=%s not found".formatted(animalTypeId)));
            spec = spec.and(GroomingServiceSpecifications.animalTypeEqualsTo(animalType));
        }
        if (category != null && !category.isBlank()) {
            GroomingServiceCategory groomingServiceCategory = GroomingServiceCategory.fromLocalizedName(category);
            spec = spec.and(GroomingServiceSpecifications.categoryEqualsTo(groomingServiceCategory));
        }
        if (priceFrom != null) {
            spec = spec.and(GroomingServiceSpecifications.priceGreaterThan(priceFrom));
        }
        if (priceTo != null) {
            spec = spec.and(GroomingServiceSpecifications.priceLessThan(priceTo));
        }
        List<GroomingService> services = groomingServiceRepository.findAll(spec);

        return services.stream()
            .map(groomingServiceDtoConverter::convert)
            .toList();
    }

    @Transactional
    @Override
    public List<GroomingServiceDto> findPopular() {
        List<GroomingService> services = groomingServiceRepository.findByIsPopularTrue();
        return services.stream()
            .map(groomingServiceDtoConverter::convert)
            .toList();
    }
}
