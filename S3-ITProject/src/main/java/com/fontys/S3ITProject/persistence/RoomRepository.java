package com.fontys.S3ITProject.persistence;

import com.fontys.S3ITProject.models.Reservation;
import com.fontys.S3ITProject.models.Room;

import java.util.List;

public interface RoomRepository {
    public boolean createRoom(Room r);
    public List<Room> readRooms();
    public List<Room> readAvailableRooms();
    public boolean updateRoom(Room r);
    public boolean deleteRoom(Room r);

    public boolean addRoomToReservation(Room room, Reservation reservation);
    public Room getRoomByID(int id);
}
