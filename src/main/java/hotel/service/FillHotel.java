package hotel.service;

import hotel.model.Room;
import hotel.model.RoomInfo;
import hotel.model.RoomType;

import java.util.Collections;
import java.util.List;

public class FillHotel {

    private final ReservationService reservationService = ReservationService.getSingleton();



    public static void createRoom(){
        Room room1 = new Room("123", 22.2, RoomType.valueOfLabel("2"));
        addRoom(Collections.singletonList(room1));
        Room room2 = new Room("328", 2312.2, RoomType.valueOfLabel("1"));
        addRoom(Collections.singletonList(room2));
        Room room3 = new Room("537", 343.8, RoomType.valueOfLabel("2"));
        addRoom(Collections.singletonList(room3));
    }

    public static void addRoom(List<RoomInfo> rooms) {
        final ReservationService reservationService = ReservationService.getSingleton();

        rooms.forEach(reservationService::addRoom);
    }



}
