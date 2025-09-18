package com.prakshit.jobportalsystem.dto;

import com.prakshit.jobportalsystem.entity.AppliedVia;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class JobApplicationRequestDto {


    @NotNull(message = "JobI d is required")
    private UUID jobId;

    @NotNull(message = "Applicant Id is required")
    private UUID applicantId;

    private AppliedVia appliedVia;


    @NotBlank(message = "Resume Url is Required")
    @Size(max = 500,message = "Resume Url must be under 500 characters")
    private String resumeUrl;

    private String coverLetter;


}
