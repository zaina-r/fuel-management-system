package org.example.fuel_management_system.controller;


import org.example.fuel_management_system.DTO.Response;
import org.example.fuel_management_system.service.TwillioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notifications")
public class TwillioController {

    @Autowired
    private TwillioService twillioService;



    @PostMapping("/generate")
    public ResponseEntity<Response> generateOTP(@RequestParam String telno) {

        Response response = twillioService.sendOTP(telno);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatusCode()));
    }

    @PostMapping("/verify")
    public ResponseEntity<Response> verifyOTP(@RequestParam String telno, @RequestParam String otp) {
        Response response = twillioService.verifyOTP(telno, otp);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatusCode()));
    }


    @PostMapping("/generateNotifications")
    public ResponseEntity<Response> generateNotifications(@RequestParam String telno,@RequestParam String message) {
        Response response = twillioService.sendNotification(telno,message);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatusCode()));
    }
}
