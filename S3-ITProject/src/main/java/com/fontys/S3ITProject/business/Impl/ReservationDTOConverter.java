package com.fontys.s3itproject.business.impl;

import com.fontys.s3itproject.dto.ReservationDTO;
import com.fontys.s3itproject.repository.entity.Reservation;

final class ReservationDTOConverter {
    private ReservationDTOConverter() {

    }

    public static ReservationDTO convertToDTO(Reservation reservation){
        return ReservationDTO.builder()
                .id(reservation.getId())
                .build();
    }
}
