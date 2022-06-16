package com.fontys.s3itproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDTO {
    private Long id;
    private LocalDate reservationDate;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private int amountOfGuests;
    private GuestReservationDTO guest;
    private double totalPrice;
    private boolean isCheckedIn;
    private List<RoomDTO> rooms;
}
