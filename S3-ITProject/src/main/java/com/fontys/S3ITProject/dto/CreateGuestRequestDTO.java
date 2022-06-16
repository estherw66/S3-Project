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
public class CreateGuestRequestDTO {
    @NotBlank
    @Length(min = 2, max = 25)
    private String firstName;

    @NotBlank
    @Length(min = 2, max = 50)
    private String lastName;

    @NotBlank
    @Length(min = 2, max = 50)
    private String email;

    @NotBlank
    @Length(min = 2, max = 20)
    private String username;

    @NotBlank
    @Length(max = 100)
    private String password;
}
