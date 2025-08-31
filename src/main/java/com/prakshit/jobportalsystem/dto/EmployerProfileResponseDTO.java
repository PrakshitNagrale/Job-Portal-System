package com.prakshit.jobportalsystem.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class EmployerProfileResponseDTO {

    private Long employerId;
    private String companyName;
    private String companyWebsite;
    private String contactNumber;
    private Long userId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
