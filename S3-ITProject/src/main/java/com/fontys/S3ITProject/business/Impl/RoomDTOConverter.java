package com.fontys.s3itproject.business.impl;

import com.fontys.s3itproject.dto.RoomDTO;
import com.fontys.s3itproject.repository.entity.Room;

public class RoomDTOConverter {
    private RoomDTOConverter() {
    }

    public static RoomDTO convertToDTO(Room room){
        return RoomDTO.builder()
                .id(room.getId())
                .capacity(room.getCapacity())
                .pricePerNight(room.getPricePerNight())
                .imageUrl(room.getImageUrl())
                .roomType(room.getRoomType())
                .isFeatured(room.isFeatured())
                .totalAmountInHotel(room.getTotalAmountInHotel())
                .build();
    }
}
