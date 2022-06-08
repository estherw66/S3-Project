package com.fontys.s3itproject.business.impl;

import com.fontys.s3itproject.business.RoomService;
import com.fontys.s3itproject.business.exception.InvalidRoomException;
import com.fontys.s3itproject.business.exception.UnauthorisedDataAccessException;
import com.fontys.s3itproject.dto.*;
import com.fontys.s3itproject.repository.RoomRepository;
import com.fontys.s3itproject.repository.entity.RoleEnum;
import com.fontys.s3itproject.repository.entity.Room;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    private AccessTokenDTO requestAccessToken;

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
        Optional<Room> roomOptional = roomRepository.findById(request.getId());

        if (roomOptional.isEmpty()){
            throw new InvalidRoomException("ROOM_NOT_FOUND");
        }

        if (!requestAccessToken.hasRole(RoleEnum.EMPLOYEE.name())){
            throw new UnauthorisedDataAccessException("NOT_AN_EMPLOYEE");
        }

        Room room = roomOptional.get();
        updateFields(request, room);
    }

    @Override
    public Optional<RoomDTO> getRoom(Long id) {
        if (!requestAccessToken.hasRole(RoleEnum.EMPLOYEE.name())){
            throw new UnauthorisedDataAccessException("UNAUTHORISED_TO_PERFORM_ACTION");
        }

        return roomRepository.findById(id).map(RoomDTOConverter::convertToDTO);
    }

    private void updateFields(UpdateRoomRequestDTO request, Room room){
        room.setPricePerNight(request.getPricePerNight());
        room.setImageUrl(request.getImageUrl());
        room.setTotalAmountInHotel(request.getTotalAmountInHotel());

        roomRepository.save(room);
    }

    private Room save(Room room) {
        return roomRepository.save(room);
    }

    private List<Room> findAll(){
        return roomRepository.findAll();
    }
}
