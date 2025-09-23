package com.prakshit.jobportalsystem.mapper;

import com.prakshit.jobportalsystem.dto.UserRequestDTO;
import com.prakshit.jobportalsystem.dto.UserResponseDTO;
import com.prakshit.jobportalsystem.entity.User;

public class UserEntityDTOMapper {

    //to get user from userRequest dto
    public static User convertUserRequestDTOToUser(UserRequestDTO userRequestDTO){

        User user = new User();

        user.setName(userRequestDTO.getName());
        user.setEmail(userRequestDTO.getEmail());
        user.setPassword(userRequestDTO.getPassword());
        user.setUserRole(userRequestDTO.getUserRole());

        return user;

    }
    //to convert user to userResponse dto
    public static UserResponseDTO convertUserToUserResponseDTO(User user){

        UserResponseDTO userResponseDTO = new UserResponseDTO();

        userResponseDTO.setUserId(user.getId());
        userResponseDTO.setName(user.getName());
        userResponseDTO.setEmail(user.getEmail());
        userResponseDTO.setUserRole(user.getUserRole());

        return userResponseDTO;
    }




}
