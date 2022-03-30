package com.fontys.S3ITProject.business;

import com.fontys.S3ITProject.models.Reservation;
import com.fontys.S3ITProject.models.Room;

import java.util.List;

public interface RoomService {
    public boolean createRoom(Room r);
    public List<Room> readRooms();
    public List<Room> readAvailableRooms();
    public boolean updateRoom(Room r);
    public boolean deleteRoom(Room r);

    public boolean addRoomToReservation(Room room, Reservation reservation);
    public double calculatePricePerNight(Room room);
}
