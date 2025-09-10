package com.prakshit.jobportalsystem.dto;

import com.prakshit.jobportalsystem.entity.JobType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class JobResponseDTO {

    private UUID jobId;
    private String jobTitle;
    private String jobDescription;
    private String location;
    private int minExperience;
    private int maxExperience;
    private BigDecimal salary;
    private List<String> skillsRequired;
    private JobType jobType;
    private UUID employerId;
    private String employerName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime lastDateToApply;


}
