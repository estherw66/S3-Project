package com.fontys.s3itproject.controller;

import com.fontys.s3itproject.business.GuestService;
import com.fontys.s3itproject.configuration.security.isauthenticated.IsAuthenticated;
import com.fontys.s3itproject.dto.CreateGuestRequestDTO;
import com.fontys.s3itproject.dto.CreateGuestResponseDTO;
import com.fontys.s3itproject.dto.GetGuestsResponseDTO;
import com.fontys.s3itproject.dto.GuestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/guests")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class GuestController {
    private final GuestService guestService;

    @GetMapping("{id}")
    public ResponseEntity<GuestDTO> getGuest(@PathVariable(value = "id") final long id){
        final Optional<GuestDTO> guestOptional = guestService.getGuest(id);
        if (guestOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(guestOptional.get());
    }

//    @IsAuthenticated
//    @RolesAllowed({"ROLE_ADMIN", "ROLE_EMPLOYEE"})
    @GetMapping
    public ResponseEntity<GetGuestsResponseDTO> getAllGuests() {
        return ResponseEntity.ok(guestService.getGuests());
    }

    @PostMapping()
    public ResponseEntity<CreateGuestResponseDTO> createGuest(@RequestBody @Valid CreateGuestRequestDTO request){
        CreateGuestResponseDTO response = guestService.createGuest(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
