package org.example.fuel_management_system.service;

import org.apache.catalina.User;
import org.example.fuel_management_system.OtpGenerator.GenerateOtp;
import org.example.fuel_management_system.Repository.VerificationCodeRepository;
import org.example.fuel_management_system.enumpackage.Role;
import org.example.fuel_management_system.model.MailStructure;
import org.example.fuel_management_system.model.Station;
import org.example.fuel_management_system.model.UserAccount;
import org.example.fuel_management_system.model.VerificationCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import java.rmi.server.UID;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class VerificationCodeService {

    @Autowired
    private VerificationCodeRepository verificationCodeRepository;
    @Autowired
    private MailService mailService;

    public VerificationCode generateOtpForStation(Station station, String otp,String email) {

        if (station == null ) {
            throw new IllegalArgumentException("Station or Station ID cannot be null");
        }

        LocalDateTime expirationTime = LocalDateTime.now().plusDays(1);

        VerificationCode existingOtp = verificationCodeRepository.findByStationId(station.getId());

        if (existingOtp != null) {
            if (existingOtp.getExpirationTime().isAfter(LocalDateTime.now())) {

                sendOtp(existingOtp.getOtp(), email);
                return existingOtp;
            } else {

                existingOtp.setOtp(otp);
                existingOtp.setExpirationTime(expirationTime);
                sendOtp(existingOtp.getOtp(), email);
                return verificationCodeRepository.save(existingOtp);
            }
        }

        VerificationCode newVerificationCode = new VerificationCode();
        newVerificationCode.setId(UUID.randomUUID().toString());
        newVerificationCode.setOtp(otp);
        newVerificationCode.setExpirationTime(expirationTime);
        newVerificationCode.setStation(station);

        sendOtp(newVerificationCode.getOtp(),email);
        return verificationCodeRepository.save(newVerificationCode);
    }



    public void sendOtp(String otp, String userContact) {
        System.out.println("Sending otp"+userContact);
        MailStructure mailStructure=new MailStructure();
        mailStructure.setSubject("Your verification Code");
        mailStructure.setMessage("Your station verification code is  "+ otp);
        mailService.sendMail(userContact,mailStructure);
    }
    public VerificationCode getVerificationCode(String id) throws Exception {
        return verificationCodeRepository.findById(id).orElseThrow(()->new Exception("The id not found"));
    }


    public List<VerificationCode> getAllStationsWithOtps() {
        return verificationCodeRepository.findAll();
    }
}
