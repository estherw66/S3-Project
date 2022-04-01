package com.fontys.S3ITProject.persistence;

import com.fontys.S3ITProject.models.Reservation;
import com.fontys.S3ITProject.models.User;

import java.util.List;

public interface ReservationsRepository {
      public boolean createReservation(Reservation r);
      public List<Reservation> readAllReservations();
      public List<Reservation> readMyReservations(User u);
      public boolean updateReservation(Reservation r);
      public boolean deleteReservation(Reservation r);
      public Reservation getReservationById(int id);
}
