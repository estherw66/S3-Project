package com.fontys.s3itproject.entity;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

//@Entity
//@Table(name = "specific_room")
////@Builder
//@Data
@Getter
@Setter
public class SpecificRoom {

//    @Id
//    @Column(name = "id")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

//    @Column(name = "room_number")
//    @NotNull
//    @Min(1)
    private int roomNumber;

//    @Column(name = "price_per_night")
//    @NotNull
//    @Min(1)
    private double pricePerNight;

//    @NotBlank
//    @Column(name = "is_available")
    private boolean isAvailable;

//    @NotBlank
//    @ManyToOne
//    @JoinColumn(name="room_id", nullable=false)
    private Room roomType;

    public SpecificRoom(){

    }

    public SpecificRoom(int id, int roomNumber, boolean isAvailable, Room roomType){
        this.id = id;
        this.roomNumber = roomNumber;
        this.isAvailable = isAvailable;
        this.roomType = roomType;
        this.pricePerNight = roomType.getBasePricePerNight();
    }
}
