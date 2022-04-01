package com.fontys.S3ITProject.dto;

import com.fontys.S3ITProject.models.Address;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class EmployeeDTO {
    private Address address;
    private String phoneNumber;
    private LocalDate dateOfBirth;
}
