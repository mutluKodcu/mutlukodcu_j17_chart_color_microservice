package com.mutlukodcu.photogalleryservice.repository;

import com.mutlukodcu.photogalleryservice.domain.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
    List<Photo> findByUserId(String userId);
    List<Photo> findByCategory(String category);
}
