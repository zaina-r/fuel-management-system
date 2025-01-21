package org.example.fuel_management_system.Repository;

import org.example.fuel_management_system.model.ForgotPasswordToken;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ForgotPasswordRepository extends JpaRepository<ForgotPasswordToken,String> {


    ForgotPasswordToken findByUserAccount_UserId(int userAccountUserId);


}
