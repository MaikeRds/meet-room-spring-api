package com.example.demo.meetroom.service;

import com.example.demo.meetroom.exception.ResourceNotFoundException;
import com.example.demo.meetroom.model.Room;
import com.example.demo.meetroom.repository.RoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RoomService {

    private RoomRepository roomRepository;

    public List<Room> findAll() {
        return roomRepository.findAll();
    }

    public Room findById(long roomId) throws ResourceNotFoundException {
        return roomRepository.findById(roomId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Room not found " + roomId)
                );
    }

    public Room save(Room room) {
        return roomRepository.save(room);
    }


    public Room update(long roomId, Room roomUpdate) throws ResourceNotFoundException {
        Room room = this.findById(roomId);

        room.setName(roomUpdate.getName());
        room.setDate(roomUpdate.getDate());
        room.setStartHour(roomUpdate.getStartHour());
        room.setEndHour(roomUpdate.getEndHour());

        return roomRepository.save(room);
    }

    public void delete(long roomId) throws ResourceNotFoundException {
        Room room = this.findById(roomId);
        roomRepository.deleteById(room.getId());
    }
}
