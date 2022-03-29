package com.fontys.S3ITProject.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Employee extends Person{

    private Address address;
    private String phoneNumber;
    private LocalDate dateOfBirth;
}
