package org.example.fuel_management_system.service;

import org.example.fuel_management_system.Repository.TwoFactorOtpRepository;
import org.example.fuel_management_system.model.TwoFactorOtp;
import org.example.fuel_management_system.model.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public interface TwoFactorOtpService {




 TwoFactorOtp createTwoFactorOtp(UserAccount user, String otp, String jwt) ;

 TwoFactorOtp findByUser(int id) ;

 TwoFactorOtp findById(String id);

 boolean verifyTwoFactorOtp(TwoFactorOtp twoFactorOTP, String otp) ;

 void deleteTwoFactorOtp(TwoFactorOtp twoFactorOTP);

 TwoFactorOtp findByOtp(String otp) ;


}
