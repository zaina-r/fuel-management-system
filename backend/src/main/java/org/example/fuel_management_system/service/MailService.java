package org.example.fuel_management_system.service;

import org.example.fuel_management_system.model.MailStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public interface MailService {


  void sendMail(String mail, MailStructure mailStructure);
}
