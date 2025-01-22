package org.example.fuel_management_system.Repository;


import org.example.fuel_management_system.model.TwoFactorOtp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TwoFactorOtpRepository extends JpaRepository<TwoFactorOtp,String> {

     TwoFactorOtp findByUser_UserId(int userId);
     TwoFactorOtp findByOtp(String otp);







}