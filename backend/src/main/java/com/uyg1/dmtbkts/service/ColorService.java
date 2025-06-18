package com.uyg1.dmtbkts.service;

import com.uyg1.dmtbkts.domain.Color;
import com.uyg1.dmtbkts.repository.ColorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ColorService {
    private final ColorRepository colorRepository;
    public ColorService(ColorRepository colorRepository) {
        this.colorRepository = colorRepository;
    }

    public List<Color> getAll() {
        return colorRepository.findAll();
    }

    public Optional<Color> getById(Long id) {
        return colorRepository.findById(id);
    }

    public Color save(Color color) {
        return colorRepository.save(color);
    }

    public Color update(Long id, Color updatedColor) {
        return colorRepository.findById(id).map(color -> {
            color.setName(updatedColor.getName());
            color.setHexCode(updatedColor.getHexCode());
            return colorRepository.save(color);
        }).orElse(null);
    }

    public void delete(Long id) {
        colorRepository.deleteById(id);
    }
}