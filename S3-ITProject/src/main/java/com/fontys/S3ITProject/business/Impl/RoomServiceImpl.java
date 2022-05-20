package com.fontys.s3itproject.business.impl;

import com.fontys.s3itproject.business.RoomService;
import com.fontys.s3itproject.dto.*;
import com.fontys.s3itproject.repository.RoomRepository;
import com.fontys.s3itproject.repository.entity.Room;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    @Override
    public CreateRoomResponseDTO createRoom(CreateRoomRequestDTO request) {

        Room newRoom = Room.builder()
                .capacity(request.getCapacity())
                .pricePerNight(request.getPricePerNight())
                .imageUrl(request.getImageUrl())
                .roomType(request.getRoomType())
                .isFeatured(request.isFeatured())
                .totalAmountInHotel(request.getTotalAmountInHotel())
                .build();

        Room savedRoom = save(newRoom);

        return CreateRoomResponseDTO.builder()
                .roomID(savedRoom.getId())
                .build();
    }

    @Override
    public GetRoomsResponseDTO getRooms() {
        List<RoomDTO> rooms = findAll()
                .stream()
                .map(RoomDTOConverter::convertToDTO)
                .toList();

        return GetRoomsResponseDTO.builder()
                .rooms(rooms)
                .build();
    }

    @Override
    public GetRoomsResponseDTO getFeaturedRooms() {
        List<RoomDTO> featuredRooms = findAll()
                .stream()
                .filter(Room::isFeatured)
                .map(RoomDTOConverter::convertToDTO)
                .toList();

        return GetRoomsResponseDTO.builder()
                .rooms(featuredRooms)
                .build();
    }

    @Override
    public void updateRoom(UpdateRoomRequestDTO request) {
        
    }

    private Room save(Room room) {
        return roomRepository.save(room);
    }

    private List<Room> findAll(){
        return roomRepository.findAll();
    }
}
