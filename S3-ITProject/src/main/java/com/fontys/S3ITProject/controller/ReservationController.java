package com.fontys.S3ITProject.controller;

import com.fontys.S3ITProject.business.ReservationService;

public class ReservationController {
    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }
}
