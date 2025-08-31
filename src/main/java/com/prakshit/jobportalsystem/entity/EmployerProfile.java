package com.prakshit.jobportalsystem.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "employer_profiles")
@Getter
@Setter
public class EmployerProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "company_name", nullable = false, length = 150)
    private String companyName;

    @Column(name = "company_website", nullable = false, length = 255)
    private String companyWebsite;

    @Column(name = "contact_number", nullable = false, length = 20)
    private String contactNumber;

    @OneToOne(fetch = FetchType.LAZY, optional = false)     //one to one relation with user and employerProfile
    @JoinColumn(name = "user_id",nullable = false ,unique = true ,  //explicitly saying to join column user_id
    foreignKey = @ForeignKey(name = "fk_employer_user"))
    private User user;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at",nullable = false)
    private LocalDateTime updatedAt;

}
