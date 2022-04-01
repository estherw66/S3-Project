package com.fontys.S3ITProject.dto;

import com.fontys.S3ITProject.models.enums.RoomType;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RoomDTO {
    private int id;
    private int maxCapacity;
    private double basePricePerNight;
    private RoomType type;
}
