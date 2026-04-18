package com.nox.service;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nox.repository.UserRepository;
import com.nox.security.JwtUtil;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<String> authenticate(String usernameOrEmail, String rawPassword) {
        return userRepository.findByEmailOrName(usernameOrEmail, usernameOrEmail)
                .filter(user -> passwordEncoder.matches(rawPassword, user.getPasswordHash()))
                .map(user -> JwtUtil.generateToken(user.getEmail()));
    }
}