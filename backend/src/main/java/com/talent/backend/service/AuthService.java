package com.talent.backend.service;

import com.talent.backend.model.User;
import com.talent.backend.model.dto.UserDTO;
import com.talent.backend.repository.UserRepository;
import com.talent.backend.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
//TO_DELETE:Busca al usuario en base de datos si las credenciales son correctas, genera JWT
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public String login(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return JwtUtil.generateToken(user);
    }

    public User register(UserDTO request) {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setName(request.getName());
        user.setLastName(request.getLastName());
        user.setCity(request.getCity());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRoles(request.getRoles());
        return userRepository.save(user);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }
}
