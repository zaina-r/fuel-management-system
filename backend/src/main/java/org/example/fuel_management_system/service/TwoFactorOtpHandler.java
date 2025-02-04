package org.example.fuel_management_system.service;

import org.example.fuel_management_system.Repository.TwoFactorOtpRepository;
import org.example.fuel_management_system.model.TwoFactorOtp;
import org.example.fuel_management_system.model.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class TwoFactorOtpHandler implements TwoFactorOtpService{

    @Autowired
    private TwoFactorOtpRepository twoFactorOtpRepository;


    public TwoFactorOtp createTwoFactorOtp(UserAccount user, String otp, String jwt) {
        UUID uuid= UUID.randomUUID();
        String id=uuid.toString();
        TwoFactorOtp twoFactorOTP=new TwoFactorOtp();
        twoFactorOTP.setOtp(otp);
        twoFactorOTP.setJwt(jwt);
        twoFactorOTP.setId(id);
        twoFactorOTP.setUser(user);
        return twoFactorOtpRepository.save(twoFactorOTP);
    }


    public TwoFactorOtp findByUser(int id) {
        return twoFactorOtpRepository.findByUser_UserId(id);
    }


    public TwoFactorOtp findById(String id) {
        Optional<TwoFactorOtp> otp=twoFactorOtpRepository.findById(id);
        return otp.orElse(null);

    }


    public boolean verifyTwoFactorOtp(TwoFactorOtp twoFactorOTP, String otp) {
        if(twoFactorOTP.getOtp().equals(otp)){
            return true;
        }
        return false;
    }


    public void deleteTwoFactorOtp(TwoFactorOtp twoFactorOTP) {
        twoFactorOtpRepository.delete(twoFactorOTP);

    }

    public TwoFactorOtp findByOtp(String otp) {
        return twoFactorOtpRepository.findByOtp(otp);

    }
}
