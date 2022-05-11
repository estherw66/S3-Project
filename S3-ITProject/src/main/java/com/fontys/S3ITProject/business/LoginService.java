package com.fontys.s3itproject.business;

import com.fontys.s3itproject.dto.LoginRequestDTO;
import com.fontys.s3itproject.dto.LoginResponseDTO;

public interface LoginService {
    LoginResponseDTO login(LoginRequestDTO loginRequest);
}
