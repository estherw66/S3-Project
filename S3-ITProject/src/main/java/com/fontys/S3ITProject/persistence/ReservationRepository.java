package com.fontys.s3itproject.persistence;

import com.fontys.s3itproject.entity.Reservation;
import com.fontys.s3itproject.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ReservationRepository {
      public boolean createReservation(Reservation r);
      public List<Reservation> readAllReservations();
      public List<Reservation> readMyReservations(User u);
      public boolean updateReservation(Reservation r);
      public boolean deleteReservation(Reservation r);
      public Reservation getReservationById(Long id);
}
