package com.fontys.S3ITProject.models;

import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class Guest extends Person{

    //list of reservations
    private List<Reservation> reservationList;

    private PaymentInfo paymentInfo;

    public Guest(int id, String firstName, String lastName, String email, String password) {
        super(id, firstName, lastName, email, password);

        //this.paymentInfo = paymentInfo;
        this.reservationList = new ArrayList<>();
    }
}
