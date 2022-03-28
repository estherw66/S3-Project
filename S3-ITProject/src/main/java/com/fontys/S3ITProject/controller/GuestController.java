package com.fontys.S3ITProject.controller;

import com.fontys.S3ITProject.business.GuestService;
import com.fontys.S3ITProject.models.Guest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/guests")
@RestController
public class GuestController {
    private final GuestService guestService;

    @Autowired
    public GuestController(GuestService guestService) {
        this.guestService = guestService;
    }

    @PostMapping
    public void createGuest(@RequestBody Guest guest){
        guestService.createGuest(guest);
    }
}
