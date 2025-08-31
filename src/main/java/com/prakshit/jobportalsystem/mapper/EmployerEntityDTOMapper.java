package com.prakshit.jobportalsystem.mapper;

import com.prakshit.jobportalsystem.dto.EmployerProfileRequestDTO;
import com.prakshit.jobportalsystem.dto.EmployerProfileResponseDTO;
import com.prakshit.jobportalsystem.entity.EmployerProfile;
import com.prakshit.jobportalsystem.entity.User;

public class EmployerEntityDTOMapper {

    public static EmployerProfile convertToEmployerProfile(EmployerProfileRequestDTO employerRequestDTO, User user){


        EmployerProfile employerProfile = new EmployerProfile();

        employerProfile.setCompanyName(employerRequestDTO.getCompanyName());
        employerProfile.setCompanyWebsite(employerRequestDTO.getCompanyWebsite());
        employerProfile.setContactNumber(employerRequestDTO.getContactNumber());
        employerProfile.setUser(user);

        return employerProfile;

    }

    public static EmployerProfileResponseDTO convertToEmployerResponse(EmployerProfile employerProfile){

        EmployerProfileResponseDTO employerResponseDTO = new EmployerProfileResponseDTO();

        employerResponseDTO.setEmployerId(employerProfile.getId());
        employerResponseDTO.setCompanyName(employerProfile.getCompanyName());
        employerResponseDTO.setCompanyWebsite(employerProfile.getCompanyWebsite());
        employerResponseDTO.setContactNumber(employerProfile.getContactNumber());
        employerResponseDTO.setUserId(employerProfile.getUser().getId());
        employerResponseDTO.setCreatedAt(employerProfile.getCreatedAt());
        employerResponseDTO.setUpdatedAt(employerProfile.getUpdatedAt());


        return employerResponseDTO;
    }
}
