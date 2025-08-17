package com.example.getrooms.repositories;

import com.example.getrooms.models.Room;
import com.example.getrooms.models.RoomType;

import java.util.List;

public interface RoomRepository {
    Room add(Room room);
    List<Room> getRooms();
    List<Room> getRoomsByRoomType(RoomType roomType);
    Room save(Room room);
}
