package com.prakshit.jobportalsystem.service;

import com.prakshit.jobportalsystem.dto.EmployerProfileRequestDTO;
import com.prakshit.jobportalsystem.dto.EmployerProfileResponseDTO;
import com.prakshit.jobportalsystem.entity.EmployerProfile;
import com.prakshit.jobportalsystem.entity.User;
import com.prakshit.jobportalsystem.exceptions.EmployerProfileAlreadyExistsException;
import com.prakshit.jobportalsystem.exceptions.ResourceNotFoundException;
import com.prakshit.jobportalsystem.mapper.EmployerEntityDTOMapper;
import com.prakshit.jobportalsystem.repository.EmployerProfileRepository;
import com.prakshit.jobportalsystem.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class EmployerProfileServiceImpl implements EmployerProfileService {

    EmployerProfileRepository employerProfileRepository;
    UserRepository userRepository;

    public EmployerProfileServiceImpl(EmployerProfileRepository employerProfileRepository, UserRepository userRepository) {
        this.employerProfileRepository = employerProfileRepository;
        this.userRepository = userRepository;
    }

    //to create employer profile
    @Override
    public EmployerProfileResponseDTO createEmployerProfile(UUID userId, EmployerProfileRequestDTO requestDTO) {

        //1.first check userId is present in user table
        User  user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User doest not found with userId: "+userId));

        //2.check this employee with this userId  is already present in employee table
       if(employerProfileRepository.existsByUserId(userId)){
           throw new EmployerProfileAlreadyExistsException("Employer Profile Already Exists for userId: "+userId);
       }
       //3. convert to employerProfile
        EmployerProfile employerProfile = EmployerEntityDTOMapper.convertToEmployerProfile(requestDTO,user);

       //4. save to db
        EmployerProfile savedEmployee = employerProfileRepository.save(employerProfile);

        //5. convert to response dto and return
        return EmployerEntityDTOMapper.convertToEmployerResponse(savedEmployee);
    }

    //to get by employer by userId
    @Override
    public EmployerProfileResponseDTO getEmployerProfileByUserId(UUID userId) {

        //1. check userId is valid, if yes then give employer profile
       EmployerProfile employerProfile =  employerProfileRepository.findByUserId(userId)
                .orElseThrow(()  -> new ResourceNotFoundException("Employer Profile not found for userId: "+userId));

       //2. convert to response
       return EmployerEntityDTOMapper.convertToEmployerResponse(employerProfile);
    }

    //to get all employer profile
    @Override
    public List<EmployerProfileResponseDTO> getAllEmployerProfile() {

        //1. find all employer profiles
        List<EmployerProfile> employerProfiles = employerProfileRepository.findAll();

        //2.Iterate on employerProfiles and convert to response list
        List<EmployerProfileResponseDTO> responseDTOList = new ArrayList<>();

        for(EmployerProfile employerProfile : employerProfiles){
            responseDTOList.add(EmployerEntityDTOMapper.convertToEmployerResponse(employerProfile));
        }
        //3. return response list
        return responseDTOList;
    }

    //to update employer profile
    @Override
    public EmployerProfileResponseDTO updateEmployerProfile(UUID userId, EmployerProfileRequestDTO requestDTO) {

        //1. check user id is valid,if yes get employer profile
        EmployerProfile employerProfile = employerProfileRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Employer Profile not found for userId: "+userId));

        //2. update the details
        employerProfile.setCompanyName(requestDTO.getCompanyName());
        employerProfile.setContactNumber(requestDTO.getContactNumber());
        employerProfile.setCompanyWebsite(requestDTO.getCompanyWebsite());

        EmployerProfile updatedEmployee = employerProfileRepository.save(employerProfile); //save in db

        return EmployerEntityDTOMapper.convertToEmployerResponse(updatedEmployee);
    }

    // to delete employer profile
    @Override
    public void deleteEmployerProfile(UUID userId) {

        //1. find employer profile by userId
        EmployerProfile employerProfile = employerProfileRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Employer Profile not found for userId: "+userId));

        //2. delete employer profile
        employerProfileRepository.delete(employerProfile);

    }
}