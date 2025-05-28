package com.folmy.folmypractice.controller;

import com.folmy.folmypractice.dto.LoginRequestDto;
import com.folmy.folmypractice.dto.RegisterRequestDto;
import com.folmy.folmypractice.facade.AuthFacade;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthFacade authFacade;

    public AuthController(AuthFacade authFacade) {
        this.authFacade = authFacade;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequestDto registerRequestDto) {
        authFacade.register(registerRequestDto);
        return ResponseEntity.ok("User registered");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequestDto loginRequestDto) {
        String token = authFacade.login(loginRequestDto.login(), loginRequestDto.password());
        return ResponseEntity.ok(Map.of("token", token));
    }
}
