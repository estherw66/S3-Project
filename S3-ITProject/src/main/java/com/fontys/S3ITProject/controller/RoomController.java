package com.fontys.s3itproject.controller;

import com.fontys.s3itproject.business.RoomService;
import com.fontys.s3itproject.configuration.security.isauthenticated.IsAuthenticated;
import com.fontys.s3itproject.dto.*;
import com.fontys.s3itproject.repository.entity.Room;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/rooms")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class RoomController {
    private final RoomService roomService;

    @GetMapping
    public ResponseEntity<GetRoomsResponseDTO> getRooms(){
        return ResponseEntity.ok(roomService.getRooms());
    }

    @GetMapping(path = "/featured")
    public ResponseEntity<GetRoomsResponseDTO> getFeaturedRooms(){return ResponseEntity.ok(roomService.getFeaturedRooms());}

    @IsAuthenticated
    @RolesAllowed({"ROLE_EMPLOYEE"})
    @GetMapping(path = "{id}")
    public ResponseEntity<RoomDTO> getRoom(@PathVariable(value = "id") final long id){
        final Optional<RoomDTO> roomOptional = roomService.getRoom(id);

        if (roomOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(roomOptional.get());
    }

    @IsAuthenticated
    @RolesAllowed({"ROLE_EMPLOYEE"})
    @PostMapping
    public ResponseEntity<CreateRoomResponseDTO> createRoom(
            @RequestBody @Valid CreateRoomRequestDTO createRoomRequest){
        CreateRoomResponseDTO response = roomService.createRoom(createRoomRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @IsAuthenticated
    @RolesAllowed({"ROLE_EMPLOYEE"})
    @PutMapping("{id}")
    public ResponseEntity<RoomDTO> updateRoom(@PathVariable("id") long id,
            @RequestBody @Valid UpdateRoomRequestDTO request) {
        request.setId(id);
        roomService.updateRoom(request);
        return ResponseEntity.noContent().build();
    }
}
