package com.fontys.s3itproject.persistence.impl;

import com.fontys.s3itproject.entity.*;
import com.fontys.s3itproject.entity.enums.RoomType;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Primary
@Repository
public class RepositoryImpl {

    public List<Employee> employees;
    public List<Reservation> reservations;
    public List<Room> roomTypes;
    public List<SpecificRoom> specificRooms;
    public List<User> users;

    public RepositoryImpl(){
        employees = new ArrayList<>();
        reservations = new ArrayList<>();
        roomTypes = new ArrayList<>();
        specificRooms = new ArrayList<>();
        users = new ArrayList<>();

//        specificRooms.add(new SpecificRoom(1, 101, false, getRoomByID(1)));
//        specificRooms.add(new SpecificRoom(2, 102, false, getRoomByID(1)));
//        specificRooms.add(new SpecificRoom(3, 103, false, getRoomByID(1)));
//        specificRooms.add(new SpecificRoom(4, 104, false, getRoomByID(1)));
//        specificRooms.add(new SpecificRoom(5, 105, true, getRoomByID(1)));
//        specificRooms.add(new SpecificRoom(6, 106, true, getRoomByID(1)));
//        specificRooms.add(new SpecificRoom(7, 107, true, getRoomByID(1)));
//        specificRooms.add(new SpecificRoom(8, 108, true, getRoomByID(1)));
//        specificRooms.add(new SpecificRoom(9, 109, true, getRoomByID(1)));
//        specificRooms.add(new SpecificRoom(10, 110, true, getRoomByID(1)));

        addInitialRooms();
        addInitialSpecificRooms();
        addInitialUser();
    }

    /* start reservations */

    /* end reservations */

    /* start users */


    /* end users*/

    /* start rooms */
    private void addInitialRooms(){

        // room types
        roomTypes.add(new Room(1, 1, 50, RoomType.SINGLE, "", false));
        roomTypes.add(new Room(2, 1, 112.50, RoomType.SINGLE_XXL, "", true));
        roomTypes.add(new Room(3, 2, 75, RoomType.DOUBLE, "", true));
        roomTypes.add(new Room(4,  2, 190, RoomType.DOUBLE_DELUXE, "", false));
        roomTypes.add(new Room(5,  4, 175, RoomType.FAMILY, "", true));
        roomTypes.add(new Room(6,  6, 225, RoomType.FAMILY_SUPERIOR, "", false));
    }
    private void addInitialSpecificRooms(){
        // single rooms
        specificRooms.add(new SpecificRoom(1, 101, false, getRoomByID(1)));
        specificRooms.add(new SpecificRoom(2, 102, false, getRoomByID(1)));
        specificRooms.add(new SpecificRoom(3, 103, false, getRoomByID(1)));
        specificRooms.add(new SpecificRoom(4, 104, false, getRoomByID(1)));
        specificRooms.add(new SpecificRoom(5, 105, true, getRoomByID(1)));
        specificRooms.add(new SpecificRoom(6, 106, true, getRoomByID(1)));
        specificRooms.add(new SpecificRoom(7, 107, true, getRoomByID(1)));
        specificRooms.add(new SpecificRoom(8, 108, true, getRoomByID(1)));
        specificRooms.add(new SpecificRoom(9, 109, true, getRoomByID(1)));
        specificRooms.add(new SpecificRoom(10, 110, true, getRoomByID(1)));
        // single xxl
        specificRooms.add(new SpecificRoom(11, 111, false, getRoomByID(2)));
        specificRooms.add(new SpecificRoom(12, 112, false, getRoomByID(2)));
        specificRooms.add(new SpecificRoom(13, 113, true, getRoomByID(2)));
        specificRooms.add(new SpecificRoom(14, 114, true, getRoomByID(2)));
        specificRooms.add(new SpecificRoom(15, 115, true, getRoomByID(2)));
        // double rooms
        specificRooms.add(new SpecificRoom(16, 201, false, getRoomByID(3)));
        specificRooms.add(new SpecificRoom(17, 202, false, getRoomByID(3)));
        specificRooms.add(new SpecificRoom(18, 203, false, getRoomByID(3)));
        specificRooms.add(new SpecificRoom(19, 204, false, getRoomByID(3)));
        specificRooms.add(new SpecificRoom(20, 205, false, getRoomByID(3)));
        specificRooms.add(new SpecificRoom(21, 206, true, getRoomByID(3)));
        specificRooms.add(new SpecificRoom(22, 207, true, getRoomByID(3)));
        // double xxl
        specificRooms.add(new SpecificRoom(23, 208, true, getRoomByID(4)));
        // family
        specificRooms.add(new SpecificRoom(24, 301, true, getRoomByID(5)));
        // family xxl
        specificRooms.add(new SpecificRoom(25, 302, true, getRoomByID(6)));
    }

    public Room getRoomByID(int id) {
        for (Room room : this.roomTypes){
            if (room.getId() == id){
                return room;
            }
        }

        return null;
    }
    /* end rooms */

    /* start employees */
    private void addInitialEmployees(){
        employees.add(new Employee(1L, "Esther", "Wolfs", "estherwolfs@goldskye.com", "ewolfs", "password", new Address(1L, "Mozartlaan 41", "5151KA", "Drunen"), "+31612901749", LocalDate.of(1998,01,01)));
    }

    private void addInitialUser(){
        users.add(new User(1L, "Fake", "Data", "fakedata@mail.com", "fake", "djkfsf"));
    }
    /* end employees */

    /* start login */

    /* end login */
}
