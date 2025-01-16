package org.example.fuel_management_system.service;

import org.apache.catalina.User;
import org.example.fuel_management_system.OtpGenerator.GenerateOtp;
import org.example.fuel_management_system.Repository.VerificationCodeRepository;
import org.example.fuel_management_system.enumpackage.Role;
import org.example.fuel_management_system.model.MailStructure;
import org.example.fuel_management_system.model.UserAccount;
import org.example.fuel_management_system.model.VerificationCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;

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

    public VerificationCode generateOtp(UserAccount userAccount,String jwt,String otp) {
        VerificationCode existingOtp = verificationCodeRepository.findByUser_UserId(userAccount.getUserId());
        LocalDateTime expirationTime = LocalDateTime.now().plusHours(24);
        if (existingOtp != null && existingOtp.getExpirationTime().isAfter(LocalDateTime.now())) {
            sendOtp(existingOtp.getOtp(),existingOtp.getUser().getUsername());
            return existingOtp;
        }
        else if(existingOtp!=null && existingOtp.getExpirationTime().isBefore(LocalDateTime.now())){
            verificationCodeRepository.delete(existingOtp);


            VerificationCode verificationCode = new VerificationCode();
            UUID uuid = UUID.randomUUID();
            String id = uuid.toString();
            verificationCode.setId(id);
            verificationCode.setOtp(otp);
            verificationCode.setExpirationTime(expirationTime);
            verificationCode.setUser(userAccount);

            sendOtp(verificationCode.getOtp(),verificationCode.getUser().getUsername());

            return verificationCodeRepository.save(verificationCode);
        }
        else {



            // Save the OTP in the database
            VerificationCode verificationCode = new VerificationCode();
            UUID uuid = UUID.randomUUID();
            String id = uuid.toString();
            verificationCode.setId(id);
            verificationCode.setOtp(otp);
            verificationCode.setExpirationTime(expirationTime);
            verificationCode.setUser(userAccount);
            sendOtp(verificationCode.getOtp(),verificationCode.getUser().getUsername());



            return verificationCodeRepository.save(verificationCode);
        }

    }


    public void sendOtp(String otp, String userContact) {
        MailStructure mailStructure=new MailStructure();
        mailStructure.setSubject("Your verification Code");
        mailStructure.setMessage("Your email verification code is  "+ otp);
        mailService.sendMail(userContact,mailStructure);
    }
    public VerificationCode getVerificationCode(String id) throws Exception {
        return verificationCodeRepository.findById(id).orElseThrow(()->new Exception("The id not found"));
    }
    public List<VerificationCode> getAllOwners() {
        return verificationCodeRepository.findAll().stream()
                .filter(otp -> otp.getUser().getRole().equals(Role.FUELSTATION_OWNER))
                .collect(Collectors.toList());
    }



}
