package com.prakshit.jobportalsystem.service;

import com.prakshit.jobportalsystem.dto.ApplicantProfileRequestDTO;
import com.prakshit.jobportalsystem.dto.ApplicantProfileResponseDTO;
import com.prakshit.jobportalsystem.entity.ApplicantProfile;
import com.prakshit.jobportalsystem.entity.User;
import com.prakshit.jobportalsystem.exceptions.ApplicantProfileAlreadyExistsException;
import com.prakshit.jobportalsystem.exceptions.ResourceNotFoundException;
import com.prakshit.jobportalsystem.mapper.ApplicantEntityDTOMapper;
import com.prakshit.jobportalsystem.repository.ApplicantProfileRepository;
import com.prakshit.jobportalsystem.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ApplicantProfileServiceImpl implements ApplicantProfileService{

    UserRepository userRepository;
    ApplicantProfileRepository applicantProfileRepository;

    //constructor injection
    public ApplicantProfileServiceImpl(UserRepository userRepository, ApplicantProfileRepository applicantProfileRepository) {
        this.userRepository = userRepository;
        this.applicantProfileRepository = applicantProfileRepository;
    }

    @Override
    public ApplicantProfileResponseDTO createApplicantProfile(UUID userId, ApplicantProfileRequestDTO requestDTO) {

        //1. check userId is valid & present in user table or throw error
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User doest not found for userId: "+userId));

        //2. check this applicant with this userId is already present Applicant table
        if(applicantProfileRepository.existsByUserId(userId)){
            throw  new ApplicantProfileAlreadyExistsException("Applicant Profile already exists for userId: "+userId);
        }
        //3. create applicant profile
        ApplicantProfile applicantProfile = ApplicantEntityDTOMapper.convertToApplicantProfile(requestDTO,user);

        //4. save in db
        ApplicantProfile savedApplicant = applicantProfileRepository.save(applicantProfile);

        //5. return the responseDto
        return ApplicantEntityDTOMapper.convertToApplicantResponse(savedApplicant);
    }

    @Override
    public ApplicantProfileResponseDTO getApplicantProfileByUserId(UUID userId) {

        //1.check userId is valid if not throw exception
        ApplicantProfile savedApplicant = applicantProfileRepository.findByUserId(userId)
                .orElseThrow(()->new ResourceNotFoundException("Applicant Profile does not found for userId: "+userId));

        //2.convert to response and return
        return ApplicantEntityDTOMapper.convertToApplicantResponse(savedApplicant);
    }

    //to get all applicant profiles
    @Override
    public List<ApplicantProfileResponseDTO> getAllApplicantProfile() {

        //1. find all applicant  profiles
       List<ApplicantProfile> applicantProfiles =  applicantProfileRepository.findAll();

       //2. create response list, convert  applicant profiles to response
        List<ApplicantProfileResponseDTO> responseList = new ArrayList<>();

        for(ApplicantProfile applicantProfile :applicantProfiles){
            responseList.add(ApplicantEntityDTOMapper.convertToApplicantResponse(applicantProfile));
        }
       return responseList;

        //with stream
//        return applicantProfiles.stream()
//                .map(ApplicantEntityDTOMapper::convertToApplicantResponse)
//                .toList();
    }

    // to update applicant profile
    @Override
    public ApplicantProfileResponseDTO updateApplicantProfile(UUID userId, ApplicantProfileRequestDTO requestDTO) {

        //1. check the userId is valid, if yes then fetch the user from db
        ApplicantProfile savedApplicant = applicantProfileRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Applicant Profile doest not found for userId: "+userId));

        //2. Update all the fields
        savedApplicant.setName(requestDTO.getApplicantName());
        savedApplicant.setEducation(requestDTO.getEducation());
        savedApplicant.setExperienceYears(requestDTO.getExperienceYears());
        savedApplicant.setExperienceDetails(requestDTO.getExperienceDetails());
        savedApplicant.setPhoneNumber(requestDTO.getPhoneNumber());
        savedApplicant.setSkills(requestDTO.getSkills());
        savedApplicant.setResumeUrl(requestDTO.getResumeUrl());
        //3. save in db
        ApplicantProfile updatedApplicant = applicantProfileRepository.save(savedApplicant);

        //4.convert to response
        return ApplicantEntityDTOMapper.convertToApplicantResponse(updatedApplicant);
    }

    @Override
    public void deleteApplicantProfile(UUID userId) {

        //1. check the userId is valid , if not throw the exception
        ApplicantProfile applicantProfile = applicantProfileRepository.findByUserId(userId)
                .orElseThrow(()-> new ResourceNotFoundException("Applicant Profile doest not found for userId: "+userId));
        //2. delete applicant profile
        applicantProfileRepository.delete(applicantProfile);


    }
}
