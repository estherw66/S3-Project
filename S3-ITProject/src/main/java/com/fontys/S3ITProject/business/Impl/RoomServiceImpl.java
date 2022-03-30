package com.fontys.S3ITProject.business.Impl;

import com.fontys.S3ITProject.business.RoomService;
import com.fontys.S3ITProject.models.Reservation;
import com.fontys.S3ITProject.models.Room;
import com.fontys.S3ITProject.persistence.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepo;

    public RoomServiceImpl(RoomRepository roomRepo) {
        this.roomRepo = roomRepo;
    }

    @Override
    public boolean createRoom(Room r) {
        return this.roomRepo.createRoom(r);
    }

    @Override
    public List<Room> readRooms() {
        return this.roomRepo.readRooms();
    }

    @Override
    public List<Room> readAvailableRooms() {
        return this.roomRepo.readAvailableRooms();
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
    public boolean addRoomToReservation(Room room, Reservation reservation) {
        return false;
    }

    @Override
    public double calculatePricePerNight(Room room) {
        return 0;
    }
}
