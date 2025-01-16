package org.example.fuel_management_system.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class TwoFactorOtp {
    @Id
    private String id;
    private String otp;
    @OneToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private UserAccount user;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String jwt;

    public TwoFactorOtp() {
    }

    public TwoFactorOtp(String id, String otp, UserAccount user, String jwt) {
        this.id = id;
        this.otp = otp;
        this.user = user;
        this.jwt = jwt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public UserAccount getUser() {
        return user;
    }

    public void setUser(UserAccount user) {
        this.user = user;
    }


}
