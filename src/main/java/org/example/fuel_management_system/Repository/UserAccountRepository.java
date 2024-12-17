package org.example.fuel_management_system.Repository;

import org.example.fuel_management_system.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAccountRepository extends JpaRepository<UserAccount,Integer> {
    Optional<UserAccount> findByUsername(String username);
}











//
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//    private final UserRepository repository;
//
//    public UserDetailsServiceImpl(UserRepository repository) {
//        this.repository = repository;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return repository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("user not found"));
//    }
//}
//
