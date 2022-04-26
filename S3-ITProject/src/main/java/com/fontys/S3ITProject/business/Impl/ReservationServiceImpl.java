package com.fontys.s3itproject.business.impl;

import com.fontys.s3itproject.business.ReservationService;
import com.fontys.s3itproject.entity.Reservation;
import com.fontys.s3itproject.entity.SpecificRoom;
import com.fontys.s3itproject.entity.User;
import com.fontys.s3itproject.persistence.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Primary
@RequiredArgsConstructor
@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationsRepo;

    @Override
    public boolean createReservation(Reservation r) {

        return reservationsRepo.createReservation(r);
    }

    @Override
    public List<Reservation> readAllReservations() {
        return reservationsRepo.readAllReservations();
    }

    @Override
    public List<Reservation> readMyReservations(User u) {
        return reservationsRepo.readMyReservations(u);
    }

    @Override
    public boolean updateReservation(Reservation r) {
        return reservationsRepo.updateReservation(r);
    }

    @Override
    public boolean deleteReservation(Reservation r) {
        return reservationsRepo.deleteReservation(r);
    }

    @Override
    public boolean checkDate(Reservation reservation) {
        boolean result = false;
        // check if date is in future
        if (reservation.getCheckIn().isAfter(LocalDate.now())) {
            result = true;
        }

        return result;
    }

    @Override
    public boolean checkCapacity(Reservation reservation){
        boolean result = false;

        if (reservation.getAmountOfGuests() < reservation.getRoom().getRoomType().getMaxCapacity()){
            result = true;
        }
        return result;
    }

    @Override
    public void calculatePricePerNight(Reservation reservation) {
//        List<SpecificRoom> rooms = reservation.getRoomList();
//
//        for (SpecificRoom room : rooms) {
//            if (reservation.getCheckIn().isAfter(reservation.getReservationDate().plusMonths(1))) {
//                room.setPricePerNight(room.getRoomType().getBasePricePerNight() + 1000);
//            } else {
//                room.setPricePerNight(room.getRoomType().getBasePricePerNight());
//            }
//        }


    }

    @Override
    public boolean addGuestToReservation() {
        return false;
    }

    @Override
    public boolean addRoomToReservation() {
        return false;
    }
}