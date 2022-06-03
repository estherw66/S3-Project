package com.fontys.s3itproject.repository;

import com.fontys.s3itproject.repository.entity.ReservationRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRoomRepository extends JpaRepository<ReservationRoom, Long> {
    List<ReservationRoom> findAllByReservation(Long id);
}
