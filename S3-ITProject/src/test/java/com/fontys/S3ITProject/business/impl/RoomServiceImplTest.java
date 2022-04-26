package com.fontys.s3itproject.business.impl;

import com.fontys.s3itproject.entity.Room;
import com.fontys.s3itproject.entity.enums.RoomType;
import com.fontys.s3itproject.persistence.impl.RepositoryImpl;
import com.fontys.s3itproject.persistence.RoomRepository;
import com.fontys.s3itproject.persistence.impl.RoomRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class RoomServiceImplTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void createNewRoomSuccessfull(){
        // arrange
        RepositoryImpl repo = new RepositoryImpl();
        RoomRepository roomRepo = new RoomRepositoryImpl(repo);
        Room newRoom = new Room(7L, 2, 50, RoomType.DOUBLE, "", false);

        // act
        boolean result = roomRepo.createRoom(newRoom);

        // assert
        assertEquals(true, result);
    }

    @Test
    void createNewRoomFailedRoomIDAlreadyExists(){
        RepositoryImpl repo = new RepositoryImpl();
        RoomRepository roomRepo = new RoomRepositoryImpl(repo);
        Room newRoom1 = new Room(7L, 2, 50, RoomType.DOUBLE, "", false);
        Room newRoom2 = new Room(7L, 2, 50, RoomType.DOUBLE, "", false);

        boolean result1 = roomRepo.createRoom(newRoom1);
        boolean result2 = roomRepo.createRoom(newRoom2);

        assertEquals(true, result1);
        assertEquals(false, result2);
    }

    @Test
    void readAllRoomsContainsSixRooms(){
        RepositoryImpl repo = new RepositoryImpl();
        RoomRepository roomRepo = new RoomRepositoryImpl(repo);

        long result = roomRepo.readRooms().stream().count();

        assertEquals(6, result);
    }

    @Test
    void readRoomByIDOneIsSingleRoom(){
        RepositoryImpl repo = new RepositoryImpl();
        RoomRepository roomRepo = new RoomRepositoryImpl(repo);

        Room testRoom = roomRepo.getRoomByID(1);

        assertNotEquals(null, testRoom);
    }

    @Test
    void readRoomByIDSevenRoomDoesntExist(){
        RepositoryImpl repo = new RepositoryImpl();
        RoomRepository roomRepo = new RoomRepositoryImpl(repo);

        Room testRoom = roomRepo.getRoomByID(7);

        assertEquals(null, testRoom);
    }

    @Test
    void updateRoomSetNewBasePricePerNight(){
        RepositoryImpl repo = new RepositoryImpl();
        RoomRepository roomRepo = new RoomRepositoryImpl(repo);
        Room old = roomRepo.getRoomByID(2);
        double oldPrice = old.getBasePricePerNight();

        old.setBasePricePerNight(100);
        roomRepo.updateRoom(old);
        Room updated = roomRepo.getRoomByID(2);

        assertNotEquals(oldPrice, updated.getBasePricePerNight());
    }

    @Test
    void deleteRoomFiveRoomsLeft(){
        RepositoryImpl repo = new RepositoryImpl();
        RoomRepository roomRepo = new RoomRepositoryImpl(repo);

        Room deleted = roomRepo.getRoomByID(2);
        roomRepo.deleteRoom(deleted);
        long result = roomRepo.readRooms().stream().count();

        assertEquals(5, result);
    }
}
