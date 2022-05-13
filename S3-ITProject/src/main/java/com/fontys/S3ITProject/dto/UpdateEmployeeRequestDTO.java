package com.fontys.s3itproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateEmployeeRequestDTO {
    private Long id;

    @NotBlank
    @Length(min = 2, max = 25)
    private String firstName;

    @NotBlank
    @Length(min = 2, max = 50)
    private String lastName;

    @NotBlank
    @Length(max = 12)
    private String phoneNumber;
}
