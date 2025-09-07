package com.prakshit.jobportalsystem.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ApplicantProfileRequestDTO {

    @NotBlank(message = "Full name is required")
    private String applicantName;

    @NotBlank(message = "Education is required")
    private String education;

    //field is a List<String> so @NotNull
    @NotNull(message = "Skills are required! Insert each skill comma separated")        //-> if someone did not put skill attribute in request
    @Size(min = 1,message = "At least one skill is required! Insert each skill comma separated")  //-> if skill is empty ->[ ]
    private List<@NotBlank(message = "Skill can not to empty") String> skills;              //->no blank entries inside the list-> if someone puts [""] it throws error

    @NotNull(message = "Experience years is required! For freshers, put 0")
    @Min(value = 0,message = "Experience years can not be negative")
    private Integer experienceYears;                //  int is always 0 if not provided any input → we can’t distinguish between “not provided any input” and “provided as 0”.
                                                    // Integer (object type) allows null checking → validation framework can catch it and return "Experience years is required" if missing".

    private String experienceDetails;

    @NotBlank(message = "Resume url is required")
    @Size(max = 1024,message = "Resume URL too Long")
    private String resumeUrl;

    @NotBlank(message = "Phone Number is required")
    @Pattern(regexp = "^[6-9]\\d{9}$", message = "Invalid phone number")  //Start with a digit between 6 and 9 (^[6-9]).
    private String phoneNumber;                                           // Be followed by exactly 9 digits (\\d{9}).

}
