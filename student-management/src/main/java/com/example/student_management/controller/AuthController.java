package com.example.student_management.controller;
import com.example.student_management.dto.*;
import com.example.student_management.entity.User;
import com.example.student_management.repository.UserRepository;
import com.example.student_management.security.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor

public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    // POST /api/v1/auth/register
    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> register(
            @Valid @RequestBody RegisterRequestDTO dto) {

        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        User user = User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .role(User.Role.USER)
                .build();

        userRepository.save(user);

        String token = jwtUtil.generateToken(
                user.getEmail(), user.getRole().name());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(AuthResponseDTO.builder()
                        .token(token)
                        .email(user.getEmail())
                        .role(user.getRole().name())
                        .build());
    }

    // POST /api/v1/auth/login
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(
            @Valid @RequestBody LoginRequestDTO dto) {

        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        dto.getEmail(), dto.getPassword()));

        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow();

        String token = jwtUtil.generateToken(
                user.getEmail(), user.getRole().name());

        return ResponseEntity.ok(AuthResponseDTO.builder()
                .token(token)
                .email(user.getEmail())
                .role(user.getRole().name())
                .build());
    }
    // POST /api/v1/auth/register/admin
    @PostMapping("/register/admin")
    public ResponseEntity<AuthResponseDTO> registerAdmin(
            @Valid @RequestBody RegisterRequestDTO dto) {

        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        User user = User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .role(User.Role.ADMIN)   // <-- only difference
                .build();

        userRepository.save(user);

        String token = jwtUtil.generateToken(
                user.getEmail(), user.getRole().name());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(AuthResponseDTO.builder()
                        .token(token)
                        .email(user.getEmail())
                        .role(user.getRole().name())
                        .build());
    }
}