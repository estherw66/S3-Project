package com.fontys.s3itproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateEmployeeRequestDTO {
    @NotNull
    @Length(min = 2, max = 25)
    private String firstName;

    @NotNull
    @Length(min = 2, max = 50)
    private String lastName;

    @NotNull
    @Length(min = 2, max = 50)
    private String email;

    @NotNull
    @Length(min = 12, max = 12)
    private String phoneNumber;

    @NotNull
    private LocalDate dateOfBirth;
}
