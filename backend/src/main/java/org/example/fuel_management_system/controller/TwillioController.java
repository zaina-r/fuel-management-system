package org.example.fuel_management_system.controller;


import org.example.fuel_management_system.service.TwillioService;
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

    @PostMapping("/send")
    public String sendNotification(
            @RequestParam String phoneNumber,
            @RequestParam String message) {
        return twillioService.sendNotification(phoneNumber, message);
    }
}
