# Implement functionality using which customers can browse the Hotel rooms.

## Problem Statement
You are building a Hotel Management System. As a part of this system, you need to implement functionality using which customers can browse the hotel rooms.

## Assignment

Your task is to implement the following functionality in the system.

#### Requirements

1. The get rooms request will get room type as input. The room type can be either "deluxe" or "super_deluxe" or "suite".
2. If an invalid room type is given to this functionality, we should get an error.
3. When a valid room type is given, you need to get the rooms from the database and filter the rooms based on the input type passed and return in the response.
4. If room type is not given, you need to return all the rooms in response.

#### Instructions

* Refer the `getRooms` function in RoomController class.
* Refer the `GetRoomsRequestDto` and `GetRoomsResponseDto` classes for understanding the expected input and output to the functionality.
* Refer the models package for reference of the models.
* Implement the `RoomRepository` and `RoomService` interfaces to achieve the above requirements.
* Please implement an in memory database to store the rooms. You can use any data structure of your choice to store the rooms.
