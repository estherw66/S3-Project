package com.fontys.s3itproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GuestDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private List<ReservationDTO> reservations;
}