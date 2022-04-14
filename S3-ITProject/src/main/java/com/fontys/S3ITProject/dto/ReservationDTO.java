package com.fontys.s3itproject.dto;

import com.fontys.s3itproject.models.User;
import com.fontys.s3itproject.models.enums.ReservationStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ReservationDTO {
    private int id;
    private int referenceNumber;
    private LocalDate reservationDate;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private User mainGuest;
    private int amountOfGuests;
    private double totalPrice;
    private ReservationStatus status;
}
