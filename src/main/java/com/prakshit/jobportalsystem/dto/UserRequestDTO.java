package com.prakshit.jobportalsystem.dto;

import com.prakshit.jobportalsystem.entity.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDTO {

    @NotBlank(message = "Name is required") //@NotBlank ->  it comes when we add valid dependency,& @Valid annotation in controller, to ensure that the name field is not blank
    private String name;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 6 ,message = "Password must be at least  6 characters")
    private String password;


    @NotNull(message = "Role is required")
    private UserRole userRole;

}
