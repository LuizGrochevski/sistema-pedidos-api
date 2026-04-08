package com.nox.controller;

import com.nox.dto.LoginRequest;
import com.nox.security.JwtUtil;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody LoginRequest request) {

        // ⚠️ MOCK (depois vamos ligar no banco)
        if (!request.username.equals("luiz@email.com") || !request.password.equals("123")) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = JwtUtil.generateToken(request.username);

        Map<String, String> response = new HashMap<>();
        response.put("token", token);

        return response;
    }
}