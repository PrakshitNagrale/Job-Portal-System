package com.prakshit.jobportalsystem.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "job")
@Getter
@Setter
public class Job extends BaseModel{

    @Column(name = "job_title",nullable = false,length = 200)
    private String jobTitle;

    @Column(name = "job_description",nullable = false,columnDefinition = "TEXT")    //columnDefinition = "TEXT" -> not "length=500" because varchar(500) would be small for description
    private String jobDescription;                                                                                // so for long desc we use TEXT


    @Column(name = "location",nullable = false,length = 150)
    private String location;

    @Column(name = "min_experience",nullable = false)
    private int minExperience;

    @Column(name = "max_experience", nullable = false)
    private int maxExperience;

    @Column(name = "salary",nullable = true)  // nullable = true -> salary can be null(optional)
    private BigDecimal salary;                  //BigDecimal instead of double because of precision issue with double

    @ElementCollection           // @ElementCollection always creates a new table to store the collection values
    @CollectionTable(            //@CollectionTable  control the table name, join column, and constraints.
            name = "job_skills",
            joinColumns = @JoinColumn(name = "job_id")
    )
    @Column(name = "skills",nullable = false)
    private List<String> skillsRequired = new ArrayList<>();  // we are initializing ArrayList to handle NullPointerException, if someone adds skills other than RequestDto validation

    @Enumerated(EnumType.STRING)                     // to store enum values as "text" in db
    @Column(name = "job_type",nullable = false,length = 50)
    private JobType jobType;

    @Column(name = "last_date_to_apply",nullable = false)
    private LocalDateTime lastDateToApply;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn (
            name = "employer_id",
            referencedColumnName = "id",
            columnDefinition = "BINARY(16)",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_job_employer")   // every job is related to one employer
    )
    private EmployerProfile employerProfile;
}
