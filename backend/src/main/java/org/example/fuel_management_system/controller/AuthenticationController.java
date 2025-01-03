package org.example.fuel_management_system.controller;

import org.apache.catalina.User;
import org.example.fuel_management_system.model.AuthenticationResponse;
import org.example.fuel_management_system.model.Role;
import org.example.fuel_management_system.model.UserAccount;
import org.example.fuel_management_system.service.AuthenticateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class AuthenticationController {
    private final AuthenticateService authenticateService;

    public AuthenticationController(AuthenticateService authenticateService) {
        this.authenticateService = authenticateService;
    }
@PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody UserAccount request){
        try{
            return ResponseEntity.ok(authenticateService.register(request));

        } catch (RuntimeException e) {
              throw new RuntimeException(e.getMessage());
        }


    }
@PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody UserAccount request){
        return ResponseEntity.ok(authenticateService.authenticate(request));
    }

@GetMapping("/allaccounts")
    public ResponseEntity<List<UserAccount>>getAllAccounts(){
        return ResponseEntity.ok(authenticateService.getAllAccounts());
}

@GetMapping("/account/{id}")
public ResponseEntity<UserAccount> getAccountById(@PathVariable("id") int userId){
        return ResponseEntity.ok(authenticateService.getAccountById(userId));
}

    @GetMapping("/by-role")
    public ResponseEntity<List<UserAccount>> getUsersByRole(@RequestParam Role role) {
       return ResponseEntity.ok(authenticateService.getUsersByRole(role));
    }


}
