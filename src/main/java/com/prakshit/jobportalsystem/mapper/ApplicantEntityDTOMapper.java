package com.prakshit.jobportalsystem.mapper;

import com.prakshit.jobportalsystem.dto.ApplicantProfileRequestDTO;
import com.prakshit.jobportalsystem.dto.ApplicantProfileResponseDTO;
import com.prakshit.jobportalsystem.entity.ApplicantProfile;
import com.prakshit.jobportalsystem.entity.User;

public class ApplicantEntityDTOMapper {

    public static ApplicantProfile convertToApplicantProfile(ApplicantProfileRequestDTO  requestDTO, User user){

        ApplicantProfile applicantProfile = new ApplicantProfile();

        applicantProfile.setName(requestDTO.getApplicantName());
        applicantProfile.setExperienceYears(requestDTO.getExperienceYears());
        applicantProfile.setExperienceDetails(requestDTO.getExperienceDetails());
        applicantProfile.setEducation(requestDTO.getEducation());
        applicantProfile.setSkills(requestDTO.getSkills());
        applicantProfile.setResumeUrl(requestDTO.getResumeUrl());
        applicantProfile.setPhoneNumber(requestDTO.getPhoneNumber());
        applicantProfile.setUser(user);

        return applicantProfile;
    }


    public static ApplicantProfileResponseDTO convertToApplicantResponse(ApplicantProfile applicantProfile){

        ApplicantProfileResponseDTO responseDTO = new ApplicantProfileResponseDTO();

        responseDTO.setApplicantId(applicantProfile.getId());
        responseDTO.setApplicantName(applicantProfile.getName());
        responseDTO.setExperienceYears(applicantProfile.getExperienceYears());
        responseDTO.setExperienceDetails(applicantProfile.getExperienceDetails());
        responseDTO.setEducation(applicantProfile.getEducation());
        responseDTO.setPhoneNumber(applicantProfile.getPhoneNumber());
        responseDTO.setResumeUrl(applicantProfile.getResumeUrl());
        responseDTO.setSkills(applicantProfile.getSkills());
        responseDTO.setUserId(applicantProfile.getUser().getId());
        responseDTO.setCreatedAt(applicantProfile.getCreatedAt());
        responseDTO.setUpdatedAt(applicantProfile.getUpdatedAt());

        return responseDTO;
    }
}
