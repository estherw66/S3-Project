package com.fontys.S3ITProject.business.impl;

import com.fontys.S3ITProject.models.Room;
import com.fontys.S3ITProject.models.enums.RoomType;
import com.fontys.S3ITProject.persistence.Impl.RepositoryImpl;
import com.fontys.S3ITProject.persistence.RoomRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class RoomServiceImplTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void createNewRoomSuccessfull(){
        // arrange
        RoomRepository roomRepo = new RepositoryImpl();
        Room newRoom = new Room(7, 2, 50, RoomType.DOUBLE, "");

        // act
        boolean result = roomRepo.createRoom(newRoom);

        // assert
        assertEquals(true, result);
    }

    @Test
    void createNewRoomFailedRoomIDAlreadyExists(){
        RoomRepository roomRepo = new RepositoryImpl();
        Room newRoom1 = new Room(7, 2, 50, RoomType.DOUBLE, "");
        Room newRoom2 = new Room(7, 2, 50, RoomType.DOUBLE, "");

        boolean result1 = roomRepo.createRoom(newRoom1);
        boolean result2 = roomRepo.createRoom(newRoom2);

        assertEquals(true, result1);
        assertEquals(false, result2);
    }

    @Test
    void readAllRoomsContainsSixRooms(){
        RoomRepository roomRepo = new RepositoryImpl();

        long result = roomRepo.readRooms().stream().count();

        assertEquals(6, result);
    }

    @Test
    void readRoomByIDOneIsSingleRoom(){
        RoomRepository roomRepo = new RepositoryImpl();

        Room testRoom = roomRepo.getRoomByID(1);

        assertNotEquals(null, testRoom);
    }

    @Test
    void readRoomByIDSevenRoomDoesntExist(){
        RoomRepository roomRepo = new RepositoryImpl();

        Room testRoom = roomRepo.getRoomByID(7);

        assertEquals(null, testRoom);
    }

    @Test
    void updateRoomSetNewBasePricePerNight(){
        RoomRepository roomRepo = new RepositoryImpl();
        Room old = roomRepo.getRoomByID(2);
        double oldPrice = old.getBasePricePerNight();

        old.setBasePricePerNight(100);
        roomRepo.updateRoom(old);
        Room updated = roomRepo.getRoomByID(2);

        assertNotEquals(oldPrice, updated.getBasePricePerNight());
    }

    @Test
    void deleteRoomFiveRoomsLeft(){
        RoomRepository roomRepo = new RepositoryImpl();

        Room deleted = roomRepo.getRoomByID(2);
        roomRepo.deleteRoom(deleted);
        long result = roomRepo.readRooms().stream().count();

        assertEquals(5, result);
    }
}
