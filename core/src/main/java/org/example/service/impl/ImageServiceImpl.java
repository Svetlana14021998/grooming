package org.example.service.impl;

import lombok.extern.log4j.Log4j2;
import org.example.service.ImageService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class ImageServiceImpl implements ImageService {

    @Override
    public List<String> getAllImages() {
        try {
            ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            Resource[] resources = resolver.getResources("classpath:static/images/results/*.{jpg,jpeg,png,gif}");

            return Arrays.stream(resources)
                .map(Resource::getFilename)
                .map(filename -> "/images/results/" + filename)
                .collect(Collectors.toList());
        } catch (IOException e) {
            log.error("Failed to load images", e);
            return Collections.emptyList();
        }
    }
}

