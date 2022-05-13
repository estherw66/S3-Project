package com.fontys.s3itproject.controller;

import com.fontys.s3itproject.business.LoginService;
import com.fontys.s3itproject.dto.LoginRequestDTO;
import com.fontys.s3itproject.dto.LoginResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/login")
@CrossOrigin(origins = "http://localhost:3000")
public class LoginController {

    private final LoginService loginService;

    @PostMapping
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid LoginRequestDTO loginRequestDTO){
        LoginResponseDTO loginResponseDTO = loginService.login(loginRequestDTO);
        return ResponseEntity.ok(loginResponseDTO);
    }
}
