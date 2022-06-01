package com.fontys.s3itproject.repository.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;


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

    @NotNull
    @Column(name = "reservation_date")
    private LocalDate reservationDate;

    @NotNull
    @Column(name = "check_in")
    private LocalDate checkInDate;

    @NotNull
    @Column(name = "check_out")
    private LocalDate checkOutDate;

    @NotNull
    @Column(name = "amount_of_guests")
    private int amountOfGuests;

    @NotNull
    @Min(45)
    @Column(name = "total_price")
    private double totalPrice;

//    @NotNull
//    @Column(name = "reservation_status")
//    private ReservationStatusEnum reservationStatus;

    @NotNull
    @Column(name = "is_checked_in")
    private boolean isCheckedIn;

    @ManyToOne
    @JoinColumn(name = "guest_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Guest guest;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "reservation_id")
    private List<ReservationRoom> reservationRooms;
}
