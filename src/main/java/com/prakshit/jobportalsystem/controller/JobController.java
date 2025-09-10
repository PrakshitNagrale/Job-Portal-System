package com.prakshit.jobportalsystem.controller;

import com.prakshit.jobportalsystem.dto.JobRequestDTO;
import com.prakshit.jobportalsystem.dto.JobResponseDTO;
import com.prakshit.jobportalsystem.entity.Job;
import com.prakshit.jobportalsystem.entity.JobType;
import com.prakshit.jobportalsystem.service.JobService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
public class JobController {

    JobService jobService;

    //constructor injection
    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    //to create job
    @PostMapping("/jobs/{employerId}")
    public ResponseEntity<JobResponseDTO> createJob(@PathVariable("employerId")UUID employerId, @Valid @RequestBody JobRequestDTO requestDTO){

        JobResponseDTO responseDTO = jobService.createJob(employerId,requestDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    //to get job by jobId
    @GetMapping("/jobs/{jobId}")
    public ResponseEntity<JobResponseDTO> getJobById(@PathVariable("jobId") UUID jobId){

       JobResponseDTO job =  jobService.getJobByJobId(jobId);

       return ResponseEntity.ok(job);

    }

    //to get all jobs from employer
    @GetMapping("/jobs/employer/{employerId}")
    public ResponseEntity<List<JobResponseDTO>> getJobsByEmployer(@PathVariable("employerId") UUID employerId){
        List<JobResponseDTO> jobList = jobService.getJobsByEmployer(employerId);

        return ResponseEntity.ok(jobList);
    }

    //to get all jobs
    @GetMapping("/jobs")
    public ResponseEntity<List<JobResponseDTO>> getAllJobs(){

        return ResponseEntity.ok(
                jobService.getAllJobs()
        );
    }

    //to update job
    @PutMapping("/jobs/{jobId}")
    public ResponseEntity<JobResponseDTO> updateJob(@PathVariable("jobId") UUID jobId,
                                                    @Valid @RequestBody JobRequestDTO requestDTO){

        JobResponseDTO updatedJob = jobService.updateJob(jobId,requestDTO);

        return ResponseEntity.ok(updatedJob);
    }

    //to delete job
    @DeleteMapping("/jobs/{jobId}")
    public ResponseEntity<String> deleteJob(@PathVariable("jobId")UUID jobId){
        jobService.deleteJob(jobId);

        return ResponseEntity.ok("Job Deleted Successfully!");
    }

    //to get jobs by jobType
    @GetMapping("jobs/type")
    public ResponseEntity<List<JobResponseDTO>> getJobByJobType(@RequestParam("jobType") String jobType){ //from postman ->localhost:8080/job/type?jobType=FULL_TIME

        JobType type = JobType.valueOf(jobType.toUpperCase()); //taking string as input , converting it to uppercase JobType

        return ResponseEntity.ok(
                jobService.getJobsByJobType(type)
        );
    }

    //to search jobs by job title, it will also partial search-> if input is "Developer" it will match with ->"Java Developer","Python Developer"..etc
    @GetMapping("jobs/title")
    public ResponseEntity<List<JobResponseDTO>> searchJobsByTitle(@RequestParam("jobTitle") String jobTitle){

        List<JobResponseDTO> jobList = jobService.searchJobsByTitle(jobTitle);

        return ResponseEntity.ok(jobList);
    }


    //to search jobs by location it will ignore case sensitivity
    @GetMapping("jobs/location")
    public  ResponseEntity<List<JobResponseDTO>> searchJobsByLocation(@RequestParam("jobLocation") String jobLocation){

        List<JobResponseDTO> jobList = jobService.getJobsByLocation(jobLocation);

        return ResponseEntity.ok(jobList);
    }


    //to search jobs by skills
    @GetMapping("jobs/skill")
    public ResponseEntity<List<JobResponseDTO>> searchJobBySkill(@RequestParam("skill") String skill){

        List<JobResponseDTO> jobList = jobService.searchJobsBySkill(skill);

        return ResponseEntity.ok(jobList);
    }

    //to search jobs by salary greater than or equal
    @GetMapping("jobs/salary")
    public  ResponseEntity<List<JobResponseDTO>> searchJobByMinimumSalary(@RequestParam("salary")BigDecimal salary){
        List<JobResponseDTO> jobList = jobService.getJobsByMinimumSalary(salary);

        return ResponseEntity.ok(jobList);
    }

    //to search jobs by last date to apply
    @GetMapping("jobs/date")
    public ResponseEntity<List<JobResponseDTO>> searchByLastDateToApply(@RequestParam("lastDate")LocalDateTime  lastDate){

        List<JobResponseDTO> jobList = jobService.getJobsByLastDateToApply(lastDate);

        return ResponseEntity.ok(jobList);
    }

    //to search jobs by experience range
    @GetMapping("jobs/experience")
    public ResponseEntity<List<JobResponseDTO>> searchJobByExperienceRange(@RequestParam("minExperience") int minExperience,
                                                                           @RequestParam("maxExperience") int maxExperience){

        List<JobResponseDTO> jobList = jobService.getJobsByExperienceRange(minExperience,maxExperience);

        return ResponseEntity.ok(jobList);
    }


                                                                        //we can also do->localhost:8080/job/location?jobLocation=Bangalore&jobTitle=Engineer&jobType=FULL_TIME
}
