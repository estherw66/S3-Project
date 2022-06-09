package com.fontys.s3itproject.repository;

import com.fontys.s3itproject.repository.entity.Guest;
import com.fontys.s3itproject.repository.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findAllByGuest(Guest guest);
}
