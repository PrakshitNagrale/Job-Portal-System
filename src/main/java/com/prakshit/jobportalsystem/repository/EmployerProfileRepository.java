package com.prakshit.jobportalsystem.repository;

import com.prakshit.jobportalsystem.entity.EmployerProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface EmployerProfileRepository extends JpaRepository<EmployerProfile,UUID> {

   Optional<EmployerProfile> findByUserId(UUID userId); //to find employer profile by userId

    boolean existsByUserId(UUID userId);  //to check user already has employer profile

    boolean existsByCompanyName(String companyName);  //to check company name already exists
}
