package org.example.fuel_management_system.service;

import org.apache.catalina.User;
import org.example.fuel_management_system.Repository.UserAccountRepository;
import org.example.fuel_management_system.model.AuthenticationResponse;
import org.example.fuel_management_system.model.Role;
import org.example.fuel_management_system.model.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthenticateService {

    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    @Autowired
    private final UserAccountRepository userAccountRepository;

    public AuthenticateService( UserAccountRepository userAccountRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userAccountRepository = userAccountRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }
    public AuthenticationResponse register(UserAccount request){
        UserAccount userAccount=new UserAccount();

        userAccount.setNIC(request.getNIC());
        userAccount.setTelno(request.getTelno());
        userAccount.setFirstname(request.getFirstname());
        userAccount.setLastname(request.getLastname());
        userAccount.setUsername(request.getUsername());
        userAccount.setPassword(passwordEncoder.encode(request.getPassword()));

        Role role = request.getRole();
        if (role == Role.FUELSTATION_OWNER) {
            if (request.getStationid() == 0 || request.getAuthfile() == null) {
                throw new IllegalArgumentException("Station ID and Auth File are required for FUELSTATION_OWNER!");
            }
            userAccount.setRole(Role.FUELSTATION_OWNER);
            userAccount.setStationid(request.getStationid());
            userAccount.setAuthfile(request.getAuthfile());
        } else if (role == Role.VEHICLE_OWNER) {
            userAccount.setRole(Role.VEHICLE_OWNER);
            userAccount.setStationid(0);
            userAccount.setAuthfile(null);
        } else {
            throw new IllegalArgumentException("Invalid role specified!");
        }

        if (userAccountRepository.existsByUsername(request.getUsername())) {
            throw new IllegalArgumentException("Username already exists!");
        }

        if(request.getPassword()==null || request.getPassword().length()<=4) {
            throw new IllegalArgumentException("Password Charter Minimum 5 charters!");
        }
        userAccount.setPassword(passwordEncoder.encode(request.getPassword()));

        userAccount=userAccountRepository.save(userAccount);
        String token=jwtService.generateToken(userAccount);
  
        return new AuthenticationResponse(token);


    }
    public AuthenticationResponse authenticate(UserAccount request){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()));
        String token=jwtService.generateToken(userAccountRepository.findByUsername(request.getUsername()).orElseThrow());

        UserAccount userAccount=userAccountRepository.findByUsername(request.getUsername()).orElseThrow();
         token=jwtService.generateToken(userAccount);
        return new AuthenticationResponse(token);
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
}
