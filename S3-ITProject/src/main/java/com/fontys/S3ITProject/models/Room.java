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
public class Room {

    private int id;
    private int maxCapacity;
    private double basePricePerNight;
    private RoomType type;
    private String imgUrl;
}
