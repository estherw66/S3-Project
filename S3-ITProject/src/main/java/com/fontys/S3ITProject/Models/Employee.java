package com.fontys.S3ITProject.Models;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Employee extends Person{

    private int employeeID;
    private Address address;
    private String phoneNumber;
    private LocalDate dateOfBirth;

    public Employee(String firstName, String lastName, String email, String password, int employeeID, Address address,
     String phoneNumber, LocalDate dateOfBirth) {
        super(firstName, lastName, email, password);

        this.employeeID = employeeID;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
    }
}
