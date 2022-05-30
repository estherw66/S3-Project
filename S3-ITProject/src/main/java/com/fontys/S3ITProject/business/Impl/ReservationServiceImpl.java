package com.fontys.s3itproject.business.impl;

import com.fontys.s3itproject.business.ReservationService;
import com.fontys.s3itproject.dto.AccessTokenDTO;
import com.fontys.s3itproject.dto.CreateReservationRequestDTO;
import com.fontys.s3itproject.dto.CreateReservationResponseDTO;
import com.fontys.s3itproject.repository.ReservationRepository;
import com.fontys.s3itproject.repository.RoomRepository;
import com.fontys.s3itproject.repository.entity.Reservation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private AccessTokenDTO requestAccessToken;

    private final ReservationRepository reservationRepository;
    private final RoomRepository roomRepository;

    @Override
    public CreateReservationResponseDTO createReservation(CreateReservationRequestDTO request) {



        return null;
    }

    private Reservation saveNewReservation(CreateReservationRequestDTO request){
        Reservation reservation = Reservation.builder()
                .reservationDate(request.getReservationDate())
                .checkInDate(request.getCheckInDate())
                .checkOutDate(request.getCheckOutDate())
                .amountOfGuests(request.getAmountOfGuests())
                .totalPrice(request.getTotalPrice())
                .isCheckedIn(request.isCheckedIn())
                .reservationRooms(request.getReservationRooms())
                .build();

        return reservationRepository.save(reservation);
    }
}