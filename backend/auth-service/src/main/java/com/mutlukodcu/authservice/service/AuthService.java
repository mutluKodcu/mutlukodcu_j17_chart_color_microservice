package com.mutlukodcu.authservice.service;

import com.mutlukodcu.authservice.domain.User;
import com.mutlukodcu.authservice.dto.UserDto;
import com.mutlukodcu.authservice.repository.UserRepository;
import com.mutlukodcu.authservice.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public String login(UserDto userDto) {
        Optional<User> userOpt = userRepository.findByUsername(userDto.getUsername());
        if (userOpt.isEmpty()) {
            throw new RuntimeException("Kullanıcı bulunamadı");
        }
        User user = userOpt.get();

        if (!passwordEncoder.matches(userDto.getPassword(), user.getPassword())) {
            throw new RuntimeException("Şifre yanlış");
        }

        return jwtUtil.generateToken(user.getUsername(), user.getRole());
    }

    public void register(UserDto userDto) {
        if (userRepository.findByUsername(userDto.getUsername()).isPresent()) {
            throw new RuntimeException("Kullanıcı zaten mevcut");
        }

        User user = User.builder()
                .username(userDto.getUsername())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .role("ROLE_USER")
                .build();

        userRepository.save(user);
    }
}
