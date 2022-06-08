package com.fontys.s3itproject.business;

import com.fontys.s3itproject.dto.CreateReservationRequestDTO;
import com.fontys.s3itproject.dto.CreateReservationResponseDTO;
import com.fontys.s3itproject.dto.GetReservationsResponseDTO;
import com.fontys.s3itproject.dto.ReservationCheckInRequestDTO;

public interface ReservationService {
    CreateReservationResponseDTO createReservation(CreateReservationRequestDTO request);
    GetReservationsResponseDTO getReservations();
    void reservationCheckIn(ReservationCheckInRequestDTO request);
}
