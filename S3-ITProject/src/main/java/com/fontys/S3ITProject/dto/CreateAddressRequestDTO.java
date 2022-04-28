package com.fontys.s3itproject.dto;

import com.fontys.s3itproject.repository.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateAddressRequestDTO {
    @NotNull
    @Length(min = 2, max = 50)
    private String streetName;

    @NotNull
    @Length(min = 6, max = 6)
    private String zipCode;

    @NotNull
    @Length(min = 2, max = 50)
    private String city;

    private Employee employee;
}
