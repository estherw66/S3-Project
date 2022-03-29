package com.fontys.S3ITProject.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
public class Login {

    String email;
    String password;

    public Login(String email, String password){
        this.email = email;
        this.password = password;
    }
}
