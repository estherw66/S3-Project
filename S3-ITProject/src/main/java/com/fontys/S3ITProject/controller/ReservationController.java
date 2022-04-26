package com.fontys.s3itproject.controller;

import com.fontys.s3itproject.business.ReservationService;
import com.fontys.s3itproject.entity.Reservation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reservations")
public class ReservationController {
    private final ReservationService reservationService;


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
            return ResponseEntity.created(uri).body(reservation);
        } else{
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
