package com.fontys.S3ITProject.Models;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.*;

@Getter
@Setter
public class Reservation {

    private int referenceNumber;
    private LocalDate reservationDate;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private Guest guest;
    private int amountOfGuests;
    private double totalPrice;
    //payment status

    // list of rooms
    private List<Room> roomList;

    public Reservation(int referenceNumber, LocalDate reservationDate, LocalDate checkIn, LocalDate checkOut,
                       Guest guest, int amountOfGuests, double totalPrice){
        this.referenceNumber = referenceNumber;
        this.reservationDate = reservationDate;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.guest = guest;
        this.amountOfGuests = amountOfGuests;
        this.totalPrice = totalPrice;

        this.roomList = new ArrayList<>();
    }
}
