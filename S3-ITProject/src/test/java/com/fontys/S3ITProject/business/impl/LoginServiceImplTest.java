package com.fontys.s3itproject.business.impl;

import com.fontys.s3itproject.business.AccessTokenEncoder;
import com.fontys.s3itproject.dto.LoginRequestDTO;
import com.fontys.s3itproject.dto.LoginResponseDTO;
import com.fontys.s3itproject.repository.UserRepository;
import com.fontys.s3itproject.repository.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LoginServiceImplTest {

    @Mock
    UserRepository userRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    @Mock
    AccessTokenEncoder accessTokenEncoder;

    @InjectMocks
    LoginServiceImpl loginService;

    @Test
    void login_shouldReturnAccessToken_whenLoginIsSuccessful() {

    }
}