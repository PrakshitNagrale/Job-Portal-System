package com.prakshit.jobportalsystem.service;

import com.prakshit.jobportalsystem.dto.ApplicantProfileRequestDTO;
import com.prakshit.jobportalsystem.dto.ApplicantProfileResponseDTO;

import java.util.List;
import java.util.UUID;

public interface ApplicantProfileService {

    ApplicantProfileResponseDTO createApplicantProfile(UUID userId, ApplicantProfileRequestDTO requestDTO); // to create applicant profile

    ApplicantProfileResponseDTO getApplicantProfileByUserId(UUID userId); // to get profile by applicant id

    List<ApplicantProfileResponseDTO> getAllApplicantProfile(); // to get all applicant profile

    ApplicantProfileResponseDTO updateApplicantProfile(UUID userId, ApplicantProfileRequestDTO requestDTO);//to update applicant profile

    void deleteApplicantProfile(UUID userId); // to delete applicant profile

}
