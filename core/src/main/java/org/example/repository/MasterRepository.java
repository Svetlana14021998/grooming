package org.example.repository;

import org.example.model.Master;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MasterRepository extends JpaRepository<Master, Long> {

    @EntityGraph("master_with_services")
    @Query("select distinct m from Master m")
    List<Master> findAllWithServices();
}
