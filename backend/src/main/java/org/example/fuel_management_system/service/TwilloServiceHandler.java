package org.example.fuel_management_system.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.example.fuel_management_system.DTO.Response;
import org.example.fuel_management_system.OtpGenerator.GenerateOtp;
import org.example.fuel_management_system.Repository.OTPRecordRepository;
import org.example.fuel_management_system.model.OTPRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TwilloServiceHandler implements TwillioService {
    @Autowired
    private OTPRecordRepository otpRecordRepository;

    @Value("${twilio.account.sid}")
    private String accountSid;

    @Value("${twilio.auth.token}")
    private String authToken;

    @Value("${twilio.phone.number}")
    private String twilioPhoneNumber;






    public Response sendNotification(String phoneNumber, String messageBody) {
        Twilio.init(accountSid, authToken);
        Response response=new Response();
        String otp= GenerateOtp.generateOtp();
        try{
            Message.creator(
                    new PhoneNumber(phoneNumber),
                    new PhoneNumber(twilioPhoneNumber),
                    messageBody
            ).create();
            OTPRecord otpRecord=new OTPRecord(phoneNumber,otp);
            otpRecordRepository.save(otpRecord);
            response.setMessage("Notifications sent Successfully!");
            response.setStatusCode(200);
        }catch (Exception e){
            response.setMessage("Failed to send Notifications: " + e.getMessage());
            response.setStatusCode(500);
        }
        return response;
    }





    public Response sendOTP(String phoneNumber) {
        Twilio.init(accountSid, authToken);
        Response response=new Response();
        String otp= GenerateOtp.generateOtp();
        try{
            Message.creator(
                    new PhoneNumber(phoneNumber),
                    new PhoneNumber(twilioPhoneNumber),
                    "Your OTP is: " + otp
            ).create();
            OTPRecord otpRecord=new OTPRecord(phoneNumber,otp);
            otpRecordRepository.save(otpRecord);
            response.setMessage("OTP Sent Successfully!");
            response.setStatusCode(200);
        }catch (Exception e){
            response.setMessage("Failed to send OTP: " + e.getMessage());
            response.setStatusCode(500);
        }
        return response;
    }

    public Response verifyOTP(String phoneNumber, String otp) {

        Twilio.init(accountSid, authToken);
        Response response = new Response();
        Optional<OTPRecord> optionalRecord = otpRecordRepository.findById(phoneNumber);
        if (optionalRecord.isPresent() && optionalRecord.get().getOtp().equals(otp)) {
            response.setMessage("OTP Verified Successfully");
            response.setStatusCode(200);
            otpRecordRepository.deleteById(phoneNumber);
        } else {
            response.setMessage("Invalid OTP. Please try again.");
            response.setStatusCode(400);
        }
        return response;
    }
}
