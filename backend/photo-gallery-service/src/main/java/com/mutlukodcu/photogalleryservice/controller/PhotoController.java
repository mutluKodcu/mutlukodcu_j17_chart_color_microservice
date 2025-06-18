package com.mutlukodcu.photogalleryservice.controller;

import com.mutlukodcu.photogalleryservice.domain.Photo;
import com.mutlukodcu.photogalleryservice.service.PhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/photos")
@RequiredArgsConstructor
public class PhotoController {

    private final PhotoService photoService;

    @PostMapping("/upload")
    public ResponseEntity<Photo> uploadPhoto(
            @RequestParam("file") MultipartFile file,
            @RequestParam("category") String category,
            @RequestHeader("X-USER-ID") String userId // frontend JWT'den çözülüp header'a eklenecek
    ) throws Exception {
        return ResponseEntity.ok(photoService.savePhoto(file, category, userId));
    }

    @GetMapping("/user")
    public ResponseEntity<List<Photo>> userPhotos(@RequestHeader("X-USER-ID") String userId) {
        return ResponseEntity.ok(photoService.getPhotosByUser(userId));
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Photo>> byCategory(@PathVariable String category) {
        return ResponseEntity.ok(photoService.getPhotosByCategory(category));
    }
}
