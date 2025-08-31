package com.prakshit.jobportalsystem.repository;

import com.prakshit.jobportalsystem.entity.EmployerProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployerProfileRepository extends JpaRepository<EmployerProfile,Long> {

   Optional<EmployerProfile> findByUserId(Long userId); //to find employer profile by userId

    boolean existsByUserId(Long userId);  //to check user already has employer profile

    boolean existsByCompanyName(String companyName);  //to check company name already exists
}
