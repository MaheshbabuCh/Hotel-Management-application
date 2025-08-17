package com.example.getrooms.services;

import com.example.getrooms.models.Room;

import java.util.List;

public interface RoomService {
    List<Room> getRooms(String roomType);
}
