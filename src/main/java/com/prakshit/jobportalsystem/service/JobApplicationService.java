package com.prakshit.jobportalsystem.service;

import com.prakshit.jobportalsystem.dto.JobApplicationRequestDto;
import com.prakshit.jobportalsystem.dto.JobApplicationResponseDto;
import com.prakshit.jobportalsystem.entity.JobApplicationStatus;

import java.util.List;
import java.util.UUID;

public interface JobApplicationService {

    //to create job application
    JobApplicationResponseDto applyForJob(JobApplicationRequestDto requestDto);

    //to get Applications by applicationId
    JobApplicationResponseDto getApplicationsByApplicationId(UUID applicationId);

    //to get Applications by jobId
    List<JobApplicationResponseDto> getApplicationsByJobId(UUID jobId);

    //to get Applications by applicantId
    List<JobApplicationResponseDto> getApplicationsByApplicantId(UUID applicantId);

    //to get All applications
    List<JobApplicationResponseDto> getAllApplications();

    //to update job ApplicationStatus
    JobApplicationResponseDto updateJobApplicationStatus(UUID applicationId,JobApplicationStatus applicationStatus);


    //to delete jobApplication
    void withDrawApplication(UUID jobApplicationId);

    //to get jobApplication by JobApplicationStatus
    List<JobApplicationResponseDto> getApplicationsByStatus(JobApplicationStatus jobApplicationStatus);

    //to get applications by jobId and jobApplicationStatus
    List<JobApplicationResponseDto> getApplicationsByJobIdAndStatus(UUID jobId,JobApplicationStatus status);

    //to get applications by applicantId and jobApplicationStatus
    List<JobApplicationResponseDto> getApplicationsByApplicantIdAndStatus(UUID applicantId, JobApplicationStatus status);

}
