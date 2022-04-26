package com.fontys.s3itproject.business.impl;

import com.fontys.s3itproject.business.RoomService;
import com.fontys.s3itproject.entity.Reservation;
import com.fontys.s3itproject.entity.Room;
import com.fontys.s3itproject.entity.SpecificRoom;
import com.fontys.s3itproject.entity.enums.RoomType;
import com.fontys.s3itproject.persistence.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Primary
@RequiredArgsConstructor
@Service
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepo;

    @Override
    public boolean createRoom(Room r) {
        return this.roomRepo.createRoom(r);
    }

    @Override
    public List<Room> readRooms() {
        return this.roomRepo.readRooms();
    }

    @Override
    public List<SpecificRoom> getAvailableRooms() {
        return this.roomRepo.getAvailableRooms();
    }

    @Override
    public boolean updateRoom(Room r) {
        return this.roomRepo.updateRoom(r);
    }

    @Override
    public boolean deleteRoom(Room r) {
        return this.roomRepo.deleteRoom(r);
    }

    @Override
    public boolean addRoomToReservation(SpecificRoom room, Reservation reservation) {
        return false;
    }

    @Override
    public double calculatePricePerNight(SpecificRoom room) {
        return 150;
    }

    @Override
    public Room getRoomByID(int id) {
        return this.roomRepo.getRoomByID(id);
    }

    @Override
    public boolean updatePricePerNight(SpecificRoom room) {
        room.setPricePerNight(calculatePricePerNight(room));

        return updateSpecificRoom(room);
    }

    @Override
    public boolean updateSpecificRoom(SpecificRoom room) {
        return this.roomRepo.updateSpecificRoom(room);
    }

    @Override
    public List<Room> searchAvailableRoom(RoomType type, int amountOfGuests) {
        return this.roomRepo.searchAvailableRoom(type, amountOfGuests);
    }
}
