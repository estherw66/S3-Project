package com.fontys.S3ITProject.persistence.Impl;

import com.fontys.S3ITProject.models.Reservation;
import com.fontys.S3ITProject.models.User;
import com.fontys.S3ITProject.persistence.FakeDataBase;
import com.fontys.S3ITProject.persistence.ReservationsRepository;
import com.fontys.S3ITProject.persistence.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RepositoryImpl implements ReservationsRepository, UserRepository {

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
    /* end users */

    /* start rooms */
}
