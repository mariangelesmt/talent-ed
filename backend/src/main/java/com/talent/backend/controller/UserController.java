package com.talent.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping
    public ResponseEntity<?> getUserInfo(Authentication auth) {
        return ResponseEntity.ok("Usuario autenticado: " + auth.getName());
    }
}
