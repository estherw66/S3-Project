package com.fontys.s3itproject.persistence.impl;

import com.fontys.s3itproject.entity.Reservation;
import com.fontys.s3itproject.entity.User;
import com.fontys.s3itproject.persistence.ReservationRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Primary
@Repository
public class ReservationRepositoryImpl implements ReservationRepository {

    private final RepositoryImpl repository;

    public ReservationRepositoryImpl(RepositoryImpl repository) {
        this.repository = repository;
    }

    @Override
    public boolean createReservation(Reservation r) {
        boolean result = true;

        if (getReservationById(r.getId()) != null) {
            result = false;
        }

        repository.reservations.add(r);
        return result;
    }

    @Override
    public List<Reservation> readAllReservations() {
        return repository.reservations;
    }

    @Override
    public List<Reservation> readMyReservations(User u) {
        List<Reservation> userReservations = new ArrayList<>();

        for (Reservation reservation : repository.reservations){
            if (reservation.getMainGuest().getId() == u.getId()){
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
            repository.reservations.remove(r);
            return true;
        }

        return false;
    }

    @Override
    public Reservation getReservationById(Long id) {
        for (Reservation reservation : repository.reservations){
            if (reservation.getId() == id){
                return reservation;
            }
        }

        return null;
    }
}
