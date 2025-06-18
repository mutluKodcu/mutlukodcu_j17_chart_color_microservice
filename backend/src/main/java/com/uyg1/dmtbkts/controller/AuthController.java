package com.uyg1.dmtbkts.controller;

import com.uyg1.dmtbkts.domain.AppUser;
import com.uyg1.dmtbkts.domain.Role;
import com.uyg1.dmtbkts.service.AppUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AppUserService appUserService;

    public AuthController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @PostMapping("/register")
    public ResponseEntity<AppUser> register(@RequestBody AppUser user) {
        user.setRole(Role.USER);
        return ResponseEntity.ok(appUserService.registerUser(user));
    }
}