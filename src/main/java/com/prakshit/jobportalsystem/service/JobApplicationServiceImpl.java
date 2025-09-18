package com.prakshit.jobportalsystem.service;

import com.prakshit.jobportalsystem.dto.JobApplicationRequestDto;
import com.prakshit.jobportalsystem.dto.JobApplicationResponseDto;
import com.prakshit.jobportalsystem.entity.ApplicantProfile;
import com.prakshit.jobportalsystem.entity.Job;
import com.prakshit.jobportalsystem.entity.JobApplication;
import com.prakshit.jobportalsystem.entity.JobApplicationStatus;
import com.prakshit.jobportalsystem.exceptions.ProfileAlreadyExistsException;
import com.prakshit.jobportalsystem.exceptions.ResourceNotFoundException;
import com.prakshit.jobportalsystem.mapper.JobApplicationEntityDTOMapper;
import com.prakshit.jobportalsystem.repository.ApplicantProfileRepository;
import com.prakshit.jobportalsystem.repository.JobApplicationRepository;
import com.prakshit.jobportalsystem.repository.JobRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class JobApplicationServiceImpl implements JobApplicationService{

    JobApplicationRepository jobApplicationRepository;
    JobRepository jobRepository;
    ApplicantProfileRepository applicantProfileRepository;

    //constructor injection
    public JobApplicationServiceImpl(JobApplicationRepository jobApplicationRepository, JobRepository jobRepository,
                                     ApplicantProfileRepository applicantProfileRepository) {
        this.jobApplicationRepository = jobApplicationRepository;
        this.jobRepository = jobRepository;
        this.applicantProfileRepository = applicantProfileRepository;
    }

    @Override
    public JobApplicationResponseDto applyForJob(JobApplicationRequestDto requestDto) {

        //1.check jobId is valid
        Job job = jobRepository.findById(requestDto.getJobId())
                .orElseThrow(() -> new ResourceNotFoundException("Job doest not found for jobId: "+requestDto.getJobId()));

        //2. check applicantId is valid
        ApplicantProfile applicantProfile = applicantProfileRepository.findById(requestDto.getApplicantId())
                .orElseThrow(() -> new ResourceNotFoundException("Applicant does not found for applicantId: "+requestDto.getApplicantId()));

        //3. check if applicant already applied to the job/ no duplicate application for same job
        if(jobApplicationRepository.existsByJobIdAndApplicantProfileId(requestDto.getJobId(),requestDto.getApplicantId())){
            throw new ProfileAlreadyExistsException("Job Application already submitted for applicantId: "+requestDto.getApplicantId());
        }
        //4.convert to JobApplication
        JobApplication jobApplication = JobApplicationEntityDTOMapper.convertToJobApplication(job,applicantProfile,requestDto);

        //5.save in db
        JobApplication savedApplication = jobApplicationRepository.save(jobApplication);

        //6. return and convert to response DTO
        return JobApplicationEntityDTOMapper.convertToResponseDTO(savedApplication);
    }

    @Override
    public JobApplicationResponseDto getApplicationsByApplicationId(UUID applicationId) {

        //1. find application by id
        JobApplication jobApplication = jobApplicationRepository.findById(applicationId)
                .orElseThrow(() -> new ResourceNotFoundException("Job Application does not found for ApplicationId: "+applicationId));
        //2. convert to response
        return JobApplicationEntityDTOMapper.convertToResponseDTO(jobApplication);
    }

    @Override
    public List<JobApplicationResponseDto> getApplicationsByJobId(UUID jobId) {

        //1. find by jobId
        List<JobApplication> jobApplications = jobApplicationRepository.findByJobId(jobId);

        //2. convert to response list using stream
        return jobApplications.stream()
                .map(JobApplicationEntityDTOMapper::convertToResponseDTO)
                .toList();
    }

    @Override
    public List<JobApplicationResponseDto> getApplicationsByApplicantId(UUID applicantId) {
        //1. find by ApplicantId
        List<JobApplication> jobApplications = jobApplicationRepository.findByApplicantProfileId(applicantId);

        //2. convert to response list using stream
        return jobApplications.stream()
                .map(JobApplicationEntityDTOMapper::convertToResponseDTO)
                .toList();
    }

    @Override
    public List<JobApplicationResponseDto> getAllApplications() {

        //1. find all applications
        List<JobApplication> jobApplications = jobApplicationRepository.findAll();

        //2.convert to response using stream
        return jobApplications.stream()
                .map(JobApplicationEntityDTOMapper::convertToResponseDTO)
                .toList();
    }

    // to update the status of job Application by applicationId
    @Override
    public JobApplicationResponseDto updateJobApplicationStatus(UUID applicationId, JobApplicationStatus status) {

        //1. check applicationId is valid
        JobApplication jobApplication = jobApplicationRepository.findById(applicationId)
                .orElseThrow(() -> new ResourceNotFoundException("Application does not found for applicationId: "+applicationId));

        //2. update the status
        jobApplication.setJobApplicationStatus(status);
        //3.
        jobApplicationRepository.save(jobApplication);
        //4.
        return JobApplicationEntityDTOMapper.convertToResponseDTO(jobApplication);
    }

    @Override
    public void withDrawApplication(UUID jobApplicationId) {

        //1. check application id is valid
        JobApplication jobApplication = jobApplicationRepository.findById(jobApplicationId)
                .orElseThrow(() -> new ResourceNotFoundException("Job Application does not found for applicationId: "+jobApplicationId));

        //2.set application status to WITHDRAW
        jobApplication.setJobApplicationStatus(JobApplicationStatus.WITHDRAW);
        //3.save to db
        jobApplicationRepository.save(jobApplication);
    }

    @Override
    public List<JobApplicationResponseDto> getApplicationsByStatus(JobApplicationStatus jobApplicationStatus) {

        //get all the applications by status and convert it to response using stream
        return jobApplicationRepository.findByJobApplicationStatus(jobApplicationStatus)
                .stream()
                .map(JobApplicationEntityDTOMapper::convertToResponseDTO)
                .toList();
    }

    @Override
    public List<JobApplicationResponseDto> getApplicationsByJobIdAndStatus(UUID jobId, JobApplicationStatus status) {
        //get all the applications by jobId & status and convert it to response using stream
        return jobApplicationRepository.findByJobIdAndJobApplicationStatus(jobId,status)
                .stream()
                .map(JobApplicationEntityDTOMapper::convertToResponseDTO)
                .toList();
    }

    @Override
    public List<JobApplicationResponseDto> getApplicationsByApplicantIdAndStatus(UUID applicantId, JobApplicationStatus status) {

        //get all the applications by applicantId & status and convert it to response using stream
        return jobApplicationRepository.findByApplicantProfileIdAndJobApplicationStatus(applicantId,status)
                .stream()
                .map(JobApplicationEntityDTOMapper::convertToResponseDTO)
                .toList();
    }
}
