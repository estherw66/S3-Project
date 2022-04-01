package com.fontys.S3ITProject.business;

import com.fontys.S3ITProject.models.Reservation;
import com.fontys.S3ITProject.models.Room;
import com.fontys.S3ITProject.models.SpecificRoom;

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
}
