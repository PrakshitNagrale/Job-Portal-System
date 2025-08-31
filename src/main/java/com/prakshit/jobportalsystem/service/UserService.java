package com.prakshit.jobportalsystem.service;

import com.prakshit.jobportalsystem.dto.UserRequestDTO;
import com.prakshit.jobportalsystem.dto.UserResponseDTO;

import java.util.List;
import java.util.UUID;


public interface UserService {

   UserResponseDTO createUser(UserRequestDTO userRequestDTO); //to create user

   UserResponseDTO getUserById(UUID id);  //to get user by id

    List<UserResponseDTO> getAllUser();

   UserResponseDTO  updateUser(UUID id,UserRequestDTO userRequestDTO);

    void deleteUser(UUID id);

    UserResponseDTO getUserByEmail(String email);

}
