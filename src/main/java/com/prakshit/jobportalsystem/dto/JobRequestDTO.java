package com.prakshit.jobportalsystem.dto;

import com.prakshit.jobportalsystem.entity.JobType;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class JobRequestDTO {

    @NotBlank(message = "Job title is Required")                //@NotBlank -> is only for String
    private String jobTitle;

    @NotBlank(message = "Job Description is Required")
    private String jobDescription;

    @NotBlank(message = "Location is Required")
    private String location;

    @NotNull(message = "Min Experience years is required! For freshers, put 0")      //using @NotNull -> because it is Integer
    @Min(value = 0,message = "Min Experience years can not be Negative")              //  int is always 0 if not provided any input → we can’t distinguish between “not provided any input” and “provided as 0”.
    private Integer minExperience;                                           // Integer (object type) allows null checking → validation framework can catch it and return "Experience years is required" if missing".

    @NotNull(message = "Max Experience years is required! For freshers , put 0")
    @Min(value = 0,message = "Max Experience can not be Negative")
    private Integer maxExperience;

    @DecimalMin(value = "0.0",inclusive = true,message = "Salary cannot be Negative")  // inclusive = true -> it allows 0.0
    private BigDecimal salary;

    //fields is a list therefore using @NotNull
    @NotNull(message = "Skills are Required! Insert each skill comma separated")                //@NotNull-> if someone did not put skill attribute in request it will throw this
    @Size(min = 1,message = "At least one skill is required! Insert each skill comma separated") //-> if skill is empty ->[ ], then it will say this
    private List<@NotBlank(message = "Skill can not be empty") String> skillsRequired;            //->no blank entries inside the list-> if someone puts [""] it throws error

    @NotNull(message = "Job type is Required")
    private JobType jobType;

    @NotNull(message = "Last Date is Required")
    @Future(message = "Last Date to apply must be in future")                   // @Future ->it says date must be in the future (not today or past)
    private LocalDateTime lastDateToApply;


}
