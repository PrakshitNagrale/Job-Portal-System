package com.prakshit.jobportalsystem.mapper;

import com.prakshit.jobportalsystem.dto.RegisterResponseDTO;
import com.prakshit.jobportalsystem.dto.UserResponseDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthDTOMapper {

    public static RegisterResponseDTO convertToAuthRegister(String token, UserResponseDTO userResponseDTO){

        RegisterResponseDTO responseDTO = new RegisterResponseDTO();

        responseDTO.setToken(token);
        responseDTO.setEmail(userResponseDTO.getEmail());
        responseDTO.setName(userResponseDTO.getName());
        responseDTO.setUserId(userResponseDTO.getUserId());
        responseDTO.setUserRole(userResponseDTO.getUserRole());

        return responseDTO;
    }
}
