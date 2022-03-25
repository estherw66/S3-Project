package com.fontys.S3ITProject.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Room {

    private int roomNumber;
    //private RoomType type;
    private double pricePerNight;
    private int maxCapacity;
    private boolean isAvailable;

    public Room(int roomNumber, double pricePerNight, int maxCapacity, boolean isAvailable){
        this.roomNumber = roomNumber;
        this.pricePerNight = pricePerNight;
        this.maxCapacity = maxCapacity;
        this.isAvailable = isAvailable;
    }
}
