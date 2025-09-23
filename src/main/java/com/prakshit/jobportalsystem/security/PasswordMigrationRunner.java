//package com.prakshit.jobportalsystem.security;
//
//import com.prakshit.jobportalsystem.repository.UserRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//@Component
//public class PasswordMigrationRunner implements CommandLineRunner { // to update the existing raw password with encoded password
//
//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//
//    public PasswordMigrationRunner(UserRepository userRepository, PasswordEncoder passwordEncoder) {
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    @Override
//    public void run(String... args) {
//        userRepository.findAll().forEach(user -> {
//            String rawPassword = user.getPassword();
//            // if not already encoded (check length or prefix)
//            if (!rawPassword.startsWith("$2a$")) { // BCrypt hashes start with $2a$
//                user.setPassword(passwordEncoder.encode(rawPassword));
//                userRepository.save(user);
//                System.out.println("Migrated password for: " + user.getEmail());
//            }
//        });
//    }
//}
//
