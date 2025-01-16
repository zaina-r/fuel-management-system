package org.example.fuel_management_system.service;

import org.example.fuel_management_system.Repository.ForgotPasswordRepository;
import org.example.fuel_management_system.model.ForgotPasswordToken;
import org.example.fuel_management_system.model.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ForgotPasswordService{

    @Autowired
    private ForgotPasswordRepository forgotPasswordRepository;


    public ForgotPasswordToken createToken(UserAccount user, String id, String otp, String sendTo) {

        ForgotPasswordToken forgotPasswordToken=new ForgotPasswordToken();
        forgotPasswordToken.setUserAccount(user);
        forgotPasswordToken.setOtp(otp);
        forgotPasswordToken.setId(id);
        forgotPasswordToken.setEmail(sendTo);
        return forgotPasswordRepository.save(forgotPasswordToken);

    }


    public ForgotPasswordToken findById(String id) {
        Optional<ForgotPasswordToken> token=forgotPasswordRepository.findById(id);
        return token.orElse(null);

    }


    public ForgotPasswordToken findByUser(int userId) {
        return forgotPasswordRepository.findByUserAccount_UserId(userId);
    }


    public void deleteToken(ForgotPasswordToken token) {
        forgotPasswordRepository.delete(token);

    }
}
