package com.fontys.s3itproject.persistence;

import com.fontys.s3itproject.entity.Reservation;
import com.fontys.s3itproject.entity.Room;
import com.fontys.s3itproject.entity.SpecificRoom;
import com.fontys.s3itproject.entity.enums.RoomType;
import org.springframework.stereotype.Repository;

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
