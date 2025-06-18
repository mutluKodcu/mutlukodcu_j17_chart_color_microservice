package com.uyg1.dmtbkts.controller;

import com.uyg1.dmtbkts.config.CustomUserDetails;
import com.uyg1.dmtbkts.domain.ColorStat;
import com.uyg1.dmtbkts.service.ColorStatService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/colors/stat")
public class ColorStatController {

    private final ColorStatService service;

    public ColorStatController(ColorStatService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public void addColor(@AuthenticationPrincipal CustomUserDetails userDetails, @RequestParam String color) {
        service.addColor(userDetails.getUser(), color);
    }

    @GetMapping
    public List<ColorStat> getUserColorStats(@AuthenticationPrincipal CustomUserDetails userDetails) {
        return service.getUserStats(userDetails.getUser());
    }
}