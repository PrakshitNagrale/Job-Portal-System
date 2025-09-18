package com.prakshit.jobportalsystem.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "job_application",
    uniqueConstraints = @UniqueConstraint(columnNames = {"applicant_id","job_id"})) //uniqueConstraints -> This prevents the same applicant applying to the same job multiple times.
@Getter
@Setter
public class JobApplication extends BaseModel{                            //Relation between Job and applicant Profile->M:M


    @ManyToOne(fetch = FetchType.LAZY,optional = false)         //ManyToOne relationship between JobApplication and job
    @JoinColumn(                                                //explicitly saying to join column job_id,which is UUID
            name = "job_id",                                     // this is actual DB column name -> job_id
            referencedColumnName = "id",                         //makes sure the FK column matches job.id type (UUID stored as binary).
            columnDefinition = "BINARY(16)",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_job_application")
    )
    private Job job;


    @ManyToOne(fetch = FetchType.LAZY,optional = false)             //ManyToOne relationship between JobApplication and applicantProfile
    @JoinColumn(
            name = "applicant_id",              // this is actual DB column name -> applicant_id
            referencedColumnName = "id",
            columnDefinition = "BINARY(16)",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_applicant_application")
    )
    private ApplicantProfile applicantProfile;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private JobApplicationStatus jobApplicationStatus;

    @Column(length = 500,nullable = false)
    private String resumeUrl;


    @Enumerated(EnumType.STRING)
    @Column(name = "applied_via")
    private AppliedVia appliedVia;

    @Column(columnDefinition = "TEXT")
    private String coverLetter;




}
