package com.prakshit.jobportalsystem.controller;

import com.prakshit.jobportalsystem.dto.*;
import com.prakshit.jobportalsystem.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {


    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    //to register user/sign up
    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDTO> register(@Valid @RequestBody UserRequestDTO requestDTO){

        RegisterResponseDTO registeredUser = authService.registerUser(requestDTO);

        return new ResponseEntity<>(registeredUser,HttpStatus.CREATED);
    }


    //to login
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO requestDTO){

        return ResponseEntity.ok(
                authService.login(requestDTO)
        );
    }


}
