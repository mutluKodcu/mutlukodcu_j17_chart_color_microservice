package com.mutlukodcu.colorservice.controller;

import com.mutlukodcu.colorservice.domain.Color;
import com.mutlukodcu.colorservice.dto.ColorDto;
import com.mutlukodcu.colorservice.service.ColorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/colors")
@RequiredArgsConstructor
public class ColorController {

    private final ColorService colorService;

    @PostMapping
    public Color create(@RequestBody ColorDto dto) {
        return colorService.create(dto);
    }

    @GetMapping
    public List<Color> getAll() {
        return colorService.findAll();
    }

    @GetMapping("/category/{category}")
    public List<Color> getByCategory(@PathVariable String category) {
        return colorService.findByCategory(category);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        colorService.delete(id);
    }
}
