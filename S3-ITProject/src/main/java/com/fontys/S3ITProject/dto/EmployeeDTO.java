package com.fontys.s3itproject.dto;

import com.fontys.s3itproject.models.Address;
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
