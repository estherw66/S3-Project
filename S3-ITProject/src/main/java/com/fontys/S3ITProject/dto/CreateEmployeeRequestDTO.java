package com.fontys.s3itproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateEmployeeRequestDTO {
    @NotBlank
    @Length(min = 2, max = 25)
    private String firstName;

    @NotBlank
    @Length(min = 2, max = 50)
    private String lastName;

    @NotBlank
    @Length(max = 12)
    private String phoneNumber;

    @NotNull
    private LocalDate dateOfBirth;

    @NotBlank
    @Length(min = 2, max = 50)
    private String streetName;

    @NotBlank
    @Length(min=6, max = 6)
    private String zipCode;

    @NotBlank
    @Length(min = 2, max = 50)
    private String city;
}
