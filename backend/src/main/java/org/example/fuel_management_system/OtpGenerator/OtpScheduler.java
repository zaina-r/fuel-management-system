package org.example.fuel_management_system.OtpGenerator;

import org.example.fuel_management_system.model.VerificationCode;
import org.example.fuel_management_system.service.VerificationCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OtpScheduler {

    @Autowired
    private VerificationCodeService verificationCodeService;

    @Scheduled(fixedRate = 24*60*60*1000) // 2 minutes
    public void regenerateOtps() {

String otp=GenerateOtp.generateOtp();


        List<VerificationCode> owners = verificationCodeService.getAllOwners();
        for (VerificationCode owner : owners) {
            VerificationCode verificationCode = verificationCodeService.generateOtp(owner.getUser(),owner.getJwt(),otp);

        }
    }
}
