package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.converter.AnimalTypeToAnimalTypeDtoConverter;
import org.example.dto.AnimalTypeDto;
import org.example.model.AnimalType;
import org.example.repository.AnimalTypeRepository;
import org.example.service.AnimalTypeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimalTypeServiceImpl implements AnimalTypeService {

    private final AnimalTypeRepository repository;

    private final AnimalTypeToAnimalTypeDtoConverter animalTypeDtoConverter;

    @Override
    public List<AnimalTypeDto> findAll() {
        List<AnimalType> animalTypes = repository.findAll();
        return animalTypes.stream()
            .map(animalTypeDtoConverter::converter)
            .toList();
    }
}
