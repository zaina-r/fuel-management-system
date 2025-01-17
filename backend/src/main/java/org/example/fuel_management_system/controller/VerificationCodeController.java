package org.example.fuel_management_system.controller;

import org.example.fuel_management_system.OtpGenerator.GenerateOtp;
import org.example.fuel_management_system.enumpackage.Role;
import org.example.fuel_management_system.model.VerificationCode;
import org.example.fuel_management_system.service.VerificationCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/verification_code")
public class VerificationCodeController {
    @Autowired
    private VerificationCodeService verificationCodeService;


    @PostMapping("/verify-otp/{otp}")
    public ResponseEntity<String> verifyOtp(
            @RequestParam String id,

            @PathVariable String otp) throws Exception {

        VerificationCode twoFactorOtp = verificationCodeService.getVerificationCode(id);




        if (twoFactorOtp.getOtp().equals(otp) &&
                twoFactorOtp.getExpirationTime().isAfter(LocalDateTime.now())) {
            return ResponseEntity.ok("OTP verification successful.");
        }


        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("Invalid or expired OTP.");
    }



}
