package org.example.fuel_management_system.service;

import org.example.fuel_management_system.Repository.UserAccountRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
@Service
public class UserAccountService implements UserDetailsService {

    private final UserAccountRepository repository;
    public UserAccountService(UserAccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return (UserDetails) repository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("user not found"));

    }
}





