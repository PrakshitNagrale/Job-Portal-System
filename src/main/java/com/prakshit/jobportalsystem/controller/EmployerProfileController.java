package com.prakshit.jobportalsystem.controller;

import com.prakshit.jobportalsystem.dto.EmployerProfileRequestDTO;
import com.prakshit.jobportalsystem.dto.EmployerProfileResponseDTO;
import com.prakshit.jobportalsystem.service.EmployerProfileService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employers")
public class EmployerProfileController {

    private EmployerProfileService employerProfileService;

    public EmployerProfileController(EmployerProfileService employerProfileService) {
        this.employerProfileService = employerProfileService;
    }


    @PostMapping("/{userId}")
    public ResponseEntity<EmployerProfileResponseDTO> create(@PathVariable("userId") Long userId,
                                                             @Valid @RequestBody EmployerProfileRequestDTO requestDTO){

        EmployerProfileResponseDTO responseDTO = employerProfileService.createEmployerProfile(userId,requestDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<EmployerProfileResponseDTO> getEmployerProfileByUserId(@PathVariable("userId") Long userId){

        EmployerProfileResponseDTO responseDTO = employerProfileService.getEmployerProfileByUserId(userId);

        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping
    public ResponseEntity<List<EmployerProfileResponseDTO>> getAllEmployerProfiles(){

        List<EmployerProfileResponseDTO> employerProfiles = employerProfileService.getAllEmployerProfile();

        return ResponseEntity.ok(employerProfiles);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<EmployerProfileResponseDTO> updateEmployerProfile(@PathVariable("userId") Long userId,@Valid @RequestBody EmployerProfileRequestDTO requestDTO){

        EmployerProfileResponseDTO responseDTO = employerProfileService.updateEmployerProfile(userId,requestDTO);

        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteEmployerProfile(@PathVariable("userId") Long userId){

        employerProfileService.deleteEmployerProfile(userId);

        return ResponseEntity.ok("Employer Profile deleted Successfully");
    }
}
