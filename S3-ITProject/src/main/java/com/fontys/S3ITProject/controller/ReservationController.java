package com.fontys.s3itproject.controller;

import com.fontys.s3itproject.business.ReservationService;
import com.fontys.s3itproject.configuration.security.isauthenticated.IsAuthenticated;
import com.fontys.s3itproject.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
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

    @GetMapping
    public ResponseEntity<GetReservationsResponseDTO> getReservations(){
       return ResponseEntity.ok(reservationService.getReservations());
    }

    @IsAuthenticated
    @RolesAllowed({"ROLE_GUEST"})
    @GetMapping(path = "{id}")
    public ResponseEntity<GetReservationsResponseDTO> getReservationsByGuest(@PathVariable(value = "id") long id){
        return ResponseEntity.ok(reservationService.getReservationsByGuest(id));
    }

    @IsAuthenticated
    @RolesAllowed({"ROLE_EMPLOYEE"})
    @PutMapping("{id}")
    public ResponseEntity<ReservationDTO> reservationCheckIn(@PathVariable(value = "id") long id){
        ReservationCheckInRequestDTO request = ReservationCheckInRequestDTO.builder()
                        .id(id).build();
        
        reservationService.reservationCheckIn(request);
        return ResponseEntity.noContent().build();
    }
}
