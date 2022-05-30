package com.fontys.s3itproject.dto;

import com.fontys.s3itproject.repository.entity.Room;
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
    private LocalDate reservationDate;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private int amountOfGuests;
    private double totalPrice;
    private boolean isCheckedIn;
    private int guestID;
    private List<Room> reservationRooms;
}
