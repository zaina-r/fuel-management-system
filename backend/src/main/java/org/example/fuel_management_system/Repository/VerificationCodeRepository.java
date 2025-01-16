package org.example.fuel_management_system.Repository;

import org.example.fuel_management_system.model.TwoFactorOtp;
import org.example.fuel_management_system.model.VerificationCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VerificationCodeRepository extends JpaRepository<VerificationCode,String> {

    VerificationCode findByUser_UserId(int userId);

}
