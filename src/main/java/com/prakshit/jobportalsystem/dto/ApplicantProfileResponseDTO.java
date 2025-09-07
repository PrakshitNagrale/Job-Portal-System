package com.prakshit.jobportalsystem.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class ApplicantProfileResponseDTO {

    private UUID applicantId;
    private String applicantName;
    private String education;
    private List<String> skills;
    private int experienceYears;
    private String experienceDetails;
    private String resumeUrl;
    private String phoneNumber;
    private UUID userId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
