package com.fontys.S3ITProject.dto;

import com.fontys.S3ITProject.models.User;
import com.fontys.S3ITProject.models.enums.ReservationStatus;
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
