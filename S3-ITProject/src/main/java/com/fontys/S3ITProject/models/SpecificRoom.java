package com.fontys.S3ITProject.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SpecificRoom {
    private int id;
    private int roomNumber;
    private double actualPricePerNight;
    private boolean isAvailable;
    private Room roomType;

    public SpecificRoom(){

    }

    public SpecificRoom(int id, int roomNumber, boolean isAvailable, Room roomType){
        this.id = id;
        this.roomNumber = roomNumber;
        this.isAvailable = isAvailable;
        this.roomType = roomType;
        this.actualPricePerNight = roomType.getBasePricePerNight();
    }
}
