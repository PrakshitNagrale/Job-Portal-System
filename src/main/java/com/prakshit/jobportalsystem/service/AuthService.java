package com.prakshit.jobportalsystem.service;

import com.prakshit.jobportalsystem.dto.*;

public interface AuthService {

    LoginResponseDTO login(LoginRequestDTO requestDTO);

    RegisterResponseDTO registerUser(UserRequestDTO requestDTO);
}
