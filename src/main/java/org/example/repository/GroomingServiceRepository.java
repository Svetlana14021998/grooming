package org.example.repository;

import org.example.model.GroomingService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface GroomingServiceRepository extends JpaRepository<GroomingService, Long>,
    JpaSpecificationExecutor<GroomingService> {

    List<GroomingService> findByIsPopularTrue();
}

