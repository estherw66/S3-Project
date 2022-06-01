package com.fontys.s3itproject.controller;

import com.fontys.s3itproject.business.ReservationService;
import com.fontys.s3itproject.dto.CreateReservationRequestDTO;
import com.fontys.s3itproject.dto.CreateReservationResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reservations")
@CrossOrigin(origins = "http://localhost:3000")
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping
    public ResponseEntity<CreateReservationResponseDTO> createReservation(
            @RequestBody @Valid CreateReservationRequestDTO createReservationRequest){
        CreateReservationResponseDTO response = reservationService.createReservation(createReservationRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

//    @PostMapping
//    public ResponseEntity<CreateEmployeeResponseDTO> createEmployee(
//            @RequestBody @Valid CreateEmployeeRequestDTO createEmployeeRequest){
//        CreateEmployeeResponseDTO response = employeeService.createEmployee(createEmployeeRequest);
//        return ResponseEntity.status(HttpStatus.CREATED).body(response);
//    }
}
