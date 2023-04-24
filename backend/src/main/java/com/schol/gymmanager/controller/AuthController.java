package com.schol.gymmanager.controller;

import com.schol.gymmanager.model.AuthRequest;
import com.schol.gymmanager.model.AuthResponse;
import com.schol.gymmanager.model.Customer;
import com.schol.gymmanager.model.DTOs.CustomerDto;
import com.schol.gymmanager.model.RegisterRequest;
import com.schol.gymmanager.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService service;
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(
            @RequestBody CustomerDto request
    ){
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> authenticate(
            @RequestBody CustomerDto request
    ){
        return ResponseEntity.ok(service.authenticate(request));
    }
}
