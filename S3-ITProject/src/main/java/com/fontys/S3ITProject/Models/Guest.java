package com.fontys.S3ITProject.Models;

import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class Guest extends Person{

    //list of reservations
    private List<Reservation> reservationList;

    private PaymentInfo paymentInfo;

    public Guest(String firstName, String lastName, String email, String password, PaymentInfo paymentInfo) {
        super(firstName, lastName, email, password);

        this.paymentInfo = paymentInfo;
        this.reservationList = new ArrayList<>();
    }
}
