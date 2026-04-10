package com.hotel.reservas.controller;

import com.hotel.reservas.dto.*;
import com.hotel.reservas.facade.HotelFacade;
import com.hotel.reservas.model.TipoServicio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/hotel")
@CrossOrigin(origins = "*")
public class HotelController {

    private final HotelFacade hotelFacade;

    public HotelController(HotelFacade hotelFacade) {
        this.hotelFacade = hotelFacade;
    }

    // GET /api/hotel/disponibilidad
    @GetMapping("/disponibilidad")
    public ResponseEntity<List<HabitacionDTO>> consultarDisponibilidad() {
        return ResponseEntity.ok(hotelFacade.consultarDisponibilidad());
    }

    // GET /api/hotel/habitaciones
    @GetMapping("/habitaciones")
    public ResponseEntity<List<HabitacionDTO>> todasLasHabitaciones() {
        return ResponseEntity.ok(hotelFacade.todasLasHabitaciones());
    }

    // POST /api/hotel/reservar
    @PostMapping("/reservar")
    public ResponseEntity<?> crearReserva(@RequestBody ReservaRequestDTO dto) {
        try {
            ReservaResponseDTO reserva = hotelFacade.crearReserva(dto);
            return ResponseEntity.ok(reserva);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    // GET /api/hotel/reserva/{reservaId}
    @GetMapping("/reserva/{reservaId}")
    public ResponseEntity<?> obtenerReserva(@PathVariable String reservaId) {
        try {
            return ResponseEntity.ok(hotelFacade.obtenerReservaDTO(reservaId));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    // POST /api/hotel/servicios/{reservaId}
    @PostMapping("/servicios/{reservaId}")
    public ResponseEntity<?> agregarServicio(
            @PathVariable String reservaId,
            @RequestBody ServicioRequestDTO dto) {
        try {
            ReservaResponseDTO reserva = hotelFacade.agregarServicio(reservaId, dto.getTipoServicio());
            return ResponseEntity.ok(reserva);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    // PUT /api/hotel/checkin/{reservaId}
    @PutMapping("/checkin/{reservaId}")
    public ResponseEntity<?> realizarCheckIn(@PathVariable String reservaId) {
        try {
            return ResponseEntity.ok(hotelFacade.realizarCheckIn(reservaId));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    // PUT /api/hotel/checkout/{reservaId}
    @PutMapping("/checkout/{reservaId}")
    public ResponseEntity<?> realizarCheckOut(@PathVariable String reservaId) {
        try {
            return ResponseEntity.ok(hotelFacade.realizarCheckOut(reservaId));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    // GET /api/hotel/factura/{reservaId}
    @GetMapping("/factura/{reservaId}")
    public ResponseEntity<?> obtenerFactura(@PathVariable String reservaId) {
        try {
            return ResponseEntity.ok(hotelFacade.obtenerFactura(reservaId));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    // GET /api/hotel/servicios/tipos - para que el frontend sepa qué servicios hay
    @GetMapping("/servicios/tipos")
    public ResponseEntity<?> tiposDeServicio() {
        var tipos = java.util.Arrays.stream(TipoServicio.values()).map(s ->
                Map.of("tipo", s.name(), "descripcion", s.getDescripcion(), "costo", s.getCosto())
        ).toList();
        return ResponseEntity.ok(tipos);
    }
}
