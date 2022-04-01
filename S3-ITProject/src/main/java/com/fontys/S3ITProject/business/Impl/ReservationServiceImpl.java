package com.fontys.S3ITProject.business.Impl;

import com.fontys.S3ITProject.business.ReservationService;
import com.fontys.S3ITProject.models.Reservation;
import com.fontys.S3ITProject.models.User;
import com.fontys.S3ITProject.persistence.ReservationsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationsRepository reservationsRepo;

    public ReservationServiceImpl(ReservationsRepository reservationsRepo) {
        this.reservationsRepo = reservationsRepo;
    }

    @Override
    public boolean createReservation(Reservation r) {
        return reservationsRepo.createReservation(r);
    }

    @Override
    public List<Reservation> readAllReservations() {
        return reservationsRepo.readAllReservations();
    }

    @Override
    public List<Reservation> readMyReservations(User u) {
        return reservationsRepo.readMyReservations(u);
    }

    @Override
    public boolean updateReservation(Reservation r) {
        return reservationsRepo.updateReservation(r);
    }

    @Override
    public boolean deleteReservation(Reservation r) {
        return reservationsRepo.deleteReservation(r);
    }

    @Override
    public boolean checkDate() {
        return false;
    }

    @Override
    public double calculatePricePerNight() {
        return 0;
    }

    @Override
    public double calculateTotalPrice() {
        return 0;
    }

    @Override
    public boolean addGuestToReservation() {
        return false;
    }

    @Override
    public boolean addRoomToReservation() {
        return false;
    }

    @Override
    public boolean updateStatusOfReservation(Reservation reservation) {
        reservation.setStatus(reservation.getStatus());
        return true;
    }
}
