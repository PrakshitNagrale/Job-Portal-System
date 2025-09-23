package com.prakshit.jobportalsystem.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name="users")
@Getter
@Setter
public class User extends BaseModel{


    @Column(nullable = false)
    private String name;

    @Column(unique = true , nullable = false)
    private String email;               //email is unique, user will login with email & password

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)  //to store enum values as "text" in db
    @Column(nullable = false)
    private UserRole  userRole;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL,fetch = FetchType.LAZY)   // it will not create column ,mappedBy tells that it is already mapped
    private EmployerProfile employerProfile;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)   // it will not create column ,mappedBy tells that it is already mapped
    private ApplicantProfile applicantProfile;

}


/*
cascade = CascadeType.ALL → if  save/delete a User, Hibernate will also save/delete the row from ApplicantProfile/EmployerProfile automatically.

fetch = FetchType.LAZY → applicantProfile is loaded only when you explicitly call user.getApplicantProfile().
 */
