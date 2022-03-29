package com.fontys.S3ITProject.controller;

import com.fontys.S3ITProject.business.LoginService;
import com.fontys.S3ITProject.models.Employee;
import com.fontys.S3ITProject.models.*;
import com.fontys.S3ITProject.models.Person;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RequestMapping("/api/login")
@RestController
public class LoginController {
    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping()
    public ResponseEntity<Guest> checkLogin(@RequestBody Login login){

        Guest user = loginService.checkLogin(login.getEmail(), login.getPassword());

        if (user != null){
            return ResponseEntity.ok().body(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
