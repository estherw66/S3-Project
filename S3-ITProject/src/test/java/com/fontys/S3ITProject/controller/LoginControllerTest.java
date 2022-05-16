package com.fontys.s3itproject.controller;

import com.fontys.s3itproject.business.LoginService;
import com.fontys.s3itproject.business.exception.InvalidCredentialsException;
import com.fontys.s3itproject.dto.LoginRequestDTO;
import com.fontys.s3itproject.dto.LoginResponseDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class LoginControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    LoginService loginServiceMock;

    @Test
    void login_shouldReturn200ResponseWithAccessToken_whenLoginSuccessful() throws Exception {
        LoginRequestDTO requestDTO = LoginRequestDTO.builder()
                .username("Esther")
                .password("password")
                .build();

        LoginResponseDTO responseDTO = LoginResponseDTO.builder()
                .accessToken("accessToken")
                .build();

        when(loginServiceMock.login(requestDTO))
                .thenReturn(responseDTO);

        mockMvc.perform(post("/api/login")
                .contentType(APPLICATION_JSON_VALUE)
                .content("""
                        {
                            "username": "Esther",
                            "password": "password"
                        }
                        """))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("""
                                {"accessToken": "accessToken"}
                            """));
        verify(loginServiceMock).login(requestDTO);
    }

    @Test
    void login_shouldReturn400Response_whenInvalidCredentials() throws Exception {

    }
}