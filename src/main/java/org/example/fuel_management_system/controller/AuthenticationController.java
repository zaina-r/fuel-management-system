package org.example.fuel_management_system.controller;

import org.example.fuel_management_system.model.AuthenticationResponse;
import org.example.fuel_management_system.model.UserAccount;
import org.example.fuel_management_system.service.AuthenticateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    private final AuthenticateService authenticateService;

    public AuthenticationController(AuthenticateService authenticateService) {
        this.authenticateService = authenticateService;
    }
@PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody UserAccount request){

        return ResponseEntity.ok(authenticateService.register(request));

    }
@PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody UserAccount request){
        return ResponseEntity.ok(authenticateService.authenticate(request));
    }
}
