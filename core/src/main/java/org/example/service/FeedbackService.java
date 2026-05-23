package org.example.service;

import org.example.dto.FeedbackDto;
import org.example.dto.FeedbackShortDto;
import org.springframework.data.domain.Page;

public interface FeedbackService {

    Page<FeedbackShortDto> findAll(Long masterId, Integer score, String sort, Integer pageNumber, Integer pageSize);

    void save(FeedbackDto dto);
}
