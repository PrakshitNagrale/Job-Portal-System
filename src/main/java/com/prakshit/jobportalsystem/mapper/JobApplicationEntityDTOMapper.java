package com.prakshit.jobportalsystem.mapper;

import com.prakshit.jobportalsystem.dto.JobApplicationRequestDto;
import com.prakshit.jobportalsystem.dto.JobApplicationResponseDto;
import com.prakshit.jobportalsystem.entity.ApplicantProfile;
import com.prakshit.jobportalsystem.entity.Job;
import com.prakshit.jobportalsystem.entity.JobApplication;
import com.prakshit.jobportalsystem.entity.JobApplicationStatus;

public class JobApplicationEntityDTOMapper {

    //convert requestDto to jobApplicationEntity
   public static JobApplication convertToJobApplication(Job job ,ApplicantProfile applicantProfile,
                                                        JobApplicationRequestDto requestDto){

        JobApplication jobApplication = new JobApplication();

        jobApplication.setJob(job);
        jobApplication.setApplicantProfile(applicantProfile);
        jobApplication.setJobApplicationStatus(JobApplicationStatus.APPLIED);
        jobApplication.setAppliedVia(requestDto.getAppliedVia());
        jobApplication.setResumeUrl(requestDto.getResumeUrl());
        jobApplication.setCoverLetter(requestDto.getCoverLetter());

        return jobApplication;
    }

    //convert JobApplicationEntity to responseDto
    public static JobApplicationResponseDto convertToResponseDTO(JobApplication jobApplication){

        JobApplicationResponseDto responseDto = new JobApplicationResponseDto();

        responseDto.setId(jobApplication.getId());
        responseDto.setJobId(jobApplication.getJob().getId());
        responseDto.setApplicantId(jobApplication.getApplicantProfile().getId());
        responseDto.setJobTitle(jobApplication.getJob().getJobTitle());
        responseDto.setApplicantName(jobApplication.getApplicantProfile().getName());
        responseDto.setAppliedVia(jobApplication.getAppliedVia());
        responseDto.setCoverLetter(jobApplication.getCoverLetter());
        responseDto.setJobApplicationStatus(jobApplication.getJobApplicationStatus());
        responseDto.setResumeUrl(jobApplication.getResumeUrl());
        responseDto.setAppliedOn(jobApplication.getCreatedAt());
        responseDto.setUpdatedAt(jobApplication.getUpdatedAt());

        return responseDto;
    }



}
