package com.fontys.S3ITProject.persistence.Impl;

import com.fontys.S3ITProject.models.*;
import com.fontys.S3ITProject.persistence.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RepositoryImpl implements ReservationsRepository, UserRepository, RoomRepository, EmployeeRepository,
        LoginRepository {
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

    @Override
    public boolean createRoom(Room r) {
        return false;
    }
    /* end users*/

    /* start rooms */
    @Override
    public List<Room> readRooms() {
        return null;
    }

    @Override
    public List<Room> readAvailableRooms() {
        return null;
    }

    @Override
    public boolean updateRoom(Room r) {
        return false;
    }

    @Override
    public boolean deleteRoom(Room r) {
        return false;
    }

    @Override
    public boolean addRoomToReservation(Room room, Reservation reservation) {
        return false;
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
