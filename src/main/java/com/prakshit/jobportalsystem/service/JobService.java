package com.prakshit.jobportalsystem.service;

import com.prakshit.jobportalsystem.dto.JobRequestDTO;
import com.prakshit.jobportalsystem.dto.JobResponseDTO;
import com.prakshit.jobportalsystem.entity.JobType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface JobService {

    // to create job
    JobResponseDTO createJob(UUID employerId, JobRequestDTO requestDTO);

    //to get job by jobId
    JobResponseDTO getJobByJobId(UUID jobId);

    //to get all jobs form employer
    List<JobResponseDTO> getJobsByEmployer(UUID employerId);

    //to get all jobs
    List<JobResponseDTO> getAllJobs();

    //to update jobs
    JobResponseDTO updateJob(UUID jobId,JobRequestDTO requestDTO);

    void deleteJob(UUID jobId);

    List<JobResponseDTO> getJobsByJobType(JobType jobType);


    List<JobResponseDTO> searchJobsByTitle(String title);

    List<JobResponseDTO> getJobsByLocation(String location);

    List<JobResponseDTO> searchJobsBySkill(String skill);

    List<JobResponseDTO> getJobsByMinimumSalary(BigDecimal salary);

    List<JobResponseDTO> getJobsByLastDateToApply(LocalDateTime date);

    List<JobResponseDTO> getJobsByExperienceRange(int minExperience, int maxExperience);



}
