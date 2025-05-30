package org.example.fuel_management_system.service;
import org.example.fuel_management_system.DTO.Response;
import org.example.fuel_management_system.Request.ResetPasswordRequest;
import org.example.fuel_management_system.Request.UserRequest;
import org.example.fuel_management_system.enumpackage.Role;
import org.example.fuel_management_system.model.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Service
public interface AuthenticateService {



     Response register(UserAccount request);

     Response authenticate(UserAccount request);

    Response getAllAccounts();

     Response getAccountById(int userId);

 Response getUsersByRole(Role role);

 Response verifySigningOtp(String otp,int userId);

  Response sendForgetPasswordOtp(String email);

 UserAccount getUserByUsername(String username);

 Response updateUserAccount(int UserId, UserRequest userRequest);

UserAccount updatePassword(UserAccount user, String newPassword);

  Response verifyOtp(String email, String otp);

 Response resetPassword(String email, ResetPasswordRequest resetPasswordRequest);
 Response deleteAccount(int userId);


    Response addProfileImage(UserAccount userAccount,MultipartFile file, int userId) throws IOException;
}
