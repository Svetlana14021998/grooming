package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.converter.UsefulArticleConverter;
import org.example.dto.MasterShortFormDto;
import org.example.dto.UsefulArticleDto;
import org.example.enums.ArticleType;
import org.example.exception.EntityNotFoundException;
import org.example.exception.IncorrectRequestParamException;
import org.example.model.UsefulArticle;
import org.example.repository.UsefulArticleRepository;
import org.example.repository.spec.UsefulArticleSpecifications;
import org.example.service.ImageService;
import org.example.service.UsefulArticleService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsefulArticleServiceImpl implements UsefulArticleService {

    private final UsefulArticleRepository repository;

    private final UsefulArticleConverter converter;

    private final ImageService imageService;

    @Value("${app.article-photo-path}")
    private String baseUrl;

    @Override
    public Page<UsefulArticleDto> findAll(Long masterId, String sorted, String type, String title, Integer pageNumber, Integer pageSize) {
        Specification<UsefulArticle> spec = Specification.allOf();
        Sort sort = Sort.by(Sort.Direction.DESC, "creatingDate");
        if (masterId != null) {
            spec = spec.and(UsefulArticleSpecifications.masterIdIs(masterId));
        }
        if (type != null && !type.isBlank()) {
            spec = spec.and(UsefulArticleSpecifications.articleTypeEqualsTo(ArticleType.fromLocalizedName(type)));
        }
        if (title != null && !title.isBlank()) {
            spec = spec.and(UsefulArticleSpecifications.titleContainsIgnoreCase(title));
        }
        if (sorted != null && !sorted.isBlank()) {
            String[] parts = sorted.split("_");
            if (parts.length != 2) {
                throw new IncorrectRequestParamException("Incorrect request sorted params for search feedback");
            }
            String fieldName = parts[0];
            String sortType = parts[1];
            sort = Sort.by(Sort.Direction
                .fromString(sortType.toUpperCase()), fieldName);
        }
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        return repository.findAll(spec, pageable)
            .map(converter::toDto);
    }

    @Override
    public void save(UsefulArticleDto articleDto, Long masterId, MultipartFile photo) {
        String fileName = imageService.generateImageName("articles", articleDto.getType(), masterId);

        articleDto.setMaster(MasterShortFormDto.builder()
            .id(masterId)
            .build());
        articleDto.setCoverImageUrl(baseUrl + "/" + fileName);
        articleDto.setViewsCount(0L);

        UsefulArticle entity = converter.toEntity(articleDto);
        repository.save(entity);
        imageService.savePhoto(photo,fileName,"core","articles");
    }

    @Override
    public List<UsefulArticleDto> findByMasterId(Long id) {
        return repository.findByMasterId(id).stream()
            .map(converter::toDto)
            .toList();
    }

    @Override
    public long countArticlesForMaster(Long id) {
        return repository.countByMasterId(id);
    }

    @Transactional
    @Override
    public UsefulArticleDto findById(Long id) {
        UsefulArticle article = repository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Article with id=%d not found"));
        article.setViewsCount(article.getViewsCount() + 1);
        return converter.toDto(article);
    }
}
