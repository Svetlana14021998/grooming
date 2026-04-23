package org.example.repository;

import org.example.model.AnimalType;
import org.example.model.GroomingService;
import org.example.model.enums.GroomingServiceCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface GroomingServiceRepository extends JpaRepository<GroomingService, Long>,
    JpaSpecificationExecutor<GroomingService> {

    List<GroomingService> findByIsPopularTrue();

    List<GroomingService> findByAnimalTypesContains(AnimalType animalType);

    List<GroomingService> findByCategory(GroomingServiceCategory category);
}

