package com.prakshit.jobportalsystem.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
;

@Entity
@Table(name = "employer_profiles")
@Getter
@Setter
public class EmployerProfile extends BaseModel {

    @Column(name = "company_name", nullable = false, length = 150)
    private String companyName;

    @Column(name = "company_website", nullable = false, length = 255)
    private String companyWebsite;

    @Column(name = "contact_number", nullable = false, length = 20)
    private String contactNumber;

    @OneToOne(fetch = FetchType.LAZY, optional = false)     //one to one relation with user and employerProfile
    @JoinColumn(name = "user_id",referencedColumnName = "id",columnDefinition = "BINARY(16)",
            nullable = false ,unique = true ,               //explicitly saying to join column user_id,
    foreignKey = @ForeignKey(name = "fk_employer_user"))
    private User user;


}
