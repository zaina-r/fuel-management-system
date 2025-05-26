package org.example.fuel_management_system.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.example.fuel_management_system.enumpackage.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.File;
import java.util.ArrayList;
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

    @Lob
    private byte[] imageData;

    private String imageType;

    private String imageName;

    @Enumerated(EnumType.STRING)
    private Role role;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<VehicleVerification>vehicleVerifications=new ArrayList<>();

    @OneToMany(mappedBy = "userAccount",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<FuelTransition>fuelTransitions=new ArrayList<>();

    private String licenseNumber;

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getNIC() {
        return NIC;
    }

    public void setNIC(String NIC) {
        this.NIC = NIC;
    }

    public String getTelno() {
        return telno;
    }

    public void setTelno(String telno) {
        this.telno = telno;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<VehicleVerification> getVehicleVerifications() {
        return vehicleVerifications;
    }

    public void setVehicleVerifications(List<VehicleVerification> vehicleVerifications) {
        this.vehicleVerifications = vehicleVerifications;
    }

    public List<FuelTransition> getFuelTransitions() {
        return fuelTransitions;
    }

    public void setFuelTransitions(List<FuelTransition> fuelTransitions) {
        this.fuelTransitions = fuelTransitions;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
}
