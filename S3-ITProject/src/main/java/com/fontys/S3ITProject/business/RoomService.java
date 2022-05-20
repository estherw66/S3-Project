package com.fontys.s3itproject.business;

import com.fontys.s3itproject.dto.CreateRoomRequestDTO;
import com.fontys.s3itproject.dto.CreateRoomResponseDTO;
import com.fontys.s3itproject.dto.GetRoomsResponseDTO;
import com.fontys.s3itproject.dto.UpdateRoomRequestDTO;

public interface RoomService {
    CreateRoomResponseDTO createRoom(CreateRoomRequestDTO request);
    GetRoomsResponseDTO getRooms();
    GetRoomsResponseDTO getFeaturedRooms();
    void updateRoom(UpdateRoomRequestDTO request);
}
