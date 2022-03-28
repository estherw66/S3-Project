package com.fontys.S3ITProject.models;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Employee extends Person{

    private Address address;
    private String phoneNumber;
    private LocalDate dateOfBirth;

    public Employee(int id, String firstName, String lastName, String email, String password, Address address,
     String phoneNumber, LocalDate dateOfBirth) {
        super(id, firstName, lastName, email, password);

        this.address = address;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
    }
}
