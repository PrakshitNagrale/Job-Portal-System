package com.prakshit.jobportalsystem.service;

import com.prakshit.jobportalsystem.dto.UserRequestDTO;
import com.prakshit.jobportalsystem.dto.UserResponseDTO;

import java.util.List;


public interface UserService {

   UserResponseDTO createUser(UserRequestDTO userRequestDTO); //to create user

   UserResponseDTO getUserById(Long id);  //to get user by id

    List<UserResponseDTO> getAllUser();

   UserResponseDTO  updateUser(Long id,UserRequestDTO userRequestDTO);

    void deleteUser(Long id);

    UserResponseDTO getUserByEmail(String email);

}
