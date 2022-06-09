package com.fontys.s3itproject.business.impl;

import com.fontys.s3itproject.business.ReservationService;
import com.fontys.s3itproject.business.exception.InvalidGuestException;
import com.fontys.s3itproject.business.exception.InvalidReservationException;
import com.fontys.s3itproject.business.exception.UnauthorisedDataAccessException;
import com.fontys.s3itproject.dto.*;
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

    private final AccessTokenDTO requestAccessToken;

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

    @Override
    public GetReservationsResponseDTO getReservations() {
        List<ReservationDTO> reservations = findAll()
                .stream()
                .map(ReservationDTOConverter::convertToDTO)
                .toList();

        return GetReservationsResponseDTO.builder()
                .reservations(reservations)
                .build();
    }

    @Override
    public GetReservationsByGuestResponseDTO getReservationsByGuest(Long id) {
        Optional<Guest> guestOptional = guestRepository.findById(id);

        if (guestOptional.isEmpty()){
            throw new InvalidGuestException("GUEST_NOT_FOUND");
        }

        List<ReservationDTO> reservations = findAllByGuest(guestOptional.get())
                .stream()
                .map(ReservationDTOConverter::convertToDTO)
                .toList();

        return GetReservationsByGuestResponseDTO.builder()
                .reservations(reservations)
                .build();
    }

    @Override
    public void reservationCheckIn(ReservationCheckInRequestDTO request) {
        Optional<Reservation> reservationOptional = reservationRepository.findById(request.getId());
        if (reservationOptional.isEmpty()){
            throw new InvalidReservationException("RESERVATION_NOT_FOUND");
        }

        if (!requestAccessToken.hasRole(RoleEnum.EMPLOYEE.name())){
            throw new UnauthorisedDataAccessException("UNAUTHORISED_TO_PERFORM_ACTION");
        }

        Reservation reservation = reservationOptional.get();

//        commented for testing reasons
//        if (reservation.getCheckInDate().isAfter(LocalDate.now())){
//            throw new InvalidReservationException("CAN_NO_LONGER_CHECK_IN");
//        }

        updateFields(reservation);
    }

    private Reservation saveNewReservation(CreateReservationRequestDTO request){
        //TODO fix this -> get correct guest
        Optional<Guest> guestOptional = findGuestByID(request.getGuestID());
        if (guestOptional.isEmpty()){
            throw new InvalidGuestException("GUEST_NOT_FOUND");
        }
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
                .guest(guestOptional.get())
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
    private List<Reservation> findAll(){
        return reservationRepository.findAll();
    }
    private List<Reservation> findAllByGuest(Guest guest) { return reservationRepository.findAllByGuest(guest); }
    private List<ReservationRoom> findAllRoomsByReservationID(Reservation reservation){
        return reservationRoomRepository.findAllByReservation(reservation.getId());
    }
    private void updateFields(Reservation reservation){
        if (reservation.isCheckedIn()){
            reservation.setCheckedIn(false);
        } else {
            reservation.setCheckedIn(true);
        }
//        reservation.setCheckedIn(!reservation.isCheckedIn());

        reservationRepository.save(reservation);
    }
}