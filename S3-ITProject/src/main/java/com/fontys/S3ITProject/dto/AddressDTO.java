package com.fontys.s3itproject.dto;

import com.fontys.s3itproject.repository.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {
    private Long id;
    private String streetName;
    private String zipCode;
    private String city;
    private Employee employee;
}
