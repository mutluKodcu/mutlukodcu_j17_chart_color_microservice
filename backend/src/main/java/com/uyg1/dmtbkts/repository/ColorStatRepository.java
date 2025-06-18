package com.uyg1.dmtbkts.repository;

import com.uyg1.dmtbkts.domain.ColorStat;
import com.uyg1.dmtbkts.domain.AppUser;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColorStatRepository extends JpaRepository<ColorStat, Long> {
    List<ColorStat> findByUser(AppUser user);
}