package org.example.fuel_management_system.OtpGenerator;

import org.example.fuel_management_system.Repository.UserAccountRepository;
import org.example.fuel_management_system.model.UserAccount;
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
    @Autowired
    private UserAccountRepository userAccountRepository;

    @Scheduled(fixedRate = 24 * 60 *60* 1000)
    public void regenerateOtps() {
        try {
            // Fetch all stations with existing OTPs
            List<VerificationCode> stationsWithOtps = verificationCodeService.getAllStationsWithOtps();

            for (VerificationCode verificationCode : stationsWithOtps) {
                if (verificationCode.getStation() != null && verificationCode.getStation().getId() != 0) {
                    // Generate a new OTP for each station
                    String otp = GenerateOtp.generateOtp(); // Ensure this generates unique values every time

                    // Fetch the user associated with the station
                    UserAccount user = userAccountRepository.findByLicenseNumber(verificationCode.getStation().getLicenseNumber());

                    if (user != null) {
                        // Update the OTP for the station
                        verificationCodeService.generateOtpForStation(verificationCode.getStation(), otp, user.getUsername());
                    } else {
                        System.err.println("No user found for license number: " + verificationCode.getStation().getLicenseNumber());
                    }
                } else {
                    // Log a warning if station or station ID is invalid
                    System.err.println("Invalid station or station ID encountered for verification code: " + verificationCode.getId());
                }
            }
        } catch (Exception e) {
            // Log any unexpected exceptions
            System.err.println("An error occurred during OTP regeneration: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

