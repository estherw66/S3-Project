package com.fontys.s3itproject.business.impl;

import com.fontys.s3itproject.business.GuestService;
import com.fontys.s3itproject.business.exception.EmailAlreadyExistsException;
import com.fontys.s3itproject.business.exception.InvalidGuestException;
import com.fontys.s3itproject.business.exception.UnauthorisedDataAccessException;
import com.fontys.s3itproject.business.exception.UsernameAlreadyExistsException;
import com.fontys.s3itproject.dto.*;
import com.fontys.s3itproject.repository.GuestRepository;
import com.fontys.s3itproject.repository.UserRepository;
import com.fontys.s3itproject.repository.entity.Guest;
import com.fontys.s3itproject.repository.entity.RoleEnum;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GuestServiceImpl implements GuestService {

    private final GuestRepository guestRepository;
    private final UserRepository userRepository;

    private AccessTokenDTO requestAccessToken;

    @Override
    public CreateGuestResponseDTO createGuest(CreateGuestRequestDTO request) {
        if (existsByEmail(request.getEmail())){
            throw new EmailAlreadyExistsException();
        }
        if (existsByUsername(request.getUsername())){
            throw new UsernameAlreadyExistsException();
        }

        Guest savedGuest = saveNewGuest(request);

        return CreateGuestResponseDTO.builder()
                .guestID(savedGuest.getId())
                .build();
    }

    @Override
    public GetGuestsResponseDTO getGuests() {
        List<GuestDTO> guests = findAll()
                .stream()
                .map(GuestDTOConverter::convertToDTO)
                .toList();

        return GetGuestsResponseDTO.builder()
                .guests(guests)
                .build();
    }

    @Override
    public Optional<GuestDTO> getGuest(Long guestID) {
        if (!requestAccessToken.hasRole(RoleEnum.EMPLOYEE.name())){
            if (!requestAccessToken.getEmployeeId().equals(guestID)){
                throw new UnauthorisedDataAccessException("GUEST_ID_NOT_FROM_LOGGED_IN_USER");
            }
        }

        return guestRepository.findById(guestID).map(GuestDTOConverter::convertToDTO);
    }

    @Override
    public void updateGuest(UpdateGuestRequestDTO request) {
        Optional<Guest> guestOptional = guestRepository.findById(request.getId());
        if(guestOptional.isEmpty()){
            throw new InvalidGuestException("GUEST_ID_INVALID");
        }

        if (!requestAccessToken.hasRole(RoleEnum.ADMIN.name()) || !requestAccessToken.hasRole(RoleEnum.EMPLOYEE.name())){
            if (!requestAccessToken.getEmployeeId().equals(request.getId())){
                throw new UnauthorisedDataAccessException("GUEST_ID_NOT_FROM_LOGGED_IN_USER");
            }
        }

        Guest guest = guestOptional.get();
        updateFields(request, guest);
    }

    private Guest saveNewGuest(CreateGuestRequestDTO request) {
        Guest newGuest = Guest.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .build();
        return guestRepository.save(newGuest);
    }

    private void updateFields(UpdateGuestRequestDTO request, Guest guest){
        guest.setFirstName(request.getFirstName());
        guest.setLastName(request.getLastName());
        guest.setEmail(request.getEmail());

        guestRepository.save(guest);
    }

    private boolean existsByEmail(String email){
        return guestRepository.existsByEmail(email);
    }
    private boolean existsByUsername(String username){
        return userRepository.existsByUsername(username);
    }

    private List<Guest> findAll(){
        return guestRepository.findAll();
    }
}
