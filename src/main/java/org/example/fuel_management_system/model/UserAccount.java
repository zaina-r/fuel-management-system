package org.example.fuel_management_system.model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "UserAccount")
public class UserAccount implements UserDetails {
    @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Id")
    private int userId;

    @Column(name="NIC") private String NIC;

    @Column(name = "TelNo")
    private String telno;

    @Column(name="FirstName")
    private String firstname;

    @Column(name = "LastName")
    private String lastname;

    @Column(name="UserName")
    private String username;

    @Column(name="Password")
    private String password;

    @Enumerated(value=EnumType.STRING)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
