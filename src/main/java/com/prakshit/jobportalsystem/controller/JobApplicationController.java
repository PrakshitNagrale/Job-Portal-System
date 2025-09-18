package com.prakshit.jobportalsystem.controller;

import com.prakshit.jobportalsystem.dto.JobApplicationRequestDto;
import com.prakshit.jobportalsystem.dto.JobApplicationResponseDto;
import com.prakshit.jobportalsystem.dto.UpdateStatusRequest;
import com.prakshit.jobportalsystem.entity.JobApplicationStatus;
import com.prakshit.jobportalsystem.service.JobApplicationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("jobApplications")
public class JobApplicationController {

    JobApplicationService jobApplicationService;

    //constructor injection
    public JobApplicationController(JobApplicationService jobApplicationService) {
        this.jobApplicationService = jobApplicationService;
    }

    //to apply for jobs
    @PostMapping
    public ResponseEntity<JobApplicationResponseDto> applyForJobs(@Valid @RequestBody JobApplicationRequestDto requestDto){

        JobApplicationResponseDto responseDto = jobApplicationService.applyForJob(requestDto);

        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    //to get application by applicationId
    @GetMapping("/applicationId/{applicationId}")
    public ResponseEntity<JobApplicationResponseDto> getApplicationById(@PathVariable("applicationId") UUID applicationId){

        return ResponseEntity.ok(
                jobApplicationService.getApplicationsByApplicationId(applicationId)
        );
    }

    //to get application by jobId
    @GetMapping("/jobId/{jobId}")
    public ResponseEntity<List<JobApplicationResponseDto>> getApplicationsByJobId(@PathVariable("jobId") UUID jobId){

        return ResponseEntity.ok(
                jobApplicationService.getApplicationsByJobId(jobId)
        );
    }

    //to get application by applicantProfileId
    @GetMapping("/applicantId/{applicantId}")
    public ResponseEntity<List<JobApplicationResponseDto>> getApplicationsByApplicantId(@PathVariable("applicantId") UUID applicantId){

        return ResponseEntity.ok(
                jobApplicationService.getApplicationsByApplicantId(applicantId)
        );
    }

    //to get all jobApplications
    @GetMapping
    public ResponseEntity<List<JobApplicationResponseDto>> getAllApplications(){

        return ResponseEntity.ok(
                jobApplicationService.getAllApplications()
        );
    }

    //to update application status
    @PatchMapping("/applicationId/{applicationId}")
    public ResponseEntity<JobApplicationResponseDto> updateJobApplicationStatus(@PathVariable("applicationId") UUID applicationId,
                                                                               @RequestBody  UpdateStatusRequest request){
                                                                                                        //Ex. localhost:8080/jobApplications/718a8809-a7c2-416e-a463-db095c2f7f8e/status?status=ACCEPTED
        return ResponseEntity.ok(
                jobApplicationService.updateJobApplicationStatus(applicationId, request.getStatus())
        );
    }

    //to withdraw application / delete application
    @DeleteMapping("/applicationId/{applicationId}")
    public ResponseEntity<String> withdrawApplication(@PathVariable("applicationId") UUID applicationId){

        jobApplicationService.withDrawApplication(applicationId);

        return ResponseEntity.ok("Application Withdrawn Successfully!");
    }

    //to get application by applications status
    @GetMapping("/status")
    public ResponseEntity<List<JobApplicationResponseDto>> getApplicationsByStatus(@RequestParam("status") JobApplicationStatus status){

        return ResponseEntity.ok(
                jobApplicationService.getApplicationsByStatus(status)
        );
    }

    //to get applications by jobId & status
    @GetMapping("/jobId/{jobId}/status")
    public ResponseEntity<List<JobApplicationResponseDto>> getApplicationsByJobIdAndStatus(@PathVariable("jobId") UUID jobId,
                                                                                           @RequestParam JobApplicationStatus status){
        return ResponseEntity.ok(
        jobApplicationService.getApplicationsByJobIdAndStatus(jobId,status)
                );
    }

    //to get Applications By ApplicantId And Status
    @GetMapping("/applicantId/{applicantId}/status")
    public ResponseEntity<List<JobApplicationResponseDto>> getApplicationsByApplicantIdAndStatus(@PathVariable("applicantId") UUID applicantId,
                                                                                                 @RequestParam JobApplicationStatus status){
        return ResponseEntity.ok(                                                                                    //localhost:8080/jobApplications/applicantId/6252e0f0-3bca-4b52-9969-453b8e1eaca9/status?status=ACCEPTED
                jobApplicationService.getApplicationsByApplicantIdAndStatus(applicantId,status)
        );
    }


}
