package org.example.fuel_management_system.controller;
import org.example.fuel_management_system.DTO.Response;
import org.example.fuel_management_system.Request.ResetPasswordRequest;
import org.example.fuel_management_system.Request.UserRequest;
import org.example.fuel_management_system.enumpackage.Role;
import org.example.fuel_management_system.model.UserAccount;
import org.example.fuel_management_system.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;




@RestController
@RequestMapping("/api")
public class AuthenticationController {


    private AuthenticateService authenticateService;

    public AuthenticationController(AuthenticateService authenticateService) {
        this.authenticateService = authenticateService;
    }


    @PostMapping("/register")
    public ResponseEntity<Response> register(@RequestBody UserAccount request) throws Exception {
        Response response = authenticateService.register(request);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<Response> login(@RequestBody UserAccount request) throws Exception {
        Response response = authenticateService.authenticate(request);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PostMapping("/two_factor/otp/{otp}")
    public ResponseEntity<Response> verifySigningOtp(@PathVariable String otp, @RequestParam int userId) throws Exception {
        Response response = authenticateService.verifySigningOtp(otp, userId);
        return ResponseEntity.status(response.getStatusCode()).body(response);


    }

    @GetMapping("/allaccounts")
    @PreAuthorize("hasAnyAuthority( 'ADMIN')")
    public ResponseEntity<Response> getAllAccounts() throws Exception {
        Response response = authenticateService.getAllAccounts();
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/account/{id}")
    @PreAuthorize("hasAnyAuthority( 'ADMIN')")
    public ResponseEntity<Response> getAccountById(@PathVariable("id") int userId) {
        Response response = authenticateService.getAccountById(userId);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatusCode()));
    }


    @GetMapping("/by-role")
    @PreAuthorize("hasAnyAuthority( 'ADMIN')")
    public ResponseEntity<Response> getUsersByRole(@RequestParam Role role) {
        Response response = authenticateService.getUsersByRole(role);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatusCode()));
    }

    @PatchMapping("/users/reset-password")
    public ResponseEntity<Response> resetPassword(@RequestParam String email, @RequestBody  ResetPasswordRequest request) throws Exception {

        Response response = authenticateService.resetPassword(email,request);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PostMapping("/users/verify-otp/{otp}")
    public ResponseEntity<Response> verifyOtp(@RequestParam String email, @PathVariable String otp) throws Exception {

        Response response = authenticateService.verifyOtp(email,otp);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PostMapping("/users/reset-password/send-otp")
    public ResponseEntity<Response> sendForgetPasswordOtp(@RequestParam String email) throws Exception {
        Response response = authenticateService.sendForgetPasswordOtp(email);
        return ResponseEntity.status(response.getStatusCode()).body(response);

    }
    @PutMapping("/users/update-details/{UserId}")

    public ResponseEntity<Response> updatePassword(@RequestBody UserRequest userRequest, @PathVariable int UserId) throws Exception {
        Response response = authenticateService.updateUserAccount(UserId,userRequest);
        return ResponseEntity.status(response.getStatusCode()).body(response);

    }
    @DeleteMapping("/admin/delete/{UserId}")
   @PreAuthorize("hasAnyAuthority( 'ADMIN')")
    public ResponseEntity<Response> deleteUser(@PathVariable int UserId) throws Exception {

           Response response=authenticateService.deleteAccount(UserId);
           return ResponseEntity.status(response.getStatusCode()).body(response);

    }


}
