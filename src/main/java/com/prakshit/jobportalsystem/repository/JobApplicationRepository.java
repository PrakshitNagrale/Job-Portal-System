package com.prakshit.jobportalsystem.repository;

import com.prakshit.jobportalsystem.entity.JobApplication;
import com.prakshit.jobportalsystem.entity.JobApplicationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplication, UUID> {

    //to find jobApplications by jobId
    List<JobApplication> findByJobId(UUID jobId);

    //to find jobApplications by applicantId
    List<JobApplication> findByApplicantProfileId(UUID applicantId);

    //to find by job Application status
    List<JobApplication> findByJobApplicationStatus(JobApplicationStatus jobApplicationStatus);

    //to get application by jobId and applicantId so that one applicant can not apply to same job twice
    boolean existsByJobIdAndApplicantProfileId(UUID jobId, UUID applicantId);

    //to get job applications via jobId and applicationStatus
    List<JobApplication> findByJobIdAndJobApplicationStatus(UUID jobId, JobApplicationStatus jobApplicationStatus);


    //to get application by applicantId and application status
    List<JobApplication> findByApplicantProfileIdAndJobApplicationStatus(UUID applicantId, JobApplicationStatus jobApplicationStatus);
}
