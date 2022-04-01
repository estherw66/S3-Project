package com.fontys.S3ITProject.controller;

import com.fontys.S3ITProject.business.ReservationService;
import com.fontys.S3ITProject.models.Reservation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {
    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public ResponseEntity<List<Reservation>> getAllReservations(){
        List<Reservation> reservations = reservationService.readAllReservations();

        if (reservations != null){
            return ResponseEntity.ok().body(reservations);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
