package com.fontys.S3ITProject.controller;

import com.fontys.S3ITProject.business.ReservationService;
import com.fontys.S3ITProject.models.Reservation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {
    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }


    // get all reservations
    @GetMapping
    public ResponseEntity<List<Reservation>> getAllReservations(){
        List<Reservation> reservations = reservationService.readAllReservations();

        if (reservations != null){
            return ResponseEntity.ok().body(reservations);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // create reservation
    @PostMapping
    public ResponseEntity<Reservation>  createReservation(@RequestBody Reservation reservation){
        if (reservationService.createReservation(reservation)){
            String url = "api" + "/" + "reservations" + "/" + reservation.getId();
            URI uri = URI.create(url);
            return new ResponseEntity(uri, HttpStatus.CREATED);
        } else{
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
    }
}
