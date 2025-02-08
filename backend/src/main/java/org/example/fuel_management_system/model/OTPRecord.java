package org.example.fuel_management_system.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class OTPRecord {

    @Id
    private String phoneNumber;
    private String otp;

    public OTPRecord() {}

    public OTPRecord(String phoneNumber, String otp) {
        this.phoneNumber = phoneNumber;
        this.otp = otp;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }
}
