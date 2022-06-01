package com.fontys.s3itproject.business.impl;

import com.fontys.s3itproject.business.ReservationService;
import com.fontys.s3itproject.business.exception.InvalidGuestException;
import com.fontys.s3itproject.dto.AccessTokenDTO;
import com.fontys.s3itproject.dto.CreateReservationRequestDTO;
import com.fontys.s3itproject.dto.CreateReservationResponseDTO;
import com.fontys.s3itproject.repository.GuestRepository;
import com.fontys.s3itproject.repository.ReservationRepository;
import com.fontys.s3itproject.repository.ReservationRoomRepository;
import com.fontys.s3itproject.repository.RoomRepository;
import com.fontys.s3itproject.repository.entity.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private AccessTokenDTO requestAccessToken;

    private final ReservationRepository reservationRepository;
    private final ReservationRoomRepository reservationRoomRepository;
    private final GuestRepository guestRepository;

    @Override
    public CreateReservationResponseDTO createReservation(CreateReservationRequestDTO request) {

        Reservation savedReservation = saveNewReservation(request);


        List<Room> rooms = request.getReservationRooms()
                .stream()
                .map(RoomDTOConverter::convertToEntity)
                .toList();

        List<ReservationRoom> reservationRooms = new ArrayList<>();
        for (Room room : rooms) {
            ReservationRoom reservationRoom = ReservationRoom.builder()
                    .reservation(savedReservation)
                    .room(room)
                    .build();
            reservationRooms.add(reservationRoom);
            reservationRoomRepository.save(reservationRoom);
        }

        savedReservation.setReservationRooms(reservationRooms);

        double totalPrice = calculateTotalPrice(savedReservation);
        savedReservation.setTotalPrice(totalPrice);

        return CreateReservationResponseDTO.builder()
                .reservationID(savedReservation.getId())
                .build();
    }

    private Reservation saveNewReservation(CreateReservationRequestDTO request){
        //TODO fix this -> get correct guest
//        Optional<Guest> guestOptional = findGuestByID(1L);
//        if (guestOptional.isEmpty()){
//            throw new InvalidGuestException("GUEST_NOT_FOUND");
//        }

        Reservation reservation = Reservation.builder()
                .reservationDate(LocalDate.now())
                .checkInDate(request.getCheckInDate())
                .checkOutDate(request.getCheckOutDate())
                .amountOfGuests(request.getAmountOfGuests())
                .isCheckedIn(false)
                .totalPrice(45)
                .guest(Guest.builder().id(1L).build())
                .build();


        return reservationRepository.save(reservation);
    }
    private Optional<Guest> findGuestByID(Long id){
        return guestRepository.findById(id);
    }
    private double calculateTotalPrice(Reservation reservation){
        double totalPrice = 45;
        // get room
        for (ReservationRoom reservationRoom : reservation.getReservationRooms()) {
            totalPrice += reservationRoom.getRoom().getPricePerNight();
        }

        return totalPrice;
    }
}