package org.example.fuel_management_system.service;
import org.example.fuel_management_system.DTO.Response;
import org.example.fuel_management_system.DTO.UserAccountDto;
import org.example.fuel_management_system.OtpGenerator.GenerateOtp;
import org.example.fuel_management_system.Repository.UserAccountRepository;
import org.example.fuel_management_system.Request.ResetPasswordRequest;
import org.example.fuel_management_system.enumpackage.Role;
import org.example.fuel_management_system.exception.FuelException;
import org.example.fuel_management_system.model.*;
import org.example.fuel_management_system.utilities.MapUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthenticateService {


    private  PasswordEncoder passwordEncoder;
    private  JwtService jwtService;
    private  AuthenticationManager authenticationManager;
    private  UserAccountRepository userAccountRepository;
    private TwoFactorOtpServiceImpl twoFactorOtpService;
    private MailService mailService;
    private ExistingStationsServiceImpl existingStationsService;
    private ForgotPasswordService forgotPasswordService;

    public AuthenticateService(PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager,
                               UserAccountRepository userAccountRepository, TwoFactorOtpServiceImpl twoFactorOtpService,
                               ExistingStationsServiceImpl existingStationsService, MailService mailService, ForgotPasswordService forgotPasswordService) {
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.userAccountRepository = userAccountRepository;
        this.twoFactorOtpService = twoFactorOtpService;
        this.existingStationsService = existingStationsService;
        this.mailService = mailService;
        this.forgotPasswordService = forgotPasswordService;
    }

    public Response register(UserAccount request){

        Response response=new Response();
        try{
            if(request.getRole()==null){
                request.setRole(Role.VEHICLE_OWNER);
            }
            if (userAccountRepository.existsByUsername(request.getUsername())) {
                throw new IllegalArgumentException("Username already exists!");
            }

            if(request.getPassword()==null || request.getPassword().length()<=4) {
                throw new IllegalArgumentException("Password Charter Minimum 5 charters!");
            }
            request.setPassword(passwordEncoder.encode(request.getPassword()));

            Role role = request.getRole();
            if (role == Role.FUELSTATION_OWNER) {
                boolean isLicenseValid = existingStationsService.isLicenseNumberValid(request.getLicenseNumber());

                if(isLicenseValid){
                    request.setLicenseNumber(request.getLicenseNumber());
                }
                else{
                    throw new IllegalArgumentException("License number is not valid");
                }


            }else if(role == Role.ADMIN){
                request.setRole(Role.ADMIN);
            }


            UserAccount savedUser=userAccountRepository.save(request);
            UserAccountDto userAccountDto= MapUtils.mapUserEntityToUserDTO(savedUser);
            String token=jwtService.generateToken(savedUser);
            response.setToken(token);
            response.setStatusCode(200);
            response.setUserAccountDto(userAccountDto);
            response.setExpirationTime(jwtService.extractExpiration(token));

        }catch (FuelException | IllegalArgumentException e){
            response.setStatusCode(400);
            response.setMessage(e.getMessage());
        }

        catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error Occurred During USer Registration " + e.getMessage());


        }
        return response;
    }

    public Response authenticate(UserAccount request){
        Response response = new Response();
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()));
            UserAccount userAccount = userAccountRepository.findByUsername(request.getUsername())
                    .orElseThrow(() -> new IllegalArgumentException("User not found!"));
            String token = jwtService.generateToken(userAccount);
            Date expirationTime = jwtService.extractExpiration(token);
            UserAccountDto userAccountDto = MapUtils.mapUserEntityToUserDTO(userAccount);
            if (userAccount.getRole().equals(Role.FUELSTATION_OWNER)) {

                 String otp=GenerateOtp.generateOtp();
                TwoFactorOtp oldTwoFactorOtp = twoFactorOtpService.findByUser(userAccount.getUserId());
                if (oldTwoFactorOtp != null) {
                    twoFactorOtpService.deleteTwoFactorOtp(oldTwoFactorOtp);
                }

                TwoFactorOtp newTwoFactorOtp = twoFactorOtpService.createTwoFactorOtp(userAccount, otp, token);

                MailStructure mailStructure = new MailStructure();
                mailStructure.setSubject("Your Two-Factor Email Verification");
                mailStructure.setMessage("Your email verification code is " + otp);
                mailService.sendMail(userAccount.getUsername(), mailStructure);

                response.setMessage("Two-factor authentication OTP sent to your email.");
            } else {
                response.setMessage("The account has been logged in successfully.");
            }


            response.setStatusCode(200);
            response.setToken(token);
            response.setRole(userAccount.getRole());
            response.setExpirationTime(expirationTime);
            response.setUserAccountDto(userAccountDto);





        }catch (IllegalArgumentException e) {
            response.setStatusCode(400);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error occurred during authentication: " + e.getMessage());
        }

        return response;
    }

    public Response getAllAccounts() {

        Response response=new Response();
        try {
            List<UserAccount>userAccountList=userAccountRepository.findAll();
            List<UserAccountDto>userAccountDtoList=MapUtils.mapUserListEntityToUserListDTO(userAccountList);
            response.setUserAccountDtoList(userAccountDtoList);
            response.setStatusCode(200);
            response.setMessage("successful");
        }catch (Exception e){
            response.setStatusCode(500);
            response.setMessage("Error getting all users " + e.getMessage());
        }
        return response;

    }

    public Response getAccountById(int userId) {
        Response response = new Response();

        try {
            UserAccount userAccount = userAccountRepository.findById(userId).orElseThrow(()->new UsernameNotFoundException("User not found"));
            if (userAccount == null) {
                response.setStatusCode(404);
                response.setMessage("User account does not exist");
            }else {
                UserAccountDto userDTO = MapUtils.mapUserEntityToUserDTO(userAccount);
                response.setStatusCode(200);
                response.setMessage("Account retrieved successfully");
                response.setUserAccountDto(userDTO);
            }

        } catch (FuelException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());

        } catch (Exception e) {

            response.setStatusCode(500);
            response.setMessage("Error getting all users " + e.getMessage());
        }
        return response;
    }

    public Response getUsersByRole(Role role) {
        Response response=new Response();
        try {
            List<UserAccount> userAccounts = userAccountRepository.findByRole(role);
            if (userAccounts == null || userAccounts.isEmpty()) {
                response.setStatusCode(404);
                response.setMessage("No users found for the given role");
            }
            else {
                response.setStatusCode(200);
                response.setMessage("Users retrieved successfully");
                response.setUserAccountDtoList(MapUtils.mapUserListEntityToUserListDTO(userAccounts));
            }
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error retrieving users by role: " + e.getMessage());

        }
        return response;

    }

    public Response verifySigningOtp(String otp,int userId){
        Response response = new Response();

        try {

            TwoFactorOtp twoFactorOtp = twoFactorOtpService.findByUser(userId);
            if (twoFactorOtp == null) {
                response.setStatusCode(404);
                response.setMessage("OTP not found for the given user.");
                return response;

            }


            boolean isOtpValid = twoFactorOtpService.verifyTwoFactorOtp(twoFactorOtp, otp);
            if (!isOtpValid) {
                response.setStatusCode(400);
                response.setMessage("Invalid or expired OTP.");
                return response;
            }


            UserAccount userAccount = userAccountRepository.findById(userId).orElseThrow(()->new UsernameNotFoundException("User not found"));
            String jwt = jwtService.generateToken(userAccount);

            response.setToken(jwt);
            response.setMessage("Two-factor authentication successfully completed.");
            response.setStatusCode(200);
            response.setUserAccountDto(MapUtils.mapUserEntityToUserDTO(userAccount));

        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error occurred during OTP verification: " + e.getMessage());

        }
        return response;
    }

    public Response sendForgetPasswordOtp(String email){
        Response response=new Response();
        try{
            UserAccount userAccount=getUserByUsername(email);
            if(userAccount==null){
                response.setStatusCode(404);
                response.setMessage("User not found with provided email.");
            }
            String otp = GenerateOtp.generateOtp();
            UUID uuid = UUID.randomUUID();
            String id = uuid.toString();
            ForgotPasswordToken token =forgotPasswordService.findByUser(userAccount.getUserId());

            if (token == null) {
                token = forgotPasswordService.createToken(userAccount, id, otp, email);
            }

            MailStructure mailStructure = new MailStructure();
            mailStructure.setSubject("Your forgot Password Verification Code");
            mailStructure.setMessage("Your verification code is " + token.getOtp());
            mailService.sendMail(email, mailStructure);

            response.setStatusCode(200);
            response.setMessage("Password reset OTP sent successfully");



        }catch(Exception e){
            response.setStatusCode(500);
            response.setMessage("Error occurred during OTP generation or email sending: " + e.getMessage());


        }
        return response;
    }

    public UserAccount getUserByUsername(String username){
        Optional<UserAccount> userAccount=userAccountRepository.findByUsername(username);
        if(userAccount.isPresent()){
            return userAccount.get();
        }
        return null;
    }


    public UserAccount updatePassword(UserAccount user, String newPassword) {
        user.setPassword(passwordEncoder.encode(newPassword));
        return userAccountRepository.save(user);
    }

    public Response verifyOtp(String email, String otp){
        Response response=new Response();
        try {

            UserAccount userAccount=getUserByUsername(email);

            ForgotPasswordToken forgotPasswordToken=forgotPasswordService.findByUser(userAccount.getUserId());
            if (forgotPasswordToken == null) {
                response.setStatusCode(404);
                response.setMessage("Invalid or expired token.");
            }
            boolean isVerified = forgotPasswordToken.getOtp().equals(otp);

            if (isVerified) {
                response.setStatusCode(200);
                response.setMessage("Otp can be verified successfully");

            }
            else {
                response.setStatusCode(400);
                response.setMessage("Wrong OTP provided.");

            }
        }catch (Exception e){
            response.setStatusCode(500);
            response.setMessage("Error occurred during password reset: " + e.getMessage());

        }
        return response;
    }

    public Response resetPassword(String email, ResetPasswordRequest resetPasswordRequest){
        System.out.println(resetPasswordRequest.getOtp()+resetPasswordRequest.getPassword());
        Response response=new Response();
        try {

            UserAccount userAccount=getUserByUsername(email);

            ForgotPasswordToken forgotPasswordToken=forgotPasswordService.findByUser(userAccount.getUserId());
            if (forgotPasswordToken == null) {
                response.setStatusCode(404);
                response.setMessage("Invalid or expired token.");
            }
            boolean isVerified = forgotPasswordToken.getOtp().equals(resetPasswordRequest.getOtp());

            if (isVerified) {
                updatePassword(forgotPasswordToken.getUserAccount(), resetPasswordRequest.getPassword());
                response.setStatusCode(200);
                response.setMessage("Password can be updated successfully!");

            }
            else {
                response.setStatusCode(400);
                response.setMessage("Wrong OTP provided.");

            }
        }catch (Exception e){
            response.setStatusCode(500);
            response.setMessage("Error occurred during password reset: " + e.getMessage());

        }
        return response;
    }










}
