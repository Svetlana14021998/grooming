package org.example.service.impl;

import lombok.extern.log4j.Log4j2;
import org.example.exception.CantSaveFileException;
import org.example.service.ImageService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    /**
     * TODO
     * Костыль. Так как фото храняться в папке static, она компилируется и храниться в target->
     * при изменении во время работы программы ничего не подтягивается в target и используется старое изображение
     *  Решение в перспективе - использовать для хранения внешнюю папку
     *
     * @param photo     новое фото
     * @param photoName названия фото для пользователя
     * @param moduleName название модуля (admin,client)
     * @param packageName в какой папке
     */
    public void savePhoto(MultipartFile photo, String photoName, String moduleName, String packageName) {
        String[] paths = {
            "%s/src/main/resources/static/images/%s/".formatted(moduleName, packageName),
            "%s/target/classes/static/images/%s/".formatted(moduleName, packageName)
        };

        for (String path : paths) {
            Path uploadPath = Paths.get(path);
            try {
                Files.createDirectories(uploadPath);
                Files.write(uploadPath.resolve(photoName), photo.getBytes());
            } catch (IOException e) {
                throw new CantSaveFileException();
            }
        }
    }
}

