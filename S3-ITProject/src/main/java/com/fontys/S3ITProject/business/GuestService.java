package com.fontys.s3itproject.business;

import com.fontys.s3itproject.dto.*;

import java.util.Optional;

public interface GuestService {
    CreateGuestResponseDTO createGuest(CreateGuestRequestDTO request);
    GetGuestsResponseDTO getGuests();
    Optional<GuestDTO> getGuest(Long guestID);
    void updateGuest(UpdateGuestRequestDTO request);
}
