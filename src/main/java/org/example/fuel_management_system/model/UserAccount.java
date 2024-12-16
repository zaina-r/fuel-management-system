package org.example.fuel_management_system.model;

import jakarta.persistence.*;

@Entity
@Table(name = "UserAccount")
public class UserAccount {
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

}
