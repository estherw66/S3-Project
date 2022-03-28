package com.fontys.S3ITProject.business.Impl;

import com.fontys.S3ITProject.business.GuestService;
import com.fontys.S3ITProject.models.Guest;
import com.fontys.S3ITProject.persistence.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class GuestServiceImpl implements GuestService {
    private final GuestRepository guestRepository;

    @Autowired
    public GuestServiceImpl(@Qualifier("fakeDB") GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    @Override
    public boolean createGuest(Guest guest) {
        return guestRepository.createGuest(guest);
    }
}
