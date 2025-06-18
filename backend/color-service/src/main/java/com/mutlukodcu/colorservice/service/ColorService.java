package com.mutlukodcu.colorservice.service;

import com.mutlukodcu.colorservice.domain.Color;
import com.mutlukodcu.colorservice.dto.ColorDto;
import com.mutlukodcu.colorservice.kafka.ColorProducer;
import com.mutlukodcu.colorservice.repository.ColorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ColorService {

    private final ColorRepository colorRepository;
    private final ColorProducer colorProducer;

    public Color create(ColorDto dto) {
        Color color = Color.builder()
                .name(dto.getName())
                .hex(dto.getHex())
                .category(dto.getCategory())
                .build();
        Color saved = colorRepository.save(color);
        colorProducer.sendColorCreatedEvent(saved);
        return saved;
    }

    public List<Color> findAll() {
        return colorRepository.findAll();
    }

    public List<Color> findByCategory(String category) {
        return colorRepository.findByCategory(category);
    }

    public void delete(Long id) {
        colorRepository.deleteById(id);
    }
}
