package com.prakshit.jobportalsystem.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class EmployerProfileResponseDTO {

    private UUID employerId;
    private String companyName;
    private String companyWebsite;
    private String contactNumber;
    private UUID userId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
