package org.example.fuel_management_system.model;

import jakarta.persistence.*;

@Entity
@Table(name = "UserAccount")
public class UserAccount {
    @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Id")
    private int UserId;

    @Column(name="NIC") private String NI;

    @Column(name = "TelNo")
    private String TelNo;

    @Column(name="FirstName")
    private String FirstName;

    @Column(name = "LastName")
    private String LastName;

    @Column(name="UserName")
    private String UserName;

    @Column(name="Password")
    private String Password;

    @Enumerated(value=EnumType.STRING)
    private Role Role;

}
