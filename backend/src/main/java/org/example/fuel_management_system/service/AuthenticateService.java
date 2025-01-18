package org.example.fuel_management_system.service;


import org.example.fuel_management_system.Dto.Response;
import org.example.fuel_management_system.Dto.UserAccountDto;
import org.example.fuel_management_system.OtpGenerator.GenerateOtp;
import org.example.fuel_management_system.Repository.UserAccountRepository;
import org.example.fuel_management_system.enumpackage.Role;
import org.example.fuel_management_system.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthenticateService {

    @Autowired
    private  PasswordEncoder passwordEncoder;
    @Autowired
    private  JwtService jwtService;
    @Autowired
    private  AuthenticationManager authenticationManager;
    @Autowired
    private  UserAccountRepository userAccountRepository;
    @Autowired
    private TwoFactorOtpServiceImpl twoFactorOtpService;
    @Autowired
    private MailService mailService;
    @Autowired
    private VerificationCodeService verificationCodeService;
    @Autowired
    private ExistingStationsServiceImpl existingStationsService;



    public Response register(UserAccount request){

        Response response=new Response();
        try{
            if(request.getRole()==null||request.getRole().equals(null)){
                request.setRole(Role.VEHICLE_OWNER);
            }
            if (userAccountRepository.existsByUsername(request.getUsername())) {
                throw new IllegalArgumentException("Username already exists!");
            }
           request.setPassword(passwordEncoder.encode(request.getPassword()));
            UserAccount savedUser=userAccountRepository.save(request);
            //UserAccountDto userAccountDto=


        }

        UserAccount userAccount=new UserAccount();
        userAccount.setNIC(request.getNIC());
        userAccount.setTelno(request.getTelno());
        userAccount.setFirstname(request.getFirstname());
        userAccount.setLastname(request.getLastname());
        userAccount.setUsername(request.getUsername());


        Role role = request.getRole();

        if (role == Role.FUELSTATION_OWNER) {
            userAccount.setRole(Role.FUELSTATION_OWNER);
        } else if (role == Role.ADMIN) {
            userAccount.setRole(Role.ADMIN);
        }




        if(request.getPassword()==null || request.getPassword().length()<=4) {
            throw new IllegalArgumentException("Password Charter Minimum 5 charters!");
        }

        userAccount.setPassword(passwordEncoder.encode(request.getPassword()));
        userAccount=userAccountRepository.save(userAccount);
        String token=jwtService.generateToken(userAccount);
        if(role==Role.FUELSTATION_OWNER && request.getLicenseNumber()!=null){
            String licenseNumber = request.getLicenseNumber();

            boolean isLicenseValid = existingStationsService.isLicenseNumberValid(licenseNumber);
            if(isLicenseValid){
                userAccount.setLicenseNumber(licenseNumber);
            }
            else{
                throw new IllegalArgumentException("License number is not valid");
            }
 }
        userAccountRepository.save(userAccount);
  
        return new AuthenticationResponse(token,"The account has been registered successfully");
    }



    public AuthenticationResponse authenticate(UserAccount request){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()));
        UserAccount userAccount=userAccountRepository.findByUsername(request.getUsername()).orElseThrow();
        String token=jwtService.generateToken(userAccount);
        UserAccount userAccount1=getUserByUsername(request.getUsername());
        if(userAccount1.getRole().equals(Role.FUELSTATION_OWNER)){
            String otp= GenerateOtp.generateOtp();
            TwoFactorOtp oldTwoFactorOtp=twoFactorOtpService.findByUser(userAccount1.getUserId());
            if(oldTwoFactorOtp!=null){
                twoFactorOtpService.deleteTwoFactorOtp(oldTwoFactorOtp);
            }
            TwoFactorOtp newTwoFactorOtp=twoFactorOtpService.createTwoFactorOtp(userAccount1,otp,token);
            MailStructure mailStructure=new MailStructure();
            mailStructure.setSubject("Your two factor  email verification");
            mailStructure.setMessage("Your email verification code is  "+otp);
            mailService.sendMail(userAccount1.getUsername(),mailStructure);


        }
        return new AuthenticationResponse(token,"The account has been login successfully");
    }


    public List<UserAccount> getAllAccounts() {
        return userAccountRepository.findAll();
    }

    public UserAccount getAccountById(int userId) {
        return userAccountRepository.findById(userId).orElseThrow(()->new UsernameNotFoundException("User Not Found"));
    }

    public List<UserAccount> getUsersByRole(Role role) {
        return  userAccountRepository.findByRole(role);
    }

    public UserAccount getUserByUsername(String username){
        Optional<UserAccount> userAccount=userAccountRepository.findByUsername(username);
        if(userAccount.isPresent()){
            return userAccount.get();
        }
        return null;
    }

    public UserAccount findByUserProfileByJwt(String jwt) {
        String email=jwtService.extractUsername(jwt);
        UserAccount userAccount=userAccountRepository.findByUsername(email).get();
        return userAccount;
    }

    public UserAccount updatePassword(UserAccount user, String newPassword) {
        user.setPassword(passwordEncoder.encode(newPassword));
        return userAccountRepository.save(user);
    }








}
