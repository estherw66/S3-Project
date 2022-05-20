package com.fontys.s3itproject.business.impl;

import com.fontys.s3itproject.dto.CreateRoomRequestDTO;
import com.fontys.s3itproject.dto.CreateRoomResponseDTO;
import com.fontys.s3itproject.dto.GetRoomsResponseDTO;
import com.fontys.s3itproject.dto.RoomDTO;
import com.fontys.s3itproject.repository.RoomRepository;
import com.fontys.s3itproject.repository.entity.Room;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith({MockitoExtension.class})
class RoomServiceImplTest {

    @Mock
    private RoomRepository roomRepositoryMock;

    @InjectMocks
    private RoomServiceImpl roomService;

    @Test
    void createRoom_shouldSaveRoom() {
        Room newRoom = Room.builder()
                .capacity(1)
                .pricePerNight(50)
                .imageUrl("")
                .roomType("Single")
                .isFeatured(false)
                .totalAmountInHotel(10)
                .build();
        Room saved = Room.builder()
                .id(1L)
                .capacity(1)
                .pricePerNight(50)
                .imageUrl("")
                .roomType("Single")
                .isFeatured(false)
                .totalAmountInHotel(10)
                .build();

        when(roomRepositoryMock.save(newRoom))
                .thenReturn(saved);

        CreateRoomRequestDTO request = CreateRoomRequestDTO.builder()
                .capacity(1)
                .pricePerNight(50)
                .imageUrl("")
                .roomType("Single")
                .isFeatured(false)
                .totalAmountInHotel(10)
                .build();

        CreateRoomResponseDTO actualResult = roomService.createRoom(request);

        CreateRoomResponseDTO expectedResult = CreateRoomResponseDTO.builder()
                .roomID(1L)
                .build();

        assertEquals(expectedResult, actualResult);
        verify(roomRepositoryMock).save(newRoom);
    }

    @Test
    void getRooms_shouldReturnAllRoomsConvertedToDTO() {
        Room single = Room.builder()
                .id(1L)
                .capacity(1)
                .pricePerNight(50)
                .imageUrl("")
                .roomType("Single")
                .isFeatured(false)
                .totalAmountInHotel(10)
                .build();
        Room singleXXL = Room.builder()
                .id(2L)
                .capacity(1)
                .pricePerNight(115)
                .imageUrl("")
                .roomType("Single XXL")
                .isFeatured(true)
                .totalAmountInHotel(5)
                .build();

        when(roomRepositoryMock.findAll())
                .thenReturn(List.of(single, singleXXL));

        GetRoomsResponseDTO actualResult = roomService.getRooms();

        RoomDTO singleDTO = RoomDTO.builder()
                .id(1L)
                .capacity(1)
                .pricePerNight(50)
                .imageUrl("")
                .roomType("Single")
                .isFeatured(false)
                .totalAmountInHotel(10)
                .build();
        RoomDTO singleXXLDTO = RoomDTO.builder()
                .id(2L)
                .capacity(1)
                .pricePerNight(115)
                .imageUrl("")
                .roomType("Single XXL")
                .isFeatured(true)
                .totalAmountInHotel(5)
                .build();

        GetRoomsResponseDTO expectedResult = GetRoomsResponseDTO.builder()
                .rooms(List.of(singleDTO, singleXXLDTO))
                .build();

        assertEquals(expectedResult, actualResult);
        verify(roomRepositoryMock).findAll();
    }

    @Test
    void getRooms_shouldReturnAllFeaturedRoomsConvertedToDTO() {
        Room singleXXL = Room.builder()
                .id(2L)
                .capacity(1)
                .pricePerNight(115)
                .imageUrl("")
                .roomType("Single XXL")
                .isFeatured(true)
                .totalAmountInHotel(10)
                .build();

        when(roomRepositoryMock.findAll())
                .thenReturn(List.of(singleXXL));

        GetRoomsResponseDTO actualResult = roomService.getFeaturedRooms();

        RoomDTO singleXXLDTO = RoomDTO.builder()
                .id(2L)
                .capacity(1)
                .pricePerNight(115)
                .imageUrl("")
                .roomType("Single XXL")
                .isFeatured(true)
                .totalAmountInHotel(10)
                .build();

        GetRoomsResponseDTO expectedResult = GetRoomsResponseDTO.builder()
                .rooms(List.of(singleXXLDTO))
                .build();

        assertEquals(expectedResult, actualResult);
        verify(roomRepositoryMock).findAll();
    }
}