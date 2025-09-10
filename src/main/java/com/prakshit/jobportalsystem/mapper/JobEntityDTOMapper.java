package com.prakshit.jobportalsystem.mapper;

import com.prakshit.jobportalsystem.dto.JobRequestDTO;
import com.prakshit.jobportalsystem.dto.JobResponseDTO;
import com.prakshit.jobportalsystem.entity.EmployerProfile;
import com.prakshit.jobportalsystem.entity.Job;
import com.prakshit.jobportalsystem.entity.JobType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class JobEntityDTOMapper {

    public static Job convertToJobEntity(EmployerProfile employerProfile, JobRequestDTO requestDTO){

        Job job = new Job();

        job.setJobTitle(requestDTO.getJobTitle());
        job.setJobDescription(requestDTO.getJobDescription());
        job.setLocation(requestDTO.getLocation());
        job.setJobType(requestDTO.getJobType());
        job.setSalary(requestDTO.getSalary());
        job.setMinExperience(requestDTO.getMinExperience());
        job.setMaxExperience(requestDTO.getMaxExperience());
        job.setSkillsRequired(requestDTO.getSkillsRequired());
        job.setEmployerProfile(employerProfile);
        job.setLastDateToApply(requestDTO.getLastDateToApply());

        return job;
    }

    public static JobResponseDTO converToJobResponseDTO(Job job){

        JobResponseDTO jobResponseDTO = new JobResponseDTO();

        jobResponseDTO.setJobId(job.getId());
        jobResponseDTO.setJobTitle(job.getJobTitle());
        jobResponseDTO.setJobDescription(job.getJobDescription());
        jobResponseDTO.setJobType(job.getJobType());
        jobResponseDTO.setMinExperience(job.getMinExperience());
        jobResponseDTO.setMaxExperience(job.getMaxExperience());
        jobResponseDTO.setLocation(job.getLocation());
        jobResponseDTO.setSalary(job.getSalary());
        jobResponseDTO.setSkillsRequired(job.getSkillsRequired());
        jobResponseDTO.setEmployerId(job.getEmployerProfile().getId());
        jobResponseDTO.setEmployerName(job.getEmployerProfile().getCompanyName());
        jobResponseDTO.setCreatedAt(job.getCreatedAt());
        jobResponseDTO.setUpdatedAt(job.getUpdatedAt());
        jobResponseDTO.setLastDateToApply(job.getLastDateToApply());

       return jobResponseDTO;
    }
}
