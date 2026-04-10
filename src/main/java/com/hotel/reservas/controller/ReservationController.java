package com.hotel.reservas.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.reservas.dto.ReservationRequestDTO;
import com.hotel.reservas.dto.ReservationResponseDTO;
import com.hotel.reservas.dto.RoomDTO;
import com.hotel.reservas.dto.ServiceRequestDTO;
import com.hotel.reservas.facade.ReservationFacade;
import com.hotel.reservas.model.ServiceType;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationFacade reservationFacade;

    public ReservationController(ReservationFacade reservationFacade) {
        this.reservationFacade = reservationFacade;
    }

    // GET /api/reservations/availability
    @GetMapping("/availability")
    public ResponseEntity<List<RoomDTO>> getAvailability() {
        return ResponseEntity.ok(reservationFacade.getAvailability());
    }

    // GET /api/reservations/rooms
    @GetMapping("/rooms")
    public ResponseEntity<List<RoomDTO>> getAllRooms() {
        return ResponseEntity.ok(reservationFacade.getAllRooms());
    }

    // POST /api/reservations
    @PostMapping
    public ResponseEntity<?> createReservation(@RequestBody ReservationRequestDTO dto) {
        try {
            ReservationResponseDTO reservation = reservationFacade.createReservation(dto);
            return ResponseEntity.ok(reservation);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    // GET /api/reservations/{reservationId}
    @GetMapping("/{reservationId}")
    public ResponseEntity<?> getReservation(@PathVariable String reservationId) {
        try {
            return ResponseEntity.ok(reservationFacade.getReservationDTO(reservationId));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    // POST /api/reservations/{reservationId}/services
    @PostMapping("/{reservationId}/services")
    public ResponseEntity<?> addService(
            @PathVariable String reservationId,
            @RequestBody ServiceRequestDTO dto) {
        try {
            ReservationResponseDTO reservation = reservationFacade.addService(reservationId, dto.getServiceType());
            return ResponseEntity.ok(reservation);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    // PUT /api/reservations/{reservationId}/check-in
    @PutMapping("/{reservationId}/check-in")
    public ResponseEntity<?> performCheckIn(@PathVariable String reservationId) {
        try {
            return ResponseEntity.ok(reservationFacade.performCheckIn(reservationId));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    // PUT /api/reservations/{reservationId}/check-out
    @PutMapping("/{reservationId}/check-out")
    public ResponseEntity<?> performCheckOut(@PathVariable String reservationId) {
        try {
            return ResponseEntity.ok(reservationFacade.performCheckOut(reservationId));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    // GET /api/reservations/{reservationId}/invoice
    @GetMapping("/{reservationId}/invoice")
    public ResponseEntity<?> getInvoice(@PathVariable String reservationId) {
        try {
            return ResponseEntity.ok(reservationFacade.getInvoice(reservationId));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    // GET /api/reservations/services/types
    @GetMapping("/services/types")
    public ResponseEntity<?> getServiceTypes() {
        var types = java.util.Arrays.stream(ServiceType.values()).map(s ->
                Map.of("type", s.name(), "description", s.getDescription(), "cost", s.getCost())
        ).toList();
        return ResponseEntity.ok(types);
    }
}
