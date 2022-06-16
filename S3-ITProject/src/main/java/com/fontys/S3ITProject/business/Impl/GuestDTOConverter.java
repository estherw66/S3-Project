package com.fontys.s3itproject.business.impl;

import com.fontys.s3itproject.dto.GuestDTO;
import com.fontys.s3itproject.dto.GuestReservationDTO;
import com.fontys.s3itproject.dto.ReservationDTO;
import com.fontys.s3itproject.repository.entity.Guest;

import java.util.List;

final class GuestDTOConverter {
    private GuestDTOConverter() {

    }

    public static GuestDTO convertToDTO(Guest guest){
        List<ReservationDTO> reservationDTOS = guest.getReservations()
                .stream()
                .map(ReservationDTOConverter::convertToDTO)
                .toList();

        return GuestDTO.builder()
                .id(guest.getId())
                .firstName(guest.getFirstName())
                .lastName(guest.getLastName())
                .email(guest.getEmail())
                .reservations(reservationDTOS)
                .build();
    }

    public static GuestReservationDTO convertToDTOForReservation(Guest guest){
        return GuestReservationDTO.builder()
                .guestID(guest.getId())
                .firstName(guest.getFirstName())
                .lastName(guest.getLastName())
                .build();
    }
}
