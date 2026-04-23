package org.example.repository;

import org.example.model.Master;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MasterRepository extends JpaRepository<Master,Long> {
}
