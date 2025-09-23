package com.prakshit.jobportalsystem.service;

import com.prakshit.jobportalsystem.dto.*;
import com.prakshit.jobportalsystem.entity.User;
import com.prakshit.jobportalsystem.exceptions.EmailAlreadyExistsException;
import com.prakshit.jobportalsystem.exceptions.ResourceNotFoundException;
import com.prakshit.jobportalsystem.mapper.AuthDTOMapper;
import com.prakshit.jobportalsystem.mapper.UserEntityDTOMapper;
import com.prakshit.jobportalsystem.repository.UserRepository;
import com.prakshit.jobportalsystem.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService{


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    //constructor injection
    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    //to login
    @Override
    public LoginResponseDTO login(LoginRequestDTO requestDTO) {

        //1. find user by email because email is unique
        User user = userRepository.findByEmail(requestDTO.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("Invalid email or password"));

        //2. verify password
        if(!passwordEncoder.matches(requestDTO.getPassword(),user.getPassword())){
            throw  new ResourceNotFoundException("Invalid email or password");     //matches() takes the raw password (user input), hashes it internally, and compares it with the hashed DB password.
        }
        //3.Generate Jwt token by email & role
        String token = jwtUtil.generateToken(user.getEmail(),user.getUserRole().name());

        //4.Return response
        return  new LoginResponseDTO(token,user.getUserRole().name());
    }

    // to register new user and return the jwt token along with the registered information
    @Override
    public RegisterResponseDTO registerUser(UserRequestDTO requestDTO) {

        //1. check email is unique
        if(userRepository.existsByEmail(requestDTO.getEmail())){
            throw new EmailAlreadyExistsException("Email Already Registered! Please Provide different Email.");
        }
        //2.convert to user
        User user = UserEntityDTOMapper.convertUserRequestDTOToUser(requestDTO);

        //3. Encode the raw password before saving to db
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        //4.save to db
        userRepository.save(user);
        //5.covert to response
         UserResponseDTO userResponseDTO = UserEntityDTOMapper.convertUserToUserResponseDTO(user);

         //6.generate jwt token
        String token = jwtUtil.generateToken(user.getEmail(),user.getUserRole().name());
        //7.return response with token
        return AuthDTOMapper.convertToAuthRegister(token,userResponseDTO);
    }
}
