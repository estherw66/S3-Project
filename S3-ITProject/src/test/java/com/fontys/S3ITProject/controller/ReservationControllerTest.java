package com.fontys.s3itproject.controller;

import com.fontys.s3itproject.business.ReservationService;
import com.fontys.s3itproject.dto.*;
import com.fontys.s3itproject.repository.entity.Guest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class ReservationControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ReservationService reservationServiceMock;

    @Test
    void createReservation_shouldCreateReservationAndReturn201Response_whenRequestValid() throws Exception {
        CreateReservationRequestDTO request = CreateReservationRequestDTO.builder()
                .checkInDate(LocalDate.of(2022,10,31))
                .checkOutDate(LocalDate.of(2022,11,2))
                .amountOfGuests(2)
                .guestID(1L)
                .reservationRooms(List.of(RoomDTO.builder().id(1L).capacity(2).pricePerNight(70).build()))
                .build();
        when(reservationServiceMock.createReservation(request))
                .thenReturn(CreateReservationResponseDTO.builder().reservationID(1L).build());

        mockMvc.perform(post("/api/reservations")
                .contentType(APPLICATION_JSON_VALUE)
                .content("""
                            {
                                "checkInDate": "2022-10-31",
                                "checkOutDate": "2022-11-02",
                                "amountOfGuests": 2,
                                "guestID": 1,
                                "reservationRooms": [
                                    {
                                        "id": 1,
                                        "capacity": 2,
                                        "pricePerNight": 70
                                    }
                                ]
                            }
                        """))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json("""
                            {"reservationID":  1}
                        """));
        verify(reservationServiceMock).createReservation(request);
    }

    @Test
    void createReservation_shouldNotCreateReservationAndReturn400Response_whenMissingFields() throws Exception {
        mockMvc.perform(post("/api/reservations")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content("""
                            {
                                "checkInDate": "",
                                "checkOutDate": "",
                                "amountOfGuests": 0,
                                "guestID": 0,
                                "reservationRooms": [
                                    
                                ]
                            }
                        """))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                            [
                                {"field":  "checkInDate", "error":  "must not be null"},
                                {"field":  "checkOutDate", "error":  "must not be null"},
                                {"field":  "amountOfGuests", "error":  "must be greater than or equal to 1"}
                            ]
                    """));
        verifyNoInteractions(reservationServiceMock);
    }

    @Test
    @WithMockUser(username = "Esther", roles = {"EMPLOYEE"})
    void getReservations_shouldReturn200ResponseWithArrayOfAllReservations() throws Exception {
        GetReservationsResponseDTO response = GetReservationsResponseDTO.builder().reservations(List.of(
                ReservationDTO.builder().id(1L).build(),
                ReservationDTO.builder().id(2L).build()
        )).build();
        when(reservationServiceMock.getReservations()).thenReturn(response);

        mockMvc.perform(get("/api/reservations"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                            {
                                "reservations": [
                                    {"id":  1}, {"id":  2}
                                ]
                            }
                        """));
        verify(reservationServiceMock).getReservations();
    }

    @Test
    @WithMockUser(username = "John", roles = {"GUEST"})
    void getReservationsByGuest_shouldReturn200ResponseWithArrayOfGuestReservations() throws Exception {
        GetReservationsResponseDTO response = GetReservationsResponseDTO.builder().reservations(List.of(
                ReservationDTO.builder().id(1L).guest(GuestReservationDTO.builder().guestID(1L).build()).build(),
                ReservationDTO.builder().id(2L).guest(GuestReservationDTO.builder().guestID(1L).build()).build()
        )).build();
        when(reservationServiceMock.getReservationsByGuest(1L)).thenReturn(response);

        mockMvc.perform(get("/api/reservations/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                            {
                                "reservations": [
                                    {"id":  1}, {"id":  2}
                                ]
                            }
                        """));
        verify(reservationServiceMock).getReservationsByGuest(1L);
    }

    @Test
    @WithMockUser(username = "Esther", roles = {"EMPLOYEE"})
    void reservationCheckIn_shouldUpdateCheckInStatusOfReservation() throws Exception {
        mockMvc.perform(put("/api/reservations/1"))
                .andDo(print())
                .andExpect(status().isNoContent());

        ReservationCheckInRequestDTO expectedRequest = ReservationCheckInRequestDTO.builder()
                .id(1L)
                .build();

        verify(reservationServiceMock).reservationCheckIn(expectedRequest);
    }
}