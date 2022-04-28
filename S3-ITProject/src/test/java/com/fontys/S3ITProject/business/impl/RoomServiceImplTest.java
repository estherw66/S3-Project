package com.fontys.s3itproject.business.impl;

import com.fontys.s3itproject.dto.GetRoomsResponseDTO;
import com.fontys.s3itproject.dto.RoomDTO;
import com.fontys.s3itproject.repository.RoomRepository;
import com.fontys.s3itproject.repository.entity.Room;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RoomServiceImplTest {

    @Test
    void createRoom() {
    }

    @Test
    void getRooms_shouldReturnAllRoomsConvertedToDTO() {
        RoomRepository roomRepositoryMock = mock(RoomRepository.class);

        Room single = Room.builder()
                .id(1L)
                .capacity(1)
                .pricePerNight(50)
                .imageUrl("")
                .roomType("Single")
                .isFeatured(false)
                .build();
        Room singleXXL = Room.builder()
                .id(2L)
                .capacity(1)
                .pricePerNight(115)
                .imageUrl("")
                .roomType("Single XXL")
                .isFeatured(true)
                .build();

        when(roomRepositoryMock.findAll())
                .thenReturn(List.of(single, singleXXL));

        RoomServiceImpl roomService = new RoomServiceImpl(roomRepositoryMock);

        GetRoomsResponseDTO actualResult = roomService.getRooms();

        RoomDTO singleDTO = RoomDTO.builder()
                .id(1L)
                .capacity(1)
                .pricePerNight(50)
                .imageUrl("")
                .roomType("Single")
                .isFeatured(false)
                .build();
        RoomDTO singleXXLDTO = RoomDTO.builder()
                .id(2L)
                .capacity(1)
                .pricePerNight(115)
                .imageUrl("")
                .roomType("Single XXL")
                .isFeatured(true)
                .build();

        GetRoomsResponseDTO expectedResult = GetRoomsResponseDTO.builder()
                .rooms(List.of(singleDTO, singleXXLDTO))
                .build();

        assertEquals(expectedResult, actualResult);
        verify(roomRepositoryMock).findAll();
    }
}