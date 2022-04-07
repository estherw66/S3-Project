package com.fontys.S3ITProject.persistence.Impl;

import com.fontys.S3ITProject.models.*;
import com.fontys.S3ITProject.models.enums.RoomType;
import com.fontys.S3ITProject.persistence.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        addInitialSpecificRooms();
    }

    /* start reservations */
    @Override
    public boolean createReservation(Reservation r) {
        if (getReservationById(r.getId()) != null) {
            return false;
        }

        reservations.add(r);
        return true;
    }

    @Override
    public List<Reservation> readAllReservations() {
        return this.reservations;
    }

    @Override
    public List<Reservation> readMyReservations(User u) {
        List<Reservation> userReservations = new ArrayList<>();

        for (Reservation reservation : reservations){
            if (reservation.getUser().getId() == u.getId()){
                userReservations.add(reservation);
            }
        }

        return  userReservations;
    }

    @Override
    public boolean updateReservation(Reservation r) {
        return false;
    }

    @Override
    public boolean deleteReservation(Reservation r) {
        if (getReservationById(r.getId()) != null){
            reservations.remove(r);
            return true;
        }

        return false;
    }

    @Override
    public Reservation getReservationById(int id) {
        for (Reservation reservation : reservations){
            if (reservation.getId() == id){
                return reservation;
            }
        }

        return null;
    }
    /* end reservations */

    /* start users */
    @Override
    public boolean createUser(User u) {
        if (readUserByID(u.getId()) != null){
            users.add(u);
            return true;
        }

        return false;
    }

    @Override
    public List<User> readAllUsers() {
        return this.users;
    }

    @Override
    public User readUserByID(int id) {
        for (User user : users){
            if (user.getId() == id){
                return user;
            }
        }

        return null;
    }

    @Override
    public boolean updateUser(User u) {
        return false;
    }

    @Override
    public boolean deleteUser(User u) {
        if (readUserByID(u.getId()) != null){
            users.remove(u);
            return true;
        }

        return false;
    }

    /* end users*/

    /* start rooms */
    private void addInitialRooms(){

        // room types
        roomTypes.add(new Room(1, 1, 50, RoomType.SINGLE, ""));
        roomTypes.add(new Room(2, 1, 112.50, RoomType.SINGLE_XXL, ""));
        roomTypes.add(new Room(3, 2, 75, RoomType.DOUBLE, ""));
        roomTypes.add(new Room(4,  2, 190, RoomType.DOUBLE_DELUXE, ""));
        roomTypes.add(new Room(5,  4, 175, RoomType.FAMILY, ""));
        roomTypes.add(new Room(6,  6, 225, RoomType.FAMILY_SUPERIOR, ""));
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
    public List<SpecificRoom> getAvailableRooms() {
        List<SpecificRoom> availableRooms = new ArrayList<>();

        for (SpecificRoom room : specificRooms){
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
            roomTypes.remove(r);
            return true;
        }

        return false;
    }

    @Override
    public boolean addRoomToReservation(SpecificRoom room, Reservation reservation) {
        if (room.isAvailable()){
            return true;
        }
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

    @Override
    public SpecificRoom getSpecificRoomByID(int id) {
        for (SpecificRoom room : specificRooms){
            if (room.getId() == id){
                return room;
            }
        }

        return null;
    }

    @Override
    public boolean updateSpecificRoom(SpecificRoom room) {
        if (getSpecificRoomByID(room.getId()) != null){
            room.setActualPricePerNight(room.getActualPricePerNight());
            room.setAvailable(room.isAvailable());

            return true;
        }

        return false;
    }

    @Override
    public List<Room> searchAvailableRoom(RoomType type, int amountOfGuests) {
        List<Room> rooms = new ArrayList<>();

        for (SpecificRoom specificRoom : this.specificRooms){
            if (specificRoom.getRoomType().getType() == type || specificRoom.getRoomType().
            getMaxCapacity() >= amountOfGuests  && specificRoom.isAvailable()){
                rooms.add(specificRoom.getRoomType());
            }
        }

        List<Room> results = rooms.stream().distinct().collect(Collectors.toList());

        return results;
    }
    /* end rooms */

    /* start employees */
    @Override
    public boolean createEmployee(Employee e) {
        if (readEmployeeByID(e.getId()) != null){
            employees.add(e);
        }

        return false;
    }

    @Override
    public List<Employee> readEmployees() {
        return this.employees;
    }

    @Override
    public Employee readEmployeeByID(int id) {
        for (Employee employee : employees){
            if(employee.getId() == id){
                return employee;
            }
        }

        return null;
    }

    @Override
    public boolean updateEmployee(Employee e) {
        return false;
    }

    @Override
    public boolean deleteEmployee(Employee e) {
        if (readEmployeeByID(e.getId()) != null){
            employees.remove(e);
            return true;
        }

        return false;
    }
    /* end employees */

    /* start login */
    @Override
    public Person checkLogin(Login l) {
        for (Person person : users){
            if (person.getUsername().equals(l.getUsername()) && person.getPassword().equals(l.getPassword())){
                return person;
            }
        }

        for (Person person : employees){
            if (person.getUsername().equals(l.getUsername()) && person.getPassword().equals(l.getPassword())){
                return person;
            }
        }

        return null;
    }
    /* end login */
}
