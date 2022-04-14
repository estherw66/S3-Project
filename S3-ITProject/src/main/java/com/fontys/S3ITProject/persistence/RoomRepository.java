package com.fontys.s3itproject.persistence;

import com.fontys.s3itproject.models.Reservation;
import com.fontys.s3itproject.models.Room;
import com.fontys.s3itproject.models.SpecificRoom;
import com.fontys.s3itproject.models.enums.RoomType;

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
