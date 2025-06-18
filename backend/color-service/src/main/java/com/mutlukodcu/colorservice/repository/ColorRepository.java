package com.mutlukodcu.colorservice.repository;

import com.mutlukodcu.colorservice.domain.Color;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ColorRepository extends JpaRepository<Color, Long> {
    List<Color> findByCategory(String category);
}
