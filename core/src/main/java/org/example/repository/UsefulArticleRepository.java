package org.example.repository;

import org.example.model.UsefulArticle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface UsefulArticleRepository extends JpaRepository<UsefulArticle, Long>,
    JpaSpecificationExecutor<UsefulArticle> {

    List<UsefulArticle> findByMasterId(Long id);

    Page<UsefulArticle> findAll(Specification<UsefulArticle> spec, Pageable pageable);
}
