package org.example.service;

import org.example.dto.UsefulArticleDto;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UsefulArticleService {

    Page<UsefulArticleDto> findAll(Long masterId, String sort, String type, String title, Integer pageNumber, Integer pageSize);

    void save(UsefulArticleDto articleDto, Long masterId, MultipartFile photo);

    List<UsefulArticleDto> findByMasterId(Long id);

    long countArticlesForMaster(Long id);

    UsefulArticleDto findById(Long id);
}
