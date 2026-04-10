package com.hotel.reservas.service;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AccessService {

    public String generateDigitalKey(String reservationId, String roomNumber) {
        // Generate a unique UUID combining reservationId and room
        String seed = reservationId + "-" + roomNumber;
        String key = "HOTEL-" + UUID.nameUUIDFromBytes(seed.getBytes()).toString().toUpperCase();
        System.out.printf("[AccessService] Digital key generated for reservation %s, room %s: %s%n",
                reservationId, roomNumber, key);
        return key;
    }

    public boolean validateKey(String key, String reservationId, String roomNumber) {
        String expectedKey = "HOTEL-" + UUID.nameUUIDFromBytes(
                (reservationId + "-" + roomNumber).getBytes()
        ).toString().toUpperCase();
        return expectedKey.equals(key);
    }
}
