package com.fontys.s3itproject.repository;

import com.fontys.s3itproject.repository.entity.Guest;
import com.fontys.s3itproject.repository.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findAllByGuest(Guest guest);

//    query to check availability for rooms:
//    SELECT COUNT(r.id) FROM ReservationRoom as rr
//    INNER JOIN Reservation as r on rr.reservation_id = r.id
//    WHERE rr.room_id = Room.roomType
//    AND r.check_in < Reservation.checkInDate
//    AND r.check_out > Reservation.checkOutDate

//    not enough time to fully implement.
}
