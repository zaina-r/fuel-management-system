package org.example.fuel_management_system.controller;

import org.example.fuel_management_system.OtpGenerator.GenerateOtp;
import org.example.fuel_management_system.Request.ResetPasswordRequest;
import org.example.fuel_management_system.Response.ApiResponse;
import org.example.fuel_management_system.Response.AuthenticationResponse;
import org.example.fuel_management_system.enumpackage.Role;
import org.example.fuel_management_system.model.ForgotPasswordToken;
import org.example.fuel_management_system.model.MailStructure;
import org.example.fuel_management_system.model.TwoFactorOtp;
import org.example.fuel_management_system.model.UserAccount;
import org.example.fuel_management_system.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api")
public class AuthenticationController {

    @Autowired
    private  AuthenticateService authenticateService;
    @Autowired
    private TwoFactorOtpServiceImpl twoFactorOtpService;
    @Autowired
    private ForgotPasswordService forgotPasswordService;
    @Autowired
    private MailService mailService;



@PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody UserAccount request) throws Exception {
        try{
            return ResponseEntity.ok(authenticateService.register(request));

        } catch (Exception e) {
              AuthenticationResponse authenticationResponse=new AuthenticationResponse();
              authenticationResponse.setMessage("The user not registered successfully");
              return new ResponseEntity<>(authenticationResponse,HttpStatus.BAD_REQUEST);
        }
}

@PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody UserAccount request) throws Exception {
    try{
        return ResponseEntity.ok(authenticateService.authenticate(request));

    } catch (Exception e) {
        AuthenticationResponse authenticationResponse=new AuthenticationResponse();
        authenticationResponse.setMessage("The user not login successfully");
        return new ResponseEntity<>(authenticationResponse,HttpStatus.BAD_REQUEST);
    }
    }


@GetMapping("/allaccounts")
    public ResponseEntity<List<UserAccount>>getAllAccounts() throws Exception {
    try {
        return ResponseEntity.ok(authenticateService.getAllAccounts());
    }catch (Exception e){
        throw new Exception("The Accounts not get proper");
    }
    }

@GetMapping("/account/{id}")
public ResponseEntity<UserAccount> getAccountById(@PathVariable("id") int userId){

        return ResponseEntity.ok(authenticateService.getAccountById(userId));
}

@GetMapping("/by-role")
public ResponseEntity<List<UserAccount>> getUsersByRole(@RequestParam Role role) {
    return ResponseEntity.ok(authenticateService.getUsersByRole(role));
}

@PostMapping("/two_factor/otp/{otp}")
public ResponseEntity<AuthenticationResponse> verifySigningOtp(
            @PathVariable String otp,@RequestParam String id
    ) throws Exception {
        TwoFactorOtp twoFactorOTP=twoFactorOtpService.findById(id);
        if(twoFactorOtpService.verifyTwoFactorOtp(twoFactorOTP,otp)){
        AuthenticationResponse authResponse=new AuthenticationResponse();
        authResponse.setToken(twoFactorOTP.getJwt());
        authResponse.setMessage("Two factor Authentication Successfully");
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
             }
            throw new Exception("Invalid otp");
    }


    @PatchMapping("/users/reset-password/verify-otp")
    public ResponseEntity<ApiResponse> resetPassword(
            @RequestParam String id,
            @RequestBody ResetPasswordRequest request
            ) throws Exception {

        ForgotPasswordToken forgotPasswordToken=forgotPasswordService.findById(id);
        boolean isVerified=
                forgotPasswordToken.getOtp().equals(request.getOtp());
        if (isVerified){
            authenticateService.updatePassword(forgotPasswordToken.getUserAccount(),request.getPassword());
            ApiResponse apiResponse=new ApiResponse();
            apiResponse.setMessage("Password updated successfully");
            return new ResponseEntity<>(apiResponse,HttpStatus.OK);
        }
        throw new Exception("wrong otp");
    }



    @PostMapping("/users/reset-password/send-otp")
    public ResponseEntity<ApiResponse> sendForgetPasswordOtp(
            @RequestParam String email
    ) throws Exception {
    UserAccount user=authenticateService.getUserByUsername(email);
        String otp= GenerateOtp.generateOtp();
        UUID uuid= UUID.randomUUID();
        String id=uuid.toString();
        ForgotPasswordToken token=forgotPasswordService.findByUser(user.getUserId());

        if(token==null){
            token=forgotPasswordService.createToken(user,id,otp,email);
        }
        MailStructure mailStructure=new MailStructure();
        mailStructure.setSubject("Your forgot Password Verification Code");
        mailStructure.setMessage("Your verification code is" + token.getOtp());
        mailService.sendMail(email,mailStructure);
        ApiResponse authResponse=new ApiResponse();
        authResponse.setMessage("Password reset otp sent successfully");
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }







}
