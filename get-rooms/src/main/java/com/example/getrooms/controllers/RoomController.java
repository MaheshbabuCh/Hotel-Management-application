package com.example.getrooms.controllers;

import com.example.getrooms.dtos.GetRoomsRequestDto;
import com.example.getrooms.dtos.GetRoomsResponseDto;
import com.example.getrooms.dtos.ResponseStatus;
import com.example.getrooms.models.Room;
import com.example.getrooms.services.RoomService;

import java.util.List;

public class RoomController {
    private RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    public GetRoomsResponseDto getRooms(GetRoomsRequestDto requestDto) {
        GetRoomsResponseDto responseDto = new GetRoomsResponseDto();
        try {
            List<Room> rooms = roomService.getRooms(requestDto.getRoomType());
            responseDto.setRooms(rooms);
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
            return responseDto;
        } catch (Exception e) {
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
            return responseDto;
        }
    }
}
