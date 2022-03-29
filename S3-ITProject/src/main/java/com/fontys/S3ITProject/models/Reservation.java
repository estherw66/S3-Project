package com.fontys.S3ITProject.models;

import com.fontys.S3ITProject.models.enums.ReservationStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.*;

@Getter
@Setter
public class Reservation {

    private int id;
    private int referenceNumber;
    private LocalDate reservationDate;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private User mainGuest;
    private int amountOfGuests;
    private double totalPrice;
    private ReservationStatus status;

    // list of rooms
    private List<Room> roomList;

    public Reservation(){
        this.roomList = new ArrayList<>();
    }

    public Reservation(int id, int referenceNumber, LocalDate reservationDate, LocalDate checkIn, LocalDate checkOut,
    User mainGuest, int amountOfGuests, double totalPrice, ReservationStatus status){
        this.roomList = new ArrayList<>();

        this.id = id;
        this.referenceNumber = referenceNumber;
        this.reservationDate = reservationDate;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.mainGuest = mainGuest;
        this.amountOfGuests = amountOfGuests;
        this.totalPrice = totalPrice;
        this.status = status;
    }
}
