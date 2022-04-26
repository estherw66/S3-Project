package com.fontys.s3itproject.persistence.impl;

import com.fontys.s3itproject.entity.Reservation;
import com.fontys.s3itproject.entity.Room;
import com.fontys.s3itproject.entity.SpecificRoom;
import com.fontys.s3itproject.entity.enums.RoomType;
import com.fontys.s3itproject.persistence.RoomRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RoomRepositoryImpl implements RoomRepository {

    private final RepositoryImpl repository;

    public RoomRepositoryImpl(RepositoryImpl repository) {
        this.repository = repository;
    }

    @Override
    public boolean createRoom(Room r) {

        if (getRoomByID(r.getId()) != null){
            return false;
        }

        repository.roomTypes.add(r);
        return true;
    }

    @Override
    public List<Room> readRooms() {
        return repository.roomTypes;
    }

    @Override
    public List<SpecificRoom> getAvailableRooms() {
        List<SpecificRoom> availableRooms = new ArrayList<>();

        for (SpecificRoom room : repository.specificRooms){
            if (room.isAvailable()){
                availableRooms.add(room);
            }
        }

        return availableRooms;
    }

    @Override
    public boolean updateRoom(Room r) {
        if (getRoomByID(r.getId()) != null){
            r.setBasePricePerNight(r.getBasePricePerNight());
            r.setMaxCapacity(r.getMaxCapacity());
            r.setType(r.getType());

            return true;
        }

        return false;
    }

    @Override
    public boolean deleteRoom(Room r) {
        if (getRoomByID(r.getId()) != null){
            repository.roomTypes.remove(r);
            return true;
        }

        return false;
    }

    @Override
    public boolean addRoomToReservation(SpecificRoom room, Reservation reservation) {
        boolean result = false;
        if (room.isAvailable()){
            result = true;
        }
        return result;
    }

    @Override
    public Room getRoomByID(int id) {
        for (Room room : repository.roomTypes){
            if (room.getId() == id){
                return room;
            }
        }

        return null;
    }

    @Override
    public SpecificRoom getSpecificRoomByID(int id) {
        for (SpecificRoom room : repository.specificRooms){
            if (room.getId() == id){
                return room;
            }
        }

        return null;
    }

    @Override
    public boolean updateSpecificRoom(SpecificRoom room) {
        if (getSpecificRoomByID(room.getId()) != null){
            room.setPricePerNight(room.getPricePerNight());
            room.setAvailable(room.isAvailable());

            return true;
        }

        return false;
    }

    @Override
    public List<Room> searchAvailableRoom(RoomType type, int amountOfGuests) {
        List<Room> rooms = new ArrayList<>();

        for (SpecificRoom specificRoom : repository.specificRooms){
            if (specificRoom.getRoomType().getMaxCapacity() >= amountOfGuests){
                rooms.add(specificRoom.getRoomType());
                if (specificRoom.getRoomType().getType() == type){
                    rooms.add(specificRoom.getRoomType());
                }
            }
        }
        return rooms.stream().distinct().toList();


    }
}
