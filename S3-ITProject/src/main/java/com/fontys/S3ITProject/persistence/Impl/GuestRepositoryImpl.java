package com.fontys.S3ITProject.persistence.Impl;

import com.fontys.S3ITProject.models.Guest;
import com.fontys.S3ITProject.persistence.GuestRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("fakeDB")
public class GuestRepositoryImpl implements GuestRepository {

    private final List<Guest> guestsList = new ArrayList<>();

    @Override
    public boolean createGuest(Guest guest) {
        guestsList.add(guest);
        return true;
    }
}
