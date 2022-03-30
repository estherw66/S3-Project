package com.fontys.S3ITProject.persistence.Impl;

import com.fontys.S3ITProject.models.*;
import com.fontys.S3ITProject.models.enums.RoomType;
import com.fontys.S3ITProject.persistence.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RepositoryImpl implements ReservationsRepository, UserRepository, RoomRepository, EmployeeRepository,
        LoginRepository {

    private final List<Employee> employees = new ArrayList<>();
    private final List<Reservation> reservations = new ArrayList<>();
    private final List<Room> roomTypes = new ArrayList<>();
    private final List<SpecificRoom> specificRooms = new ArrayList<>();
    private final List<User> users = new ArrayList<>();

    public RepositoryImpl(){
        addInitialRooms();
    }

    /* start reservations */
    @Override
    public boolean createReservation(Reservation r) {
        return false;
    }

    @Override
    public List<Reservation> readAllReservations() {
        return null;
    }

    @Override
    public List<Reservation> readMyReservations(User u) {
        return null;
    }

    @Override
    public boolean updateReservation(Reservation r) {
        return false;
    }

    @Override
    public boolean deleteReservation(Reservation r) {
        return false;
    }
    /* end reservations */

    /* start users */
    @Override
    public boolean createUser(User u) {
        return false;
    }

    @Override
    public List<User> readAllUsers() {
        return null;
    }

    @Override
    public User readUserByID(int id) {
        return null;
    }

    @Override
    public boolean updateUser(User u) {
        return false;
    }

    @Override
    public boolean deleteUser(User u) {
        return false;
    }

    /* end users*/

    /* start rooms */
    private void addInitialRooms(){

        // room types
        roomTypes.add(new Room(1, 1, 50, RoomType.SINGLE));
        roomTypes.add(new Room(2, 1, 112.50, RoomType.SINGLE_XXL));
        roomTypes.add(new Room(3, 2, 75, RoomType.DOUBLE));
        roomTypes.add(new Room(4,  2, 190, RoomType.DOUBLE_DELUXE));
        roomTypes.add(new Room(5,  4, 175, RoomType.FAMILY));
        roomTypes.add(new Room(6,  6, 225, RoomType.FAMILY_SUPERIOR));
    }

    @Override
    public boolean createRoom(Room r) {

        if (getRoomByID(r.getId()) != null){
            return false;
        }

        roomTypes.add(r);
        return true;
    }

    @Override
    public List<Room> readRooms() {
        return this.roomTypes;
    }

    @Override
    public List<Room> readAvailableRooms() {
        List<Room> availableRooms = new ArrayList<>();

//        for (Room room : rooms){
//            if (room.isAvailable()){
//                availableRooms.add(room);
//            }
//        }

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
            roomTypes.remove(r);
            return true;
        }

        return false;
    }

    @Override
    public boolean addRoomToReservation(Room room, Reservation reservation) {
        return false;
    }

    @Override
    public Room getRoomByID(int id) {
        for (Room room : roomTypes){
            if (room.getId() == id){
                return room;
            }
        }

        return null;
    }
    /* end rooms */

    /* start employees */
    @Override
    public boolean createEmployee(Employee e) {
        return false;
    }

    @Override
    public List<Employee> readEmployees() {
        return null;
    }

    @Override
    public Employee readEmployeeByID(int id) {
        return null;
    }

    @Override
    public boolean updateEmployee(Employee e) {
        return false;
    }

    @Override
    public boolean deleteEmployee(Employee e) {
        return false;
    }
    /* end employees */

    /* start login */
    @Override
    public Person checkLogin(Login l) {
        return null;
    }
    /* end login */
}
