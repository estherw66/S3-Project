package com.fontys.s3itproject.persistence.entity;

import com.fontys.s3itproject.persistence.entity.enums.ReservationStatus;
import lombok.*;

import java.time.LocalDate;

//@Entity
//@Table(name = "reservation")
////@Builder
//@Data
@Getter
@Setter
public class Reservation {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
    private Long id;

//    @NotBlank
//    @Column(name = "reservation_date")
    private LocalDate reservationDate;

//    @NotBlank
//    @Column(name = "check_in")
    private LocalDate checkIn;

//    @NotBlank
//    @Column(name = "check_out")
    private LocalDate checkOut;

//    @NotNull
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    @EqualsAndHashCode.Exclude
//    @ToString.Exclude
    private User mainGuest;

//    @NotBlank
//    @Column(name = "amount_of_guests")
//    @Min(1)
//    @Max(10)
//    @EqualsAndHashCode.Exclude
    private int amountOfGuests;

//    @NotBlank
//    @Column(name = "total_price")
//    @Min(1)
//    @EqualsAndHashCode.Exclude
    private double totalPrice;

    private ReservationStatus status;

//    @OneToOne()
    private SpecificRoom room;

    // list of rooms
//    private List<SpecificRoom> roomList;

    public Reservation(){
//        this.roomList = new ArrayList<>();
    }

    public Reservation(Long id, LocalDate reservationDate, LocalDate checkIn, LocalDate checkOut,
    User mainGuest, int amountOfGuests, double totalPrice, SpecificRoom room){
//        this.roomList = new ArrayList<>();

        this.id = id;
        this.reservationDate = reservationDate;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.mainGuest = mainGuest;
        this.amountOfGuests = amountOfGuests;
        this.totalPrice = totalPrice;
        this.room = room;
    }
}
