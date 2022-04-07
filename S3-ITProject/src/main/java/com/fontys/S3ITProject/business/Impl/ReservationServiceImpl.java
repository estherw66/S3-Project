package com.fontys.S3ITProject.business.Impl;

import com.fontys.S3ITProject.business.ReservationService;
import com.fontys.S3ITProject.models.Reservation;
import com.fontys.S3ITProject.models.SpecificRoom;
import com.fontys.S3ITProject.models.User;
import com.fontys.S3ITProject.persistence.ReservationsRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationsRepository reservationsRepo;

    public ReservationServiceImpl(ReservationsRepository reservationsRepo) {
        this.reservationsRepo = reservationsRepo;
    }

    @Override
    public boolean createReservation(Reservation r) {
//        if (!checkDate(r) || !checkCapacity(r)) {
//            return false;
//        }

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
        // check if date is in future
        if (reservation.getCheckIn().isAfter(LocalDate.now())) {
            return true;
        }

        return false;
    }

    @Override
    public boolean checkCapacity(Reservation reservation){
        if (reservation.getAmountOfGuests() < reservation.getRoom().getRoomType().getMaxCapacity()){
            return true;
        }
        return false;
    }

    @Override
    public void calculatePricePerNight(Reservation reservation) {
        List<SpecificRoom> rooms = reservation.getRoomList();

        for (SpecificRoom room : rooms) {
            if (reservation.getCheckIn().isAfter(reservation.getReservationDate().plusMonths(1))) {
                room.setActualPricePerNight(room.getRoomType().getBasePricePerNight() + 1000);
            } else {
                room.setActualPricePerNight(room.getRoomType().getBasePricePerNight());
            }
        }
    }

    @Override
    public void calculateTotalPrice(Reservation reservation) {
        long totalDays = Duration.between(reservation.getCheckIn(), reservation.getCheckOut()).toDays();

        //double totalPrice
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