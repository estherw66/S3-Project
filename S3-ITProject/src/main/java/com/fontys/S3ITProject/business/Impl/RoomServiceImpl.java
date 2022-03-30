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
        return false;
    }

    @Override
    public List<Room> readRooms() {
        return null;
    }

    @Override
    public List<Room> readAvailableRooms() {
        return null;
    }

    @Override
    public boolean updateRoom(Room r) {
        return false;
    }

    @Override
    public boolean deleteRoom(Room r) {
        return false;
    }

    @Override
    public boolean addRoomToReservation(Room room, Reservation reservation) {
        return false;
    }
}
