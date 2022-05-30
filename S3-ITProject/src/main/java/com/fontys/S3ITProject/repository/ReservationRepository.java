package com.fontys.s3itproject.repository;

import com.fontys.s3itproject.repository.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
