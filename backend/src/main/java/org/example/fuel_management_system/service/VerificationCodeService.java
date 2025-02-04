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
public interface VerificationCodeService {

 VerificationCode generateOtpForStation(Station station, String otp,String email) ;

 void sendOtp(String otp, String userContact) ;
 VerificationCode getVerificationCode(String id) throws Exception ;

 List<VerificationCode> getAllStationsWithOtps();
}
