package com.fontys.s3itproject.controller;

import com.fontys.s3itproject.business.RoomService;
import com.fontys.s3itproject.entity.Room;
import com.fontys.s3itproject.entity.SpecificRoom;
import com.fontys.s3itproject.entity.enums.RoomType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/rooms")
@CrossOrigin(origins = "http://localhost:3000")
public class RoomController {
    private final RoomService roomService;

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

    // search rooms
    @GetMapping("/search")
    public ResponseEntity<List<Room>> getSearchRooms(@RequestParam RoomType type, @RequestParam int guests) {

        List<Room> rooms = roomService.searchAvailableRoom(type, guests);

        if (rooms != null) {
            return ResponseEntity.ok().body(rooms);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
