package com.prakshit.jobportalsystem.service;

import com.prakshit.jobportalsystem.dto.EmployerProfileRequestDTO;
import com.prakshit.jobportalsystem.dto.EmployerProfileResponseDTO;

import java.util.List;

public interface EmployerProfileService {

    EmployerProfileResponseDTO createEmployerProfile(Long userId,EmployerProfileRequestDTO requestDTO);

    EmployerProfileResponseDTO getEmployerProfileByUserId(Long userId);

    List<EmployerProfileResponseDTO> getAllEmployerProfile();

    EmployerProfileResponseDTO updateEmployerProfile(Long userId, EmployerProfileRequestDTO requestDTO);

    void deleteEmployerProfile(Long userId);
}
