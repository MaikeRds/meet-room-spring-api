package com.example.demo.meetroom.controller;


import com.example.demo.meetroom.exception.ResourceNotFoundException;
import com.example.demo.meetroom.model.Room;
import com.example.demo.meetroom.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController @CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @RequestMapping(value = "rooms", method = RequestMethod.GET)
    public List<Room> getAllRoom(){
        return roomService.findAll();
    }

    @RequestMapping(value = "rooms/{id}", method = RequestMethod.GET)
    public ResponseEntity<Room> getRoomById(@PathVariable(value = "id") long roomId) throws ResourceNotFoundException {
        Room room = roomService.findById(roomId);
        return ResponseEntity.ok().body(room);
    }

    @RequestMapping(value = "rooms", method = RequestMethod.POST)
    public Room createRoom(@Valid @RequestBody Room room){
        return roomService.save(room);
    }

    @RequestMapping(value = "rooms/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Room> updateRoom(@PathVariable(value = "id") long roomId,
                           @Valid @RequestBody Room roomUpdate) throws ResourceNotFoundException {

        Room room = roomService.update(roomId, roomUpdate);

        return ResponseEntity.ok().body(room);
    }

    @RequestMapping(value = "rooms/{id}", method = RequestMethod.DELETE)
    public Map<String, Boolean> deleteRoom(@PathVariable(value = "id") long roomId) throws ResourceNotFoundException {
        roomService.delete(roomId);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return response;
    }

}
