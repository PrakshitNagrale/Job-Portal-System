package com.prakshit.jobportalsystem.dto;

import com.prakshit.jobportalsystem.entity.UserRole;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter

public class RegisterResponseDTO {

    private UUID userId;
    private String name;
    private String email;
    private UserRole userRole;
    private String token;

}
