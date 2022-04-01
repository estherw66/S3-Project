package com.fontys.S3ITProject.controller;

import com.fontys.S3ITProject.business.RoomService;
import com.fontys.S3ITProject.models.Room;
import com.fontys.S3ITProject.models.SpecificRoom;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
@CrossOrigin(origins = "http://localhost:3000")
public class RoomController {
    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    // get all roomtypes
    @GetMapping
    public ResponseEntity<List<Room>> getAllRooms(){
        List<Room> rooms = roomService.readRooms();

        if (rooms != null){
            return ResponseEntity.ok().body(rooms);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // get available rooms
    @GetMapping("/available")
    public ResponseEntity<List<SpecificRoom>> getAllAvailableRooms(){
        List<SpecificRoom> rooms = roomService.getAvailableRooms();

        if (rooms != null){
            return ResponseEntity.ok().body(rooms);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    // get all rooms

    //
}
