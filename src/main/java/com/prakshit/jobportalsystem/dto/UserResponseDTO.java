package com.prakshit.jobportalsystem.dto;

import com.prakshit.jobportalsystem.entity.UserRole;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Getter
@Setter
public class UserResponseDTO {

    private UUID id;
    private String name;
    private String email;
    private UserRole userRole;

}
