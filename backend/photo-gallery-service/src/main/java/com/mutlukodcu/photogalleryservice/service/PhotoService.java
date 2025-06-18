package com.mutlukodcu.photogalleryservice.service;

import com.mutlukodcu.photogalleryservice.domain.Photo;
import com.mutlukodcu.photogalleryservice.kafka.PhotoProducer;
import com.mutlukodcu.photogalleryservice.repository.PhotoRepository;
import com.mutlukodcu.shared.dto.PhotoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PhotoService {

    private final PhotoRepository photoRepository;
    private final PhotoProducer photoProducer;

    private final String BASE_UPLOAD_DIR = "uploads/";

    public Photo savePhoto(MultipartFile file, String category, String userId) throws Exception {
        String dirPath = BASE_UPLOAD_DIR + category;
        Files.createDirectories(Paths.get(dirPath));

        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Path filePath = Paths.get(dirPath, fileName);
        file.transferTo(filePath.toFile());

        Photo photo = Photo.builder()
                .fileName(fileName)
                .filePath(filePath.toString())
                .category(category)
                .userId(userId)
                .build();

        Photo saved = photoRepository.save(photo);

        // Kafka Event Gönderimi
        photoProducer.sendPhotoEvent(PhotoDTO.builder()
                .photoId(saved.getId())
                .fileName(fileName)
                .category(category)
                .filePath(filePath.toString())
                .userId(userId)
                .build());

        return saved;
    }

    public List<Photo> getPhotosByUser(String userId) {
        return photoRepository.findByUserId(userId);
    }

    public List<Photo> getPhotosByCategory(String category) {
        return photoRepository.findByCategory(category);
    }
}
