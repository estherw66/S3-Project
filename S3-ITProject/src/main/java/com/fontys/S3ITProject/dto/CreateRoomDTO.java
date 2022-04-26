package com.fontys.s3itproject.dto;

import com.fontys.s3itproject.entity.enums.RoomType;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateRoomDTO {

    private int maxCapacity;
    private double basePricePerNight;
    private RoomType type;
    private String imgUrl;
}
