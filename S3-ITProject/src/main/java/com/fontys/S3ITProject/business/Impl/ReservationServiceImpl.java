package com.fontys.s3itproject.business.impl;

import com.fontys.s3itproject.business.ReservationService;
import com.fontys.s3itproject.business.exception.InvalidReservationException;
import com.fontys.s3itproject.dto.AccessTokenDTO;
import com.fontys.s3itproject.dto.CreateReservationRequestDTO;
import com.fontys.s3itproject.dto.CreateReservationResponseDTO;
import com.fontys.s3itproject.dto.RoomDTO;
import com.fontys.s3itproject.repository.GuestRepository;
import com.fontys.s3itproject.repository.ReservationRepository;
import com.fontys.s3itproject.repository.ReservationRoomRepository;
import com.fontys.s3itproject.repository.entity.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.time.temporal.ChronoUnit.DAYS;

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
        if (request.getCheckInDate().isBefore(LocalDate.now())){
            throw new InvalidReservationException("CHECK_IN_DATE_CANNOT_BE_BEFORE_TODAY");
        }
        if (request.getCheckOutDate().isBefore(request.getCheckInDate())){
            throw new InvalidReservationException("CHECK_OUT_DATE_MUST_BE_AFTER_CHECK_IN_DATE");
        }
        if (request.getCheckOutDate().isBefore(LocalDate.now())){
            throw new InvalidReservationException("CHECK_OUT_DATE_CANNOT_BE_BEFORE_TODAY");
        }
        if (request.getAmountOfGuests() > calculateTotalRoomCapacity(request)){
            throw new InvalidReservationException("AMOUNT_OF_GUESTS_IS_HIGHER_THAN_TOTAL_CAPACITY");
        }
        if (request.getCheckOutDate().equals(request.getCheckInDate())){
            throw new InvalidReservationException("CHECK_OUT_DATE_CANNOT_BE_THE_SAME_AS_CHECK_IN_DATE");
        }

        Reservation reservation = Reservation.builder()
                .reservationDate(LocalDate.now())
                .checkInDate(request.getCheckInDate())
                .checkOutDate(request.getCheckOutDate())
                .amountOfGuests(request.getAmountOfGuests())
                .isCheckedIn(false)
                .totalPrice(calculateTotalPrice(request))
                .guest(Guest.builder().id(1L).build())
                .build();


        return reservationRepository.save(reservation);
    }
    private Optional<Guest> findGuestByID(Long id){
        return guestRepository.findById(id);
    }
    private double calculateTotalPrice(CreateReservationRequestDTO request){
        double totalPrice = 0;

        // calculate nights for reservation
        long totalDays = DAYS.between(request.getCheckInDate(), request.getCheckOutDate());

        // calculate total price for every room
        for (RoomDTO room : request.getReservationRooms()){
            totalPrice += room.getPricePerNight() * totalDays;
        }

        return totalPrice;
    }
    private int calculateTotalRoomCapacity(CreateReservationRequestDTO request){
        int totalCapacity = 0;

        for (RoomDTO room : request.getReservationRooms()){
            totalCapacity += room.getCapacity();
        }

        return totalCapacity;
    }
}