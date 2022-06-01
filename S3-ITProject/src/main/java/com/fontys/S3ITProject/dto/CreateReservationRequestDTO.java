package com.fontys.s3itproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateReservationRequestDTO {
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private int amountOfGuests;
    private Long guestID;
    private List<RoomDTO> reservationRooms;
}
