package com.fontys.s3itproject.repository.entity;

import lombok.*;

import javax.persistence.*;


@Entity
@Table(name = "reservation")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /*
    private LocalDate reservationDate;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private User mainGuest;
    private int amountOfGuests;
    private double totalPrice;
    private ReservationStatusEnum status;
    */
}
