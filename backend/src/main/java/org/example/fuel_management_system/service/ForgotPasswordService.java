package org.example.fuel_management_system.service;

import org.example.fuel_management_system.Repository.ForgotPasswordRepository;
import org.example.fuel_management_system.model.ForgotPasswordToken;
import org.example.fuel_management_system.model.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public interface ForgotPasswordService{



 ForgotPasswordToken createToken(UserAccount user, String id, String otp, String sendTo);


 ForgotPasswordToken findById(String id);
 ForgotPasswordToken findByUser(int userId) ;
 void deleteToken(ForgotPasswordToken token);
}
