package com.fontys.s3itproject.business.impl;

import com.fontys.s3itproject.business.exception.InvalidGuestException;
import com.fontys.s3itproject.business.exception.InvalidReservationException;
import com.fontys.s3itproject.business.exception.UnauthorisedDataAccessException;
import com.fontys.s3itproject.dto.*;
import com.fontys.s3itproject.repository.GuestRepository;
import com.fontys.s3itproject.repository.ReservationRepository;
import com.fontys.s3itproject.repository.ReservationRoomRepository;
import com.fontys.s3itproject.repository.entity.Guest;
import com.fontys.s3itproject.repository.entity.Reservation;
import com.fontys.s3itproject.repository.entity.RoleEnum;
import org.apache.tomcat.jni.Local;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReservationServiceImplTest {

    @Mock
    AccessTokenDTO accessTokenDTO;

    @Mock
    ReservationRepository reservationRepositoryMock;

    @Mock
    ReservationRoomRepository reservationRoomRepository;

    @Mock
    GuestRepository guestRepository;

    @InjectMocks
    ReservationServiceImpl reservationService;

    @Test
    void createReservation_shouldSaveNewReservation() {
        when(guestRepository.findById(1L)).thenReturn(Optional.of(Guest.builder().id(1L).build()));

        Reservation reservation = Reservation.builder()
                .reservationDate(LocalDate.now())
                .checkInDate(LocalDate.now().plusDays(10))
                .checkOutDate(LocalDate.now().plusDays(11))
                .amountOfGuests(1)
                .totalPrice(45)
                .isCheckedIn(false)
                .guest(Guest.builder().id(1L).build())
                .reservationRooms(null)
                .build();

        Reservation saved = Reservation.builder()
                .id(1L)
                .reservationDate(LocalDate.now())
                .checkInDate(LocalDate.now().plusDays(10))
                .checkOutDate(LocalDate.now().plusDays(11))
                .amountOfGuests(1)
                .totalPrice(45)
                .isCheckedIn(false)
                .guest(Guest.builder().id(1L).build())
                .reservationRooms(null)
                .build();

        when(reservationRepositoryMock.save(reservation)).thenReturn(saved);

        CreateReservationRequestDTO request = CreateReservationRequestDTO.builder()
                .checkInDate(LocalDate.now().plusDays(10))
                .checkOutDate(LocalDate.now().plusDays(11))
                .amountOfGuests(1)
                .guestID(1L)
                .reservationRooms(List.of(RoomDTO.builder()
                        .id(1L).roomType("Single")
                        .isFeatured(false).imageUrl("")
                        .pricePerNight(45).capacity(1)
                        .build()))
                .build();

        CreateReservationResponseDTO actualResult = reservationService.createReservation(request);
        CreateReservationResponseDTO expectedResult = CreateReservationResponseDTO.builder()
                .reservationID(1L).build();

        assertEquals(expectedResult, actualResult);
        verify(reservationRepositoryMock).save(reservation);
        verify(guestRepository).findById(1L); 
    }

    @Test
    void createReservation_shouldThrowInvalidGuestException_whenGuestNotFound(){
        InvalidGuestException exception = assertThrows(InvalidGuestException.class,
                () -> reservationService.createReservation(CreateReservationRequestDTO.builder().guestID(1L).build()));

        assertEquals("GUEST_NOT_FOUND", exception.getReason());
        verifyNoInteractions(reservationRepositoryMock);
    }

    @Test
    void createReservation_shouldThrowInvalidReservationException_whenCheckInDateIsBeforeToday(){
        when(guestRepository.findById(1L)).thenReturn(Optional.of(Guest.builder().id(1L).build()));

        CreateReservationRequestDTO request = CreateReservationRequestDTO.builder()
                .checkInDate(LocalDate.now().minusDays(1))
                .checkOutDate(LocalDate.now().plusDays(11))
                .guestID(1L)
                .build();

        InvalidReservationException exception = assertThrows(InvalidReservationException.class,
                () -> reservationService.createReservation(request));
        assertEquals("CHECK_IN_DATE_CANNOT_BE_BEFORE_TODAY", exception.getReason());
    }

    @Test
    void createReservation_shouldThrowInvalidReservationException_whenCheckInDateIsAfterCheckOutDate(){
        when(guestRepository.findById(1L)).thenReturn(Optional.of(Guest.builder().id(1L).build()));

        CreateReservationRequestDTO request = CreateReservationRequestDTO.builder()
                .checkInDate(LocalDate.now().plusDays(12))
                .checkOutDate(LocalDate.now().plusDays(11))
                .guestID(1L)
                .build();

        InvalidReservationException exception = assertThrows(InvalidReservationException.class,
                () -> reservationService.createReservation(request));
        assertEquals("CHECK_OUT_DATE_MUST_BE_AFTER_CHECK_IN_DATE", exception.getReason());
    }

    @Test
    void createReservation_shouldThrowInvalidReservationException_whenAmountOfGuestsIsHigherThanTotalCapacity(){
        when(guestRepository.findById(1L)).thenReturn(Optional.of(Guest.builder().id(1L).build()));

        CreateReservationRequestDTO request = CreateReservationRequestDTO.builder()
                .checkInDate(LocalDate.now().plusDays(10))
                .checkOutDate(LocalDate.now().plusDays(11))
                .guestID(1L)
                .amountOfGuests(5)
                .reservationRooms(List.of(RoomDTO.builder().id(1L).capacity(4).build()))
                .build();

        InvalidReservationException exception = assertThrows(InvalidReservationException.class,
                () -> reservationService.createReservation(request));
        assertEquals("AMOUNT_OF_GUESTS_IS_HIGHER_THAN_TOTAL_CAPACITY", exception.getReason());
    }

    @Test
    void createReservation_shouldThrowInvalidReservationException_whenCheckInDateIsTheSameAsCheckOutDate(){
        when(guestRepository.findById(1L)).thenReturn(Optional.of(Guest.builder().id(1L).build()));

        CreateReservationRequestDTO request = CreateReservationRequestDTO.builder()
                .checkInDate(LocalDate.now().plusDays(10))
                .checkOutDate(LocalDate.now().plusDays(10))
                .guestID(1L)
                .amountOfGuests(1)
                .reservationRooms(List.of(RoomDTO.builder().id(1L).capacity(4).build()))
                .build();

        InvalidReservationException exception = assertThrows(InvalidReservationException.class,
                () -> reservationService.createReservation(request));
        assertEquals("CHECK_OUT_DATE_CANNOT_BE_THE_SAME_AS_CHECK_IN_DATE", exception.getReason());
    }

    @Test
    void getReservations_shouldReturnAllReservationsConvertedToDTO() {
        Reservation reservation1 = Reservation.builder()
                .id(1L)
                .reservationDate(LocalDate.now())
                .checkInDate(LocalDate.now().plusDays(10))
                .checkOutDate(LocalDate.now().plusDays(11))
                .amountOfGuests(1)
                .totalPrice(45)
                .isCheckedIn(false)
                .guest(Guest.builder().id(1L).build())
                .reservationRooms(null)
                .build();

        Reservation reservation2 = Reservation.builder()
                .id(2L)
                .reservationDate(LocalDate.now())
                .checkInDate(LocalDate.now().plusDays(10))
                .checkOutDate(LocalDate.now().plusDays(11))
                .amountOfGuests(1)
                .totalPrice(100)
                .isCheckedIn(false)
                .guest(Guest.builder().id(2L).build())
                .reservationRooms(null)
                .build();

        when(reservationRepositoryMock.findAll()).thenReturn(List.of(reservation1, reservation2));

        GetReservationsResponseDTO actualResult = reservationService.getReservations();

        ReservationDTO reservation1DTO = ReservationDTO.builder()
                .id(1L)
                .reservationDate(LocalDate.now())
                .checkInDate(LocalDate.now().plusDays(10))
                .checkOutDate(LocalDate.now().plusDays(11))
                .amountOfGuests(1)
                .totalPrice(45)
                .isCheckedIn(false)
                .rooms(null)
                .build();

        ReservationDTO reservation2DTO = ReservationDTO.builder()
                .id(2L)
                .reservationDate(LocalDate.now())
                .checkInDate(LocalDate.now().plusDays(10))
                .checkOutDate(LocalDate.now().plusDays(11))
                .amountOfGuests(1)
                .totalPrice(100)
                .isCheckedIn(false)
                .rooms(null)
                .build();

        GetReservationsResponseDTO expectedResult = GetReservationsResponseDTO.builder()
                .reservations(List.of(reservation1DTO, reservation2DTO)).build();

        assertEquals(expectedResult, actualResult);
        verify(reservationRepositoryMock).findAll();
    }

    @Test
    void getReservationsByGuest_shouldReturnAllReservationsByGuestID() {
        when(guestRepository.findById(1L)).thenReturn(Optional.of(Guest.builder().id(1L).build()));
        Reservation reservation1 = Reservation.builder()
                .id(1L)
                .build();

        Reservation reservation2 = Reservation.builder()
                .id(2L)
                .build();

        when(reservationRepositoryMock.findAllByGuest(Guest.builder().id(1L).build())).thenReturn(List.of(reservation1, reservation2));

        GetReservationsResponseDTO actualResult = reservationService.getReservationsByGuest(1L);

        ReservationDTO reservation1DTO = ReservationDTO.builder()
                .id(1L)
                .build();

        ReservationDTO reservation2DTO = ReservationDTO.builder()
                .id(2L)
                .build();

        GetReservationsResponseDTO expectedResult = GetReservationsResponseDTO.builder()
                .reservations(List.of(reservation1DTO, reservation2DTO)).build();

        assertEquals(expectedResult, actualResult);
        verify(reservationRepositoryMock).findAllByGuest(Guest.builder().id(1L).build());
    }

    @Test
    void getReservationsByGuest_shouldThrowInvalidGuestException_whenGuestNotFound(){
        InvalidGuestException exception = assertThrows(InvalidGuestException.class,
                () -> reservationService.getReservationsByGuest(1L));

        assertEquals("GUEST_NOT_FOUND", exception.getReason());
        verifyNoInteractions(reservationRepositoryMock);
    }

    @Test
    void reservationCheckIn_shouldUpdateCheckInStatusToTrue() {
        when(accessTokenDTO.hasRole(RoleEnum.EMPLOYEE.name())).thenReturn(true);
        Reservation checkedIn = Reservation.builder()
                .id(1L)
                .isCheckedIn(true)
                .build();

        when(reservationRepositoryMock.findById(1L)).thenReturn(Optional.of(checkedIn));
        ReservationCheckInRequestDTO request = ReservationCheckInRequestDTO.builder()
                .id(1L)
                .build();

        reservationService.reservationCheckIn(request);
        verify(reservationRepositoryMock).save(checkedIn);
    }
    @Test
    void reservationCheckIn_shouldUpdateCheckInStatusToFalse() {
        when(accessTokenDTO.hasRole(RoleEnum.EMPLOYEE.name())).thenReturn(true);
        Reservation checkedIn = Reservation.builder()
                .id(1L)
                .isCheckedIn(false)
                .build();

        when(reservationRepositoryMock.findById(1L)).thenReturn(Optional.of(checkedIn));
        ReservationCheckInRequestDTO request = ReservationCheckInRequestDTO.builder()
                .id(1L)
                .build();

        reservationService.reservationCheckIn(request);
        verify(reservationRepositoryMock).save(checkedIn);
    }

    @Test
    void reservationCheckIn_shouldThrowInvalidReservationException_whenReservationNotFound(){
        InvalidReservationException exception = assertThrows(InvalidReservationException.class,
                () -> reservationService.reservationCheckIn(ReservationCheckInRequestDTO.builder().id(1L).build()));

        assertEquals("RESERVATION_NOT_FOUND", exception.getReason());
    }

    @Test
    void reservationCheckIn_shouldThrowUnauthorisedDataException_whenLoggedInUserIsNotEmployee(){
        when(reservationRepositoryMock.findById(1L)).thenReturn(Optional.of(Reservation.builder().id(1L).build()));
        when(accessTokenDTO.hasRole(RoleEnum.EMPLOYEE.name())).thenReturn(false);

        UnauthorisedDataAccessException exception = assertThrows(UnauthorisedDataAccessException.class,
                () -> reservationService.reservationCheckIn(ReservationCheckInRequestDTO.builder().id(1L).build()));

        assertEquals("UNAUTHORISED_TO_PERFORM_ACTION", exception.getReason());
    }
}