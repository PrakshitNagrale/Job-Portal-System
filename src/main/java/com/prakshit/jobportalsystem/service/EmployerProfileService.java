package com.prakshit.jobportalsystem.service;

import com.prakshit.jobportalsystem.dto.EmployerProfileRequestDTO;
import com.prakshit.jobportalsystem.dto.EmployerProfileResponseDTO;

import java.util.List;
import java.util.UUID;

public interface EmployerProfileService {

    EmployerProfileResponseDTO createEmployerProfile(UUID userId, EmployerProfileRequestDTO requestDTO);

    EmployerProfileResponseDTO getEmployerProfileByUserId(UUID userId);

    List<EmployerProfileResponseDTO> getAllEmployerProfile();

    EmployerProfileResponseDTO updateEmployerProfile(UUID userId, EmployerProfileRequestDTO requestDTO);

    void deleteEmployerProfile(UUID userId);
}
