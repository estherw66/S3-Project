package com.fontys.s3itproject.repository;

import com.fontys.s3itproject.repository.entity.ReservationRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRoomRepository extends JpaRepository<ReservationRoom, Long> {
}
