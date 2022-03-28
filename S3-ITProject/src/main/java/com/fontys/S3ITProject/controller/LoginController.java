package com.fontys.S3ITProject.controller;

import com.fontys.S3ITProject.business.LoginService;
import com.fontys.S3ITProject.models.Employee;
import com.fontys.S3ITProject.models.Guest;
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

    @ResponseBody
    public Person checkLogin(@RequestBody String email, String password){

        return new Person(7, "test", "test", "test@mail.com", "password");

//        if (email.equals("test@email.com") || password.equals("password")){
//            return new Person(7, "test", "test", "test@mail.com", "password");
//        } else {
//            return new Person(7, "error", "error", "test@mail.com", "password");
//        }
    }
}
