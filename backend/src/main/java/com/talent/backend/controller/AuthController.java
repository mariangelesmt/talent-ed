package com.talent.backend.controller;

import com.talent.backend.model.User;
import com.talent.backend.model.dto.UserDTO;
import com.talent.backend.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body) {
        String email = body.get("email");
        String password = body.get("password");

        String token = authService.login(email, password);
        User user = authService.findByEmail(email);
        return ResponseEntity.ok(Map.of(
                "token", token,
                "email", email,
                "name", user.getName(),
                "lastName", user.getLastName(),
                "roles", user.getRoles()));
    }
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDTO user) {
        User savedUser = authService.register(user);
        return ResponseEntity.ok(Map.of(
                "id", savedUser.getId(),
                "email", savedUser.getEmail()));
    }
}
