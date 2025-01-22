package org.example.fuel_management_system.service;
/*
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;



import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

@Service
public class TwillioService {

    @Value("${twilio.account-sid}")
    private String accountSid;

    @Value("${twilio.auth-token}")
    private String authToken;

    @Value("${twilio.phone-number}")
    private String twilioPhoneNumber;


    @PostConstruct
    public void initTwilio() {
        Twilio.init(accountSid, authToken);
    }

    public String sendNotification(String toPhoneNumber, String messageBody) {
        try {
            if (!toPhoneNumber.startsWith("+")) {
                toPhoneNumber = "+94" + toPhoneNumber.substring(1); // Assuming UK country code
            }

            Message message = Message.creator(
                    new com.twilio.type.PhoneNumber(toPhoneNumber),
                    new com.twilio.type.PhoneNumber(twilioPhoneNumber),
                    messageBody
            ).create();
            return "Notification sent successfully. Message SID: " + message.getSid();
        } catch (Exception e) {
            return "Failed to send notification: " + e.getMessage();
        }
    }
}*/
