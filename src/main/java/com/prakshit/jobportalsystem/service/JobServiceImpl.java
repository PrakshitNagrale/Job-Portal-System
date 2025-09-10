package com.prakshit.jobportalsystem.service;

import com.prakshit.jobportalsystem.dto.JobRequestDTO;
import com.prakshit.jobportalsystem.dto.JobResponseDTO;
import com.prakshit.jobportalsystem.entity.EmployerProfile;
import com.prakshit.jobportalsystem.entity.Job;
import com.prakshit.jobportalsystem.entity.JobType;
import com.prakshit.jobportalsystem.exceptions.ResourceNotFoundException;
import com.prakshit.jobportalsystem.mapper.JobEntityDTOMapper;
import com.prakshit.jobportalsystem.repository.EmployerProfileRepository;
import com.prakshit.jobportalsystem.repository.JobRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService{

    EmployerProfileRepository employerProfileRepository;
    JobRepository jobRepository;

    // constructor injection
    public JobServiceImpl(EmployerProfileRepository employerProfileRepository, JobRepository jobRepository) {
        this.employerProfileRepository = employerProfileRepository;
        this.jobRepository = jobRepository;
    }

    //to create job
    @Override
    public JobResponseDTO createJob(UUID employerId, JobRequestDTO requestDTO) {

        //1.check employer id is valid
        EmployerProfile employerProfile =employerProfileRepository.findById(employerId)
                .orElseThrow(()-> new ResourceNotFoundException("Employer Profile doest Not found for EmployerId"+employerId));

        //2. covert to job Entity
        Job job = JobEntityDTOMapper.convertToJobEntity(employerProfile,requestDTO);

        //3.save to db
        jobRepository.save(job);

        //4. convert to job response
        return JobEntityDTOMapper.converToJobResponseDTO(job);
    }

    //to get job by jobId
    @Override
    public JobResponseDTO getJobByJobId(UUID jobId) {

        //1. check the jobId is valid and then fetch the job
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new ResourceNotFoundException("Job does not found for JobId"+jobId));

        //2.convert to job response
        return JobEntityDTOMapper.converToJobResponseDTO(job);
    }

    //get all jobs from employer
    @Override
    public List<JobResponseDTO> getJobsByEmployer(UUID employerId) {

        //1. check the employerId is valid
        List<Job> jobList = jobRepository.findByEmployerProfile_Id(employerId);

        //2.convert to responseDTOList by using stream
        return jobList.stream()
                .map(JobEntityDTOMapper::converToJobResponseDTO)
                .collect(Collectors.toList());
    }

    //to get all jobs
    @Override
    public List<JobResponseDTO> getAllJobs() {

        //1. find all jobs
        List<Job> jobList = jobRepository.findAll();

        //2.convert jobList to responseDTOList using stream
        return jobList.stream()
                .map(JobEntityDTOMapper::converToJobResponseDTO)
                .collect(Collectors.toList());
    }

    //to update job
    @Override
    public JobResponseDTO updateJob(UUID jobId, JobRequestDTO requestDTO) {

        //1. check jobId is valid , if yes fetch the job
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new ResourceNotFoundException("Job does not found for JobId"+jobId));

        //2. update all fields
        job.setJobTitle(requestDTO.getJobTitle());
        job.setJobType(requestDTO.getJobType());
        job.setSalary(requestDTO.getSalary());
        job.setSkillsRequired(requestDTO.getSkillsRequired());
        job.setLocation(requestDTO.getLocation());
        job.setJobDescription(requestDTO.getJobDescription());
        job.setMinExperience(requestDTO.getMinExperience());
        job.setMaxExperience(requestDTO.getMaxExperience());
        job.setLastDateToApply(requestDTO.getLastDateToApply());

        Job updatedJob = jobRepository.save(job);
        //3. convert to job response
        return JobEntityDTOMapper.converToJobResponseDTO(updatedJob);
    }

    //to delete job
    @Override
    public void deleteJob(UUID jobId) {

        //1. find job by jobId
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new ResourceNotFoundException("Job does not found for jobId"+jobId));
        //2. delete job
        jobRepository.delete(job);
    }

    // to get jobs by jobType
    @Override
    public List<JobResponseDTO> getJobsByJobType(JobType jobType) {

        //1. find job by job type
        List<Job> jobList = jobRepository.findByJobType(jobType);

        //2.convert jobList to responseDTOList using stream
        return jobList.stream()
                .map(JobEntityDTOMapper::converToJobResponseDTO)
                .toList();
    }

    //to search jobs by title
    @Override
    public List<JobResponseDTO> searchJobsByTitle(String title) {

        //1. find jobs by job title
        List<Job> jobList = jobRepository.searchByJobTitle(title);

        //2.convert jobList to responseDTOList using stream
        return jobList.stream()
                .map(JobEntityDTOMapper::converToJobResponseDTO)
                .toList();
    }

    @Override
    public List<JobResponseDTO> getJobsByLocation(String location) {

        //1.find jobs by location
        List<Job> jobList = jobRepository.findByLocationIgnoreCase(location);

        //2.convert jobList to responseDTOList using stream
        return jobList.stream()
                .map(JobEntityDTOMapper::converToJobResponseDTO)
                .toList();
    }

    @Override
    public List<JobResponseDTO> searchJobsBySkill(String skill) {

        //1. find jobs by skill
        List<Job> jobList = jobRepository.searchBySkill(skill);

        //2. convert jobList to responseDTOList using stream
        return jobList.stream()
                .map(JobEntityDTOMapper::converToJobResponseDTO)
                .toList();

    }

    //to get all jobs by minimum salary
    @Override
    public List<JobResponseDTO> getJobsByMinimumSalary(BigDecimal salary) {

        //1. find jobs
        List<Job> jobList = jobRepository.findBySalaryGreaterThanEqual(salary);

        //2.convert jobList to responseDTOList using stream
        return jobList.stream()
                .map(JobEntityDTOMapper::converToJobResponseDTO)
                .toList();
    }

    //to get all jobs which is open ,after the date provided
    @Override
    public List<JobResponseDTO> getJobsByLastDateToApply(LocalDateTime date) {

        //1.find jobs
        List<Job> jobList = jobRepository.findByLastDateToApplyAfter(date);

        //2..convert jobList to responseDTOList using stream
        return jobList.stream()
                .map(JobEntityDTOMapper::converToJobResponseDTO)
                .toList();
    }

    //to get jobs by experience range
    @Override
    public List<JobResponseDTO> getJobsByExperienceRange(int minExperience, int maxExperience) {
        //1.find jobs by experience range
        List<Job> jobList = jobRepository.findByMinExperienceGreaterThanEqualAndMaxExperienceLessThanEqual(minExperience,maxExperience);

      //2.convert jobList to responseDTOList using stream
        return jobList.stream()
                .map(JobEntityDTOMapper::converToJobResponseDTO)
                .toList();
    }
}
