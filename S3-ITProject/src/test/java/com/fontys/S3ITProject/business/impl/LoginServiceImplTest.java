package com.fontys.s3itproject.business.impl;

import com.fontys.s3itproject.business.AccessTokenEncoder;
import com.fontys.s3itproject.business.exception.InvalidCredentialsException;
import com.fontys.s3itproject.dto.LoginRequestDTO;
import com.fontys.s3itproject.dto.LoginResponseDTO;
import com.fontys.s3itproject.repository.UserRepository;
import com.fontys.s3itproject.repository.entity.RoleEnum;
import com.fontys.s3itproject.repository.entity.User;
import com.fontys.s3itproject.repository.entity.UserRole;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

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
        User testUser = User.builder()
                .id(1L)
                .username("username")
                .password("hashed-password")
                .build();
        testUser.setUserRoles(Set.of(
                UserRole.builder()
                        .user(testUser)
                        .role(RoleEnum.EMPLOYEE)
                        .build()));

        when(userRepository.findByUsername("username")).thenReturn(testUser);
        LoginRequestDTO requestDTO = LoginRequestDTO.builder()
                .username("username")
                .password("raw-password")
                .build();

        when(loginService.generateAccessToken(testUser)).thenReturn("accessToken");
        when(passwordEncoder.matches(requestDTO.getPassword(), testUser.getPassword())).thenReturn(true);

        LoginResponseDTO expectedResult = LoginResponseDTO.builder()
                .accessToken("accessToken")
                .build();

        LoginResponseDTO actualResult = loginService.login(requestDTO);

        assertEquals(actualResult, expectedResult);
        verify(userRepository).findByUsername("username");
    }

    @Test
    void login_shouldThrowInvalidCredentialsException_whenUsernameDoesntExist(){
        when(userRepository.findByUsername("username")).thenReturn(null);

        LoginRequestDTO requestDTO = LoginRequestDTO.builder()
                .username("username")
                .password("raw-password")
                .build();

        InvalidCredentialsException exception = assertThrows(InvalidCredentialsException.class,
                () -> loginService.login(requestDTO));

        assertEquals("USERNAME_DOESNT_EXIST", exception.getReason());
        verify(userRepository).findByUsername("username");
    }

    @Test
    void login_shouldThrowInvalidCredentialsException_whenCredentialsAreInvalid(){
        User testUser = User.builder()
                .id(1L)
                .username("username")
                .password("hashed-password")
                .build();
        testUser.setUserRoles(Set.of(
                UserRole.builder()
                        .user(testUser)
                        .role(RoleEnum.EMPLOYEE)
                        .build()));

        when(userRepository.findByUsername("username")).thenReturn(testUser);
        LoginRequestDTO requestDTO = LoginRequestDTO.builder()
                .username("username")
                .password("raw-password")
                .build();

        when(passwordEncoder.matches(requestDTO.getPassword(), testUser.getPassword())).thenReturn(false);

        InvalidCredentialsException exception = assertThrows(InvalidCredentialsException.class,
                () -> loginService.login(requestDTO));

        assertEquals("INVALID_CREDENTIALS", exception.getReason());

        verify(userRepository).findByUsername("username");
    }
}