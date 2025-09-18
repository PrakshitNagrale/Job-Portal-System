package com.prakshit.jobportalsystem.dto;

import com.prakshit.jobportalsystem.entity.JobApplicationStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateStatusRequest {

    @NotNull(message = "Job application status is required")
    private JobApplicationStatus status;
}
