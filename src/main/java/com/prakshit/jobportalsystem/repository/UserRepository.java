package com.prakshit.jobportalsystem.repository;

import com.prakshit.jobportalsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

   Optional<User> findByEmail(String email); //it will return user if present

   boolean existsByEmail(String email);  // it will check the email is present
}
