package com.fontys.S3ITProject.persistence;

import com.fontys.S3ITProject.models.Reservation;
import com.fontys.S3ITProject.models.Room;
import com.fontys.S3ITProject.models.SpecificRoom;
import com.fontys.S3ITProject.models.enums.RoomType;

import java.util.List;

public interface RoomRepository {
    public boolean createRoom(Room r);
    public List<Room> readRooms();
    public List<SpecificRoom> getAvailableRooms();
    public boolean updateRoom(Room r);
    public boolean deleteRoom(Room r);

    public boolean addRoomToReservation(SpecificRoom room, Reservation reservation);
    public Room getRoomByID(int id);
    public SpecificRoom getSpecificRoomByID(int id);

    public boolean updateSpecificRoom(SpecificRoom room);

    public List<Room> searchAvailableRoom(RoomType type, int amountOfGuests);
}
