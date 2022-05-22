package com.fontys.s3itproject.business.impl;

import com.fontys.s3itproject.business.exception.InvalidRoomException;
import com.fontys.s3itproject.business.exception.UnauthorisedDataAccessException;
import com.fontys.s3itproject.dto.*;
import com.fontys.s3itproject.repository.RoomRepository;
import com.fontys.s3itproject.repository.entity.Room;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith({MockitoExtension.class})
class RoomServiceImplTest {

    @Mock
    private RoomRepository roomRepositoryMock;

    @Mock
    private AccessTokenDTO accessTokenMock = AccessTokenDTO.builder()
        .employeeId(1L)
        .roles(List.of("EMPLOYEE"))
        .subject("Esther")
        .build();

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

    @Test
    void updateRoom_shouldSaveNewPrice(){
        when(accessTokenMock.hasRole("EMPLOYEE")).thenReturn(true);

        Room oldRoom = Room.builder()
                .id(1L)
                .capacity(1)
                .pricePerNight(50)
                .imageUrl("")
                .roomType("Single")
                .isFeatured(false)
                .totalAmountInHotel(10)
                .build();

        when(roomRepositoryMock.findById(1L)).thenReturn(Optional.of(oldRoom));

        UpdateRoomRequestDTO request = UpdateRoomRequestDTO.builder()
                .id(1L)
                .pricePerNight(75)
                .imageUrl("")
                .isFeatured(false)
                .totalAmountInHotel(10)
                .build();
        roomService.updateRoom(request);

        verify(roomRepositoryMock).findById(1L);

        Room expectedNewRoom = Room.builder()
                .id(1L)
                .capacity(1)
                .pricePerNight(75)
                .imageUrl("")
                .roomType("Single")
                .isFeatured(false)
                .totalAmountInHotel(10)
                .build();
        verify(roomRepositoryMock).save(expectedNewRoom);
    }

    @Test
    void updateRoom_shouldThrowUnauthorisedDataAccessException_whenUpdateRoomEmployeeNotLoggedIn(){
        Room oldRoom = Room.builder()
                .id(1L)
                .capacity(1)
                .pricePerNight(50)
                .imageUrl("")
                .roomType("Single")
                .isFeatured(false)
                .totalAmountInHotel(10)
                .build();

        when(roomRepositoryMock.findById(1L)).thenReturn(Optional.of(oldRoom));
        when(accessTokenMock.hasRole("EMPLOYEE")).thenReturn(false);

        UpdateRoomRequestDTO request = UpdateRoomRequestDTO.builder()
                .id(1L)
                .pricePerNight(50)
                .imageUrl("")
                .isFeatured(false)
                .totalAmountInHotel(10)
                .build();

        UnauthorisedDataAccessException exception = assertThrows(UnauthorisedDataAccessException.class,
                () -> roomService.updateRoom(request));

        assertEquals("NOT_AN_EMPLOYEE", exception.getReason());
        verifyNoMoreInteractions(roomRepositoryMock);
    }
}