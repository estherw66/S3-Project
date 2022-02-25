package com.fontys.S3ITProject.Models;

import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class Guest extends Person{

    //list of reservations
    private List<Reservation> reservationList;

    private int accountNumber;
    private PaymentInfo paymentInfo;

    public Guest(int accountNumber, String firstName, String lastName, String email, String password, PaymentInfo paymentInfo) {
        super(firstName, lastName, email, password);

        this.accountNumber = accountNumber;
        this.paymentInfo = paymentInfo;
        this.reservationList = new ArrayList<>();
    }
}
