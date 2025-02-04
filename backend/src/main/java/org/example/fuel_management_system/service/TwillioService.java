package org.example.fuel_management_system.service;

import org.example.fuel_management_system.DTO.Response;
import org.springframework.stereotype.Service;





@Service
public interface TwillioService {


 Response sendNotification(String phoneNumber, String messageBody) ;

 Response sendOTP(String phoneNumber) ;

 Response verifyOTP(String phoneNumber, String otp);



}
