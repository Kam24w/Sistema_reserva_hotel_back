package com.hotel.reservas.service;

import com.hotel.reservas.model.Room;
import com.hotel.reservas.model.RoomType;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RoomService {

    private final Map<String, Room> rooms = new LinkedHashMap<>();

    public RoomService() {
        initializeRooms();
    }

    private void initializeRooms() {
        // 6 Single Rooms
        rooms.put("101", new Room("101", RoomType.SINGLE, 80.0, "Single room with garden view"));
        rooms.put("102", new Room("102", RoomType.SINGLE, 80.0, "Single room with pool view"));
        rooms.put("103", new Room("103", RoomType.SINGLE, 85.0, "Premium single room with balcony"));
        rooms.put("104", new Room("104", RoomType.SINGLE, 80.0, "Standard single room floor 1"));
        rooms.put("105", new Room("105", RoomType.SINGLE, 80.0, "Standard single room floor 1"));
        rooms.put("106", new Room("106", RoomType.SINGLE, 85.0, "Single room with mountain view"));

        // 6 Double Rooms
        rooms.put("201", new Room("201", RoomType.DOUBLE, 130.0, "Double room with king size bed"));
        rooms.put("202", new Room("202", RoomType.DOUBLE, 130.0, "Double room with two single beds"));
        rooms.put("203", new Room("203", RoomType.DOUBLE, 140.0, "Premium double room with jacuzzi"));
        rooms.put("204", new Room("204", RoomType.DOUBLE, 130.0, "Double room with sea view"));
        rooms.put("205", new Room("205", RoomType.DOUBLE, 135.0, "Double room with private terrace"));
        rooms.put("206", new Room("206", RoomType.DOUBLE, 130.0, "Family double room"));

        // 3 Suites
        rooms.put("301", new Room("301", RoomType.SUITE, 250.0, "Presidential suite with living room"));
        rooms.put("302", new Room("302", RoomType.SUITE, 220.0, "Junior suite with panoramic view"));
        rooms.put("303", new Room("303", RoomType.SUITE, 280.0, "Luxury suite with private pool"));
    }

    public List<Room> getAvailableRooms() {
        return rooms.values().stream()
                .filter(Room::isAvailable)
                .collect(Collectors.toList());
    }

    public List<Room> getAvailableRoomsByType(RoomType type) {
        return rooms.values().stream()
                .filter(r -> r.isAvailable() && r.getType() == type)
                .collect(Collectors.toList());
    }

    public List<Room> getAllRooms() {
        return new ArrayList<>(rooms.values());
    }

    public Optional<Room> findRoom(String number) {
        return Optional.ofNullable(rooms.get(number));
    }

    public boolean reserveRoom(String number) {
        Room room = rooms.get(number);
        if (room != null && room.isAvailable()) {
            room.setAvailable(false);
            System.out.println("[RoomService] Room " + number + " reserved.");
            return true;
        }
        return false;
    }

    public void releaseRoom(String number) {
        Room room = rooms.get(number);
        if (room != null) {
            room.setAvailable(true);
            System.out.println("[RoomService] Room " + number + " released.");
        }
    }
}
