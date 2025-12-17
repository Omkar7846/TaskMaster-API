package com.TaskMaster.TaskMaster.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TaskMaster.TaskMaster.Model.DTO.AuthResponseDTO;
import com.TaskMaster.TaskMaster.Model.DTO.LoginRequestDTO;
import com.TaskMaster.TaskMaster.Model.DTO.RegisterRequestDTO;
import com.TaskMaster.TaskMaster.Service.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

	@Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterRequestDTO request) {
        authService.register(request);
        return ResponseEntity.ok("User registered successfully.");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@Valid @RequestBody LoginRequestDTO request) {
        String token = authService.login(request);
        return ResponseEntity.ok(new AuthResponseDTO(token, request.getUsername()));
    }
}