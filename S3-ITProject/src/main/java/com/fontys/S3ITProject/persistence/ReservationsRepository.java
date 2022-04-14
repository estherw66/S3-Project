package com.fontys.s3itproject.persistence;

import com.fontys.s3itproject.models.Reservation;
import com.fontys.s3itproject.models.User;

import java.util.List;

public interface ReservationsRepository {
      public boolean createReservation(Reservation r);
      public List<Reservation> readAllReservations();
      public List<Reservation> readMyReservations(User u);
      public boolean updateReservation(Reservation r);
      public boolean deleteReservation(Reservation r);
      public Reservation getReservationById(int id);
}
