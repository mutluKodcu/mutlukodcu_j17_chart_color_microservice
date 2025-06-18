package com.uyg1.dmtbkts.controller;

import com.uyg1.dmtbkts.domain.Color;
import com.uyg1.dmtbkts.service.ColorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/colors")
public class ColorController {
    private final ColorService colorService;
    public ColorController(ColorService colorService) {
        this.colorService = colorService;
    }

    @GetMapping
    public List<Color> getAll() {
        return colorService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Color> getById(@PathVariable Long id) {
        return colorService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Color> create(@RequestBody Color color) {
        return ResponseEntity.ok(colorService.save(color));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Color> update(@PathVariable Long id, @RequestBody Color color) {
        Color updated = colorService.update(id, color);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        colorService.delete(id);
        return ResponseEntity.ok().build();
    }
}