package com.fontys.s3itproject.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
}
