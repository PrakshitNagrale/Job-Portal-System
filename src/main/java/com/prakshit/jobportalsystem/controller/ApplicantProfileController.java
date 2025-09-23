package com.prakshit.jobportalsystem.controller;

import com.prakshit.jobportalsystem.dto.ApplicantProfileRequestDTO;
import com.prakshit.jobportalsystem.dto.ApplicantProfileResponseDTO;
import com.prakshit.jobportalsystem.service.ApplicantProfileService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class ApplicantProfileController {

    ApplicantProfileService applicantProfileService;

    //constructor injection
    public ApplicantProfileController(ApplicantProfileService applicantProfileService) {
        this.applicantProfileService = applicantProfileService;
    }

    //to create Applicant Profile
    @PostMapping("/applicant/userId/{userId}")
    public ResponseEntity<ApplicantProfileResponseDTO> createApplicantProfile(@PathVariable("userId")UUID userId,
                                                                              @Valid @RequestBody ApplicantProfileRequestDTO requestDTO){

        ApplicantProfileResponseDTO responseDTO = applicantProfileService.createApplicantProfile(userId,requestDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    // to get Applicant by userId
    @GetMapping("/applicant/{userId}")
    public ResponseEntity<ApplicantProfileResponseDTO> getApplicantProfileByUserId(@PathVariable UUID userId){

        ApplicantProfileResponseDTO applicantProfile = applicantProfileService.getApplicantProfileByUserId(userId);

        return ResponseEntity.ok(applicantProfile);
    }

    //to get all Applicant Profiles
    @GetMapping("/applicant")
    public ResponseEntity<List<ApplicantProfileResponseDTO>> getAllApplicantProfiles(){

        List<ApplicantProfileResponseDTO> applicantProfiles = applicantProfileService.getAllApplicantProfile();

        return ResponseEntity.ok(applicantProfiles);
    }

    //to update Applicant Profile
    @PutMapping("/applicant/{userId}")
    public ResponseEntity<ApplicantProfileResponseDTO> updateApplicant(@PathVariable("userId") UUID userId,
                                                                       @Valid @RequestBody ApplicantProfileRequestDTO requestDTO){

        ApplicantProfileResponseDTO updatedApplicant = applicantProfileService.updateApplicantProfile(userId,requestDTO);

        return ResponseEntity.ok(updatedApplicant);
    }

    //to delete applicant profile
    @DeleteMapping("/applicant/{userId}")
    public ResponseEntity<String> deleteApplicantProfile(@PathVariable("userId") UUID userId){

        applicantProfileService.deleteApplicantProfile(userId);

        return ResponseEntity.ok("Applicant Profile Deleted Successfully!");
    }

}
