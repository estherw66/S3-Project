package com.fontys.s3itproject.controller;

import com.fontys.s3itproject.business.RoomService;
import com.fontys.s3itproject.dto.CreateRoomRequestDTO;
import com.fontys.s3itproject.dto.CreateRoomResponseDTO;
import com.fontys.s3itproject.dto.GetRoomsResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    @PostMapping
    public ResponseEntity<CreateRoomResponseDTO> createRoom(
            @RequestBody @Valid CreateRoomRequestDTO createRoomRequest){
        CreateRoomResponseDTO response = roomService.createRoom(createRoomRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}