package com.fontys.s3itproject.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GetReservationsByGuestResponseDTO {
    private List<ReservationDTO> reservations;
}