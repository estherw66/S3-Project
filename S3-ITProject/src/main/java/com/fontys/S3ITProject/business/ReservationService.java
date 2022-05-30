package com.fontys.s3itproject.business;

import com.fontys.s3itproject.dto.CreateReservationRequestDTO;
import com.fontys.s3itproject.dto.CreateReservationResponseDTO;

public interface ReservationService {
    CreateReservationResponseDTO createReservation(CreateReservationRequestDTO request);
}
