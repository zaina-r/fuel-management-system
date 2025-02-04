package org.example.fuel_management_system.service;

import org.example.fuel_management_system.model.MailStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailSender implements MailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendMail(String mail, MailStructure mailStructure){
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom("jjenushan550@gmail.com");
            simpleMailMessage.setSubject(mailStructure.getSubject());
            simpleMailMessage.setText(mailStructure.getMessage());
            simpleMailMessage.setTo(mail);
            mailSender.send(simpleMailMessage);
            System.out.println("Mail sent successfully to: " + mail);
        }catch (Exception e) {
            System.err.println("Error sending mail to: " + mail);
            e.printStackTrace(); // Log the exception
        }
    }
}
