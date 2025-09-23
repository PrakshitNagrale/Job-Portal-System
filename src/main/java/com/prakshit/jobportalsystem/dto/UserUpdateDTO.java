package com.prakshit.jobportalsystem.dto;

import com.prakshit.jobportalsystem.entity.UserRole;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateDTO {


    private String name;

    @Email(message = "Invalid email format")
    private String email;

    // password is optional → no @NotBlank
    private String password;

    private UserRole userRole;
}
