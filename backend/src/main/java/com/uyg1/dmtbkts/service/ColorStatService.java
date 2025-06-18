package com.uyg1.dmtbkts.service;

import com.uyg1.dmtbkts.domain.AppUser;
import com.uyg1.dmtbkts.domain.ColorStat;
import com.uyg1.dmtbkts.repository.ColorStatRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColorStatService {

    private final ColorStatRepository repository;

    public ColorStatService(ColorStatRepository repository) {
        this.repository = repository;
    }

    public void addColor(AppUser user, String color) {
        ColorStat stat = repository.findByUser(user).stream()
            .filter(s -> s.getColor().equalsIgnoreCase(color))
            .findFirst()
            .orElse(ColorStat.builder().color(color).count(0).user(user).build());

        stat.setCount(stat.getCount() + 1);
        repository.save(stat);
    }

    public List<ColorStat> getUserStats(AppUser user) {
        return repository.findByUser(user);
    }
}