package com.fontys.s3itproject.business;

import com.fontys.s3itproject.dto.*;

public interface ReservationService {
    CreateReservationResponseDTO createReservation(CreateReservationRequestDTO request);
    GetReservationsResponseDTO getReservations();
    GetReservationsByGuestResponseDTO getReservationsByGuest(Long id);
    void reservationCheckIn(ReservationCheckInRequestDTO request);
}
