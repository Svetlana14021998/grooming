package org.example.repository;

import org.example.model.Feedback;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Long>,
    JpaSpecificationExecutor<Feedback> {

    @Query("SELECT master.id, COALESCE (ROUND(AVG(f.score),1), 0.0) AS rating, COALESCE (COUNT(f),0) AS score FROM Feedback f GROUP BY master.id")
    List<Object[]> getRatingAndScoreForMasters();

    @EntityGraph("feedback-withMaster")
    Page<Feedback> findAll(Specification<Feedback> spec, Pageable pageable);
}
