package com.schol.gymmanager.controller;

import com.schol.gymmanager.model.*;
import com.schol.gymmanager.model.DTOs.BaseUserDto;
import com.schol.gymmanager.model.DTOs.CustomerDto;
import com.schol.gymmanager.model.enums.Role;
import com.schol.gymmanager.service.AuthService;
import com.schol.gymmanager.service.LogoutService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService service;
    @Autowired
    private final LogoutService logoutService;
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(
            @RequestBody BaseUserDto request
    ){
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> authenticate(
            @RequestBody AuthRequest request
    ){
        return ResponseEntity.ok(service.authenticate(request));
    }

    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        service.refreshToken(request, response);
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request,
                       HttpServletResponse response,
                       Authentication authentication){
        logoutService.logout(request, response, authentication);
    }

    @GetMapping("/loggedInUser")
    public Optional<BaseUser> getLoggedInUser(){
        return service.getLoggedInUser();
    }
}
