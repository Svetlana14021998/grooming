package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.converter.FeedbackToShortFeedbackDtoConverter;
import org.example.dto.FeedbackDto;
import org.example.dto.FeedbackShortDto;
import org.example.exception.EntityNotFoundException;
import org.example.exception.IncorrectRequestParamException;
import org.example.model.Feedback;
import org.example.model.Master;
import org.example.repository.FeedbackRepository;
import org.example.repository.MasterRepository;
import org.example.repository.spec.FeedbackRepositorySpecifications;
import org.example.service.FeedbackService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepository feedbackRepository;

    private final MasterRepository masterRepository;

    private final FeedbackToShortFeedbackDtoConverter feedbackDtoConverter;

    @Transactional
    @Override
    public void save(FeedbackDto dto) {
        Master master = masterRepository.findById(dto.getMasterId())
            .orElseThrow(() -> new EntityNotFoundException("Мастер с id=%s не найден".formatted(dto.getMasterId())));
        Feedback feedback = Feedback.builder()
            .visitDate(dto.getVisitDate())
            .clientName(dto.getClientName())
            .petName(dto.getPetName())
            .phone(dto.getPhone())
            .email(dto.getEmail())
            .review(dto.getReview())
            .score(dto.getScore())
            .master(master)
            .build();
        feedbackRepository.save(feedback);
    }

    @Transactional
    public Page<FeedbackShortDto> findAll(Long masterId, Integer score, String sorted, Integer pageNumber, Integer pageSize) {
        Specification<Feedback> spec = Specification.allOf();
        Sort sort = Sort.unsorted();
        if (masterId != null) {
            spec = spec.and(FeedbackRepositorySpecifications.masterIdIs(masterId));
        }
        if (score != null) {
            spec = spec.and(FeedbackRepositorySpecifications.scoreIsMoreOrEqualsThan(score));
        }
        if (sorted != null && !sorted.isBlank()) {
            String[] parts = sorted.split("_");
            if (parts.length != 2) {
                throw new IncorrectRequestParamException("Incorrect request sorted params for search feedback");
            }
            String fieldName = parts[0];
            String sortType = parts[1];
            sort = sort.and(Sort.by(Sort.Direction
                .fromString(sortType.toUpperCase()), fieldName));
        }
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        return feedbackRepository.findAll(spec, pageable)
            .map(feedbackDtoConverter::convert);
    }
}
