package com.fontys.S3ITProject.business.Impl;

import com.fontys.S3ITProject.business.ReservationService;
import com.fontys.S3ITProject.models.Reservation;
import com.fontys.S3ITProject.models.User;
import com.fontys.S3ITProject.persistence.ReservationsRepository;

import java.util.List;

public class ReservationServiceImpl implements ReservationService {

    private final ReservationsRepository reservationsRepo;

    public ReservationServiceImpl(ReservationsRepository reservationsRepo) {
        this.reservationsRepo = reservationsRepo;
    }

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
}
