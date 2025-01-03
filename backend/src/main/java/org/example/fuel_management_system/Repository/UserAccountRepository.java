package org.example.fuel_management_system.Repository;

import org.example.fuel_management_system.model.Role;
import org.example.fuel_management_system.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserAccountRepository extends JpaRepository<UserAccount,Integer> {
    Optional<UserAccount> findByUsername(String username);

    boolean existsByUsername(String username);

List<UserAccount> findByRole(Role role);

}











