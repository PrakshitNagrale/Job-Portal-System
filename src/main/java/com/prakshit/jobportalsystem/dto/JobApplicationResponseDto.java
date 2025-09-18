package com.prakshit.jobportalsystem.dto;

import com.prakshit.jobportalsystem.entity.AppliedVia;
import com.prakshit.jobportalsystem.entity.JobApplicationStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class JobApplicationResponseDto {

    private UUID id;
    private UUID jobId;
    private String jobTitle;
    private UUID applicantId;
    private String applicantName;
    private JobApplicationStatus jobApplicationStatus;
    private String resumeUrl;
    private AppliedVia appliedVia;
    private String coverLetter;
    private LocalDateTime appliedOn;
    private LocalDateTime updatedAt;

}
