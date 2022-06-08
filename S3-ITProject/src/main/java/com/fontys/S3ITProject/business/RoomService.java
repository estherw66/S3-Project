package com.fontys.s3itproject.business;

import com.fontys.s3itproject.dto.*;

import java.util.Optional;

public interface RoomService {
    CreateRoomResponseDTO createRoom(CreateRoomRequestDTO request);
    GetRoomsResponseDTO getRooms();
    GetRoomsResponseDTO getFeaturedRooms();
    void updateRoom(UpdateRoomRequestDTO request);
    Optional<RoomDTO> getRoom(Long id);
}
