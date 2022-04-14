package com.fontys.s3itproject.business;

import com.fontys.s3itproject.models.Reservation;
import com.fontys.s3itproject.models.User;

import java.util.List;

public interface ReservationService {
    public boolean createReservation(Reservation r);
    public List<Reservation> readAllReservations();
    public List<Reservation> readMyReservations(User u);
    public boolean updateReservation(Reservation r);
    public boolean deleteReservation(Reservation r);

    public boolean checkDate(Reservation reservation);
    public void calculatePricePerNight(Reservation reservation);
    public boolean addGuestToReservation();
    public boolean addRoomToReservation();
    public boolean checkCapacity(Reservation reservation);
}
