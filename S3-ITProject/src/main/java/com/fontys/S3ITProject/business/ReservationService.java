package com.fontys.S3ITProject.business;

import com.fontys.S3ITProject.models.Reservation;
import com.fontys.S3ITProject.models.User;

import java.util.List;

public interface ReservationService {
    public boolean createReservation(Reservation r);
    public List<Reservation> readAllReservations();
    public List<Reservation> readMyReservations(User u);
    public boolean updateReservation(Reservation r);
    public boolean deleteReservation(Reservation r);

    public boolean checkDate(Reservation reservation);
    public void calculatePricePerNight(Reservation reservation);
    public void calculateTotalPrice(Reservation reservation);
    public boolean addGuestToReservation();
    public boolean addRoomToReservation();
    public boolean checkCapacity(Reservation reservation);
}
