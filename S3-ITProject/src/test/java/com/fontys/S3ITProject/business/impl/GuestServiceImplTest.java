package com.fontys.s3itproject.business.impl;

import com.fontys.s3itproject.business.GuestService;
import com.fontys.s3itproject.business.exception.EmailAlreadyExistsException;
import com.fontys.s3itproject.business.exception.UnauthorisedDataAccessException;
import com.fontys.s3itproject.business.exception.UsernameAlreadyExistsException;
import com.fontys.s3itproject.dto.*;
import com.fontys.s3itproject.repository.GuestRepository;
import com.fontys.s3itproject.repository.UserRepository;
import com.fontys.s3itproject.repository.entity.Guest;
import com.fontys.s3itproject.repository.entity.Reservation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GuestServiceImplTest {

    @Mock
    private GuestRepository guestRepositoryMock;

    @Mock
    private UserRepository userRepositoryMock;

    @Mock
    private AccessTokenDTO accessTokenDTO;

    @InjectMocks
    private GuestServiceImpl guestServiceMock;

    @Test
    void createGuest_shouldSaveNewGuest() {
        when(guestRepositoryMock.existsByEmail("estherwolfs@hotmail.com")).thenReturn(false);
        when(userRepositoryMock.existsByUsername("esther123")).thenReturn(false);

        Guest newGuest = Guest.builder()
                .firstName("Esther")
                .lastName("Wolfs")
                .email("estherwolfs@hotmail.com")
                .build();

        Guest saved = Guest.builder()
                .id(1L)
                .firstName("Esther")
                .lastName("Wolfs")
                .email("estherwolfs@hotmail.com")
                .build();

        when(guestRepositoryMock.save(newGuest)).thenReturn(saved);

        CreateGuestRequestDTO request = CreateGuestRequestDTO.builder()
                .firstName("Esther")
                .lastName("Wolfs")
                .email("estherwolfs@hotmail.com")
                .username("esther123")
                .password("password")
                .build();

        CreateGuestResponseDTO actualResult = guestServiceMock.createGuest(request);
        CreateGuestResponseDTO expectedResult = CreateGuestResponseDTO.builder()
                .guestID(1L).build();

        assertEquals(expectedResult, actualResult);
        verify(guestRepositoryMock).existsByEmail("estherwolfs@hotmail.com");
        verify(userRepositoryMock).existsByUsername("esther123");
        verify(guestRepositoryMock).save(newGuest);
    }

    @Test
    void createGuest_shouldThrowEmailAlreadyExistsException_whenEmailAlreadyExists() {
        when(guestRepositoryMock.existsByEmail("estherwolfs@hotmail.com")).thenReturn(true);

        CreateGuestRequestDTO request = CreateGuestRequestDTO.builder()
                .firstName("Esther")
                .lastName("Wolfs")
                .email("estherwolfs@hotmail.com")
                .username("esther123")
                .password("password")
                .build();

        assertThrows(EmailAlreadyExistsException.class, () -> guestServiceMock.createGuest(request));
        verify(guestRepositoryMock).existsByEmail("estherwolfs@hotmail.com");
        verifyNoInteractions(userRepositoryMock);
        verifyNoMoreInteractions(guestRepositoryMock);
    }

    @Test
    void createGuest_shouldThrowUsernameAlreadyExistsException_whenUsernameAlreadyExists(){
        when(guestRepositoryMock.existsByEmail("estherwolfs@hotmail.com")).thenReturn(false);
        when(userRepositoryMock.existsByUsername("esther123")).thenReturn(true);
        CreateGuestRequestDTO request = CreateGuestRequestDTO.builder()
                .firstName("Esther")
                .lastName("Wolfs")
                .email("estherwolfs@hotmail.com")
                .username("esther123")
                .password("password")
                .build();

        assertThrows(UsernameAlreadyExistsException.class, () -> guestServiceMock.createGuest(request));
        verify(guestRepositoryMock).existsByEmail("estherwolfs@hotmail.com");
        verify(userRepositoryMock).existsByUsername("esther123");
        verifyNoMoreInteractions(guestRepositoryMock);
    }

    @Test
    void getGuests_shouldReturnAllGuestsConvertedToDTO() {
        Reservation reservation = Reservation.builder()
                .id(1L)
                .build();

        Guest esther = Guest.builder()
                .id(1L)
                .firstName("Esther")
                .lastName("Wolfs")
                .email("estherwolfs@hotmail.com")
                .reservations(List.of(reservation))
                .build();
        Guest jeremy = Guest.builder()
                .id(2L)
                .firstName("Jeremy")
                .lastName("Bolm")
                .email("jeremybolm@gmail.com")
                .reservations(List.of(reservation))
                .build();

        when(guestRepositoryMock.findAll()).thenReturn(List.of(esther, jeremy));

        GetGuestsResponseDTO actualResult = guestServiceMock.getGuests();

        ReservationDTO reservationDTO = ReservationDTO.builder()
                .id(1L)
                .build();

        GuestDTO estherDTO = GuestDTO.builder()
                .id(1L)
                .firstName("Esther")
                .lastName("Wolfs")
                .email("estherwolfs@hotmail.com")
                .reservations(List.of(reservationDTO))
                .build();
        GuestDTO jeremyDTO = GuestDTO.builder()
                .id(2L)
                .firstName("Jeremy")
                .lastName("Bolm")
                .email("jeremybolm@gmail.com")
                .reservations(List.of(reservationDTO))
                .build();

        GetGuestsResponseDTO expectedResult = GetGuestsResponseDTO.builder()
                .guests(List.of(estherDTO, jeremyDTO))
                .build();

        assertEquals(expectedResult, actualResult);
        verify(guestRepositoryMock).findAll();
    }

    @Test
    void getGuest_shouldReturnOptionalGuestConvertedToDTO_whenFoundByID() {
        when(accessTokenDTO.getEmployeeId()).thenReturn(1L);

        Reservation reservation = Reservation.builder()
                .id(1L)
                .build();

        Guest guest = Guest.builder()
                .id(1L)
                .firstName("Esther")
                .lastName("Wolfs")
                .email("estherwolfs@hotmail.com")
                .reservations(List.of(reservation))
                .build();

        when(guestRepositoryMock.findById(1L)).thenReturn(Optional.of(guest));

        Optional<GuestDTO> actualResult = guestServiceMock.getGuest(1L);

        ReservationDTO reservationDTO = ReservationDTO.builder()
                .id(1L)
                .build();

        GuestDTO expectedResult = GuestDTO.builder()
                .id(1L)
                .firstName("Esther")
                .lastName("Wolfs")
                .email("estherwolfs@hotmail.com")
                .reservations(List.of(reservationDTO))
                .build();

        assertTrue(actualResult.isPresent());
        assertEquals(expectedResult, actualResult.get());
        verify(guestRepositoryMock).findById(1L);
    }

    @Test
    void getGuest_shouldThrowUnauthorisedDataAccessException_whenGuestIDIsNotFromLoggedInUser(){
        when(accessTokenDTO.getEmployeeId()).thenReturn(1L);

        UnauthorisedDataAccessException exception = assertThrows(UnauthorisedDataAccessException.class,
                () -> guestServiceMock.getGuest(2L));

        assertEquals("GUEST_ID_NOT_FROM_LOGGED_IN_USER", exception.getReason());
        verifyNoInteractions(guestRepositoryMock);
    }

    @Test
    void getGuest_shouldReturnOptionalGuestConvertedToDTO_whenEmployeeLoggedIn(){
        when(accessTokenDTO.hasRole("EMPLOYEE")).thenReturn(true);

        Reservation reservation = Reservation.builder()
                .id(1L)
                .build();

        Guest guest = Guest.builder()
                .id(1L)
                .firstName("Esther")
                .lastName("Wolfs")
                .email("estherwolfs@hotmail.com")
                .reservations(List.of(reservation))
                .build();

        when(guestRepositoryMock.findById(1L)).thenReturn(Optional.of(guest));

        Optional<GuestDTO> actualResult = guestServiceMock.getGuest(1L);

        ReservationDTO reservationDTO = ReservationDTO.builder()
                .id(1L)
                .build();

        GuestDTO expectedResult = GuestDTO.builder()
                .id(1L)
                .firstName("Esther")
                .lastName("Wolfs")
                .email("estherwolfs@hotmail.com")
                .reservations(List.of(reservationDTO))
                .build();

        assertTrue(actualResult.isPresent());
        assertEquals(expectedResult, actualResult.get());
        verify(guestRepositoryMock).findById(1L);
    }

    @Test
    void updateGuest_shouldUpdateGuestEmail_whenGuestIDMatchesLoggedInUser() {
        when(accessTokenDTO.getEmployeeId()).thenReturn(1L);

        Reservation reservation = Reservation.builder()
                .id(1L)
                .build();

        Guest oldGuest = Guest.builder()
                .id(1L)
                .firstName("Esther")
                .lastName("Wolfs")
                .email("estherwolfs@hotmail.com")
                .reservations(List.of(reservation))
                .build();

        when(guestRepositoryMock.findById(1L)).thenReturn(Optional.of(oldGuest));

        UpdateGuestRequestDTO request = UpdateGuestRequestDTO.builder()
                .id(1L)
                .firstName("Esther")
                .lastName("Wolfs")
                .email("estherwolfs@gmail.com")
                .build();
        guestServiceMock.updateGuest(request);

        verify(guestRepositoryMock).findById(1L);

        Guest expectedNewGuest = Guest.builder()
                .id(1L)
                .firstName("Esther")
                .lastName("Wolfs")
                .email("estherwolfs@gmail.com")
                .reservations(List.of(reservation))
                .build();
        verify(guestRepositoryMock).save(oldGuest);
    }
}