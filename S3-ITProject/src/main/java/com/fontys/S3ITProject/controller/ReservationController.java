package com.fontys.s3itproject.controller;

import com.fontys.s3itproject.business.ReservationService;
import com.fontys.s3itproject.persistence.entity.Reservation;
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

}
