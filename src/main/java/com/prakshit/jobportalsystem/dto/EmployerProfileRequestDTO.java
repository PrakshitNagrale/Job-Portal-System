package com.prakshit.jobportalsystem.dto;

import com.prakshit.jobportalsystem.entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployerProfileRequestDTO {

    @NotBlank(message = "Company name is required")
    @Size(max = 150, message = "company name should not be greater than 150 characters") // size constraint so that it should not be more than our db varchar constraint,to prevent db error
    private String companyName;

    @Size(max = 255 , message = "Company website should not be greater than 255 characters")
    private String companyWebsite;

    @NotBlank(message = "Contact number is required")
    @Size(max = 20, message = "Contact Number should not be greater than 20 characters")
    private String contactNumber;

    @NotNull(message = "User Id is required to create Employer Profile")
    private Long userId;


}
