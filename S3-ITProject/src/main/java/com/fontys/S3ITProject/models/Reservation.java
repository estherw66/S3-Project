package com.fontys.s3itproject.models;

import com.fontys.s3itproject.models.enums.ReservationStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.*;

@Getter
@Setter
public class Reservation {

    private int id;
    private LocalDate reservationDate;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private User mainGuest;
    private int amountOfGuests;
    private double totalPrice;
    private ReservationStatus status;
    private SpecificRoom room;

    // list of rooms
    private List<SpecificRoom> roomList;

    public Reservation(){
        this.roomList = new ArrayList<>();
    }

    public Reservation(int id, LocalDate reservationDate, LocalDate checkIn, LocalDate checkOut,
    User mainGuest, int amountOfGuests, double totalPrice){
        this.roomList = new ArrayList<>();

        this.id = id;
        this.reservationDate = reservationDate;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.mainGuest = mainGuest;
        this.amountOfGuests = amountOfGuests;
        this.totalPrice = totalPrice;
    }

    public User getUser(){
        if (mainGuest != null){
            return mainGuest;
        }

        return null;
    }


}
