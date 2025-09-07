package com.prakshit.jobportalsystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "applicant_profiles")
@Getter
@Setter
public class ApplicantProfile extends BaseModel{

    @Column(name = "full_name",nullable = false,length = 150)
    private String name;

    @Column(name = "education", length = 255)
    private String education;

    @ElementCollection                     // @ElementCollection always creates a new table to store the collection values
    @CollectionTable(                       //@CollectionTable  control the table name, join column, and constraints.
            name = "applicant_profile_skills",
            joinColumns = @JoinColumn(name = "applicant_id")
    )
    @Column(name = "skills",nullable = false, length = 100)
    private List<String> skills;


    @Column(name = "experience_years",nullable = false)
    private int experienceYears;

    @Column(name = "experience_details",length = 2000)
    private String experienceDetails;

    @Column(name = "resume_url",nullable = false, length = 1024)
    private String resumeUrl;

    @Column(name = "phone_number", nullable = false,length = 20,unique = true)
    private String phoneNumber;

   @OneToOne(fetch = FetchType.LAZY , optional = false)         //OneToOne relation with user and applicantProfile ,optional = false → enforces that every applicant profile must belong to a user.
   @JoinColumn(                                             //explicitly saying to join column user_id,which is UUID
           name = "user_id",
           referencedColumnName = "id",             //makes sure the FK column matches User.id type (UUID stored as binary).
           columnDefinition = "BINARY(16)",
           nullable = false, unique = true,
          foreignKey = @ForeignKey(name = "fk_applicant_user"))
    private User user;


}
