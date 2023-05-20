package com.spring.hospital.controller;

import com.spring.hospital.dto.AuthenticationRequest;
import com.spring.hospital.dto.AuthenticationResponse;
import com.spring.hospital.dto.RegisterRequest;
import com.spring.hospital.service.Implementation.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.yaml.snakeyaml.constructor.DuplicateKeyException;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @ModelAttribute RegisterRequest registerRequest
            ) {

        try {
            AuthenticationResponse response = authenticationService.register(registerRequest);
            response.setMessage("Registration successful!");
            return ResponseEntity.ok(response);
        } catch (DuplicateKeyException e) {
            String message = "This email address is already registered. Please try again with a different email.";
            AuthenticationResponse response = new AuthenticationResponse(message);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest authenticationRequest ){
        return  ResponseEntity.ok(authenticationService.authenticate(authenticationRequest));
    }


}
