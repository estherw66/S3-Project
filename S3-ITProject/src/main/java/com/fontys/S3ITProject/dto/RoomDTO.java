package com.fontys.s3itproject.dto;

import com.fontys.s3itproject.models.enums.RoomType;
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
