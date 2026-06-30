package org.example.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {

    List<String> getAllImages();

    void savePhoto(MultipartFile photo, String photoName, String moduleName, String packageName);

    String generateImageName(String path, String type, Long masterId);
}
