package com.fontys.s3itproject.business;

import com.fontys.s3itproject.entity.Reservation;
import com.fontys.s3itproject.entity.Room;
import com.fontys.s3itproject.entity.SpecificRoom;
import com.fontys.s3itproject.entity.enums.RoomType;
import org.springframework.stereotype.Service;


import java.util.List;

public interface RoomService {
    public boolean createRoom(Room r);
    public List<Room> readRooms();
    public List<SpecificRoom> getAvailableRooms();
    public boolean updateRoom(Room r);
    public boolean deleteRoom(Room r);

    public boolean addRoomToReservation(SpecificRoom room, Reservation reservation);
    public double calculatePricePerNight(SpecificRoom room);
    public Room getRoomByID(int id);


    public boolean updatePricePerNight(SpecificRoom room);
    public boolean updateSpecificRoom(SpecificRoom room);

    public List<Room> searchAvailableRoom(RoomType type, int amountOfGuests);
}
