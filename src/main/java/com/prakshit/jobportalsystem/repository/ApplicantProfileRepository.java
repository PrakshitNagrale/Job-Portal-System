package com.prakshit.jobportalsystem.repository;

import com.prakshit.jobportalsystem.entity.ApplicantProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ApplicantProfileRepository extends JpaRepository<ApplicantProfile, UUID> {


    Optional<ApplicantProfile> findByUserId(UUID userId); // find applicant profile by userId

    boolean existsByUserId(UUID userId); // to check the applicant with the userId is already present


}
