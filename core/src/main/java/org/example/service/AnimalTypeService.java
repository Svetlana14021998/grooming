package org.example.service;

import org.example.dto.AnimalTypeDto;

import java.util.List;

public interface AnimalTypeService {

    List<AnimalTypeDto> findAll();
}
