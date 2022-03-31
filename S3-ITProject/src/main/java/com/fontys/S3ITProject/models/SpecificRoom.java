package com.fontys.S3ITProject.models;

import com.fontys.S3ITProject.models.enums.RoomType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SpecificRoom {
    private int id;
    private int roomNumber;
    private double actualPricePerNight;
    private boolean isAvailable;
    private Room roomType;
}
