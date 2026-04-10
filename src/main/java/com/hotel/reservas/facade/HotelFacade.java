package com.hotel.reservas.facade;

import com.hotel.reservas.dto.*;
import com.hotel.reservas.model.*;
import com.hotel.reservas.service.*;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * HotelFacade - Patrón Facade
 * Proporciona una interfaz unificada y simplificada para todas las
 * operaciones del hotel, ocultando la complejidad de los subsistemas internos:
 * - HabitacionService
 * - TarifaService
 * - ServicioAdicionalService
 * - FacturacionService
 * - AccesoService
 */
@Component
public class HotelFacade {

    private final HabitacionService habitacionService;
    private final TarifaService tarifaService;
    private final ServicioAdicionalService servicioAdicionalService;
    private final FacturacionService facturacionService;
    private final AccesoService accesoService;

    // Estado en memoria
    private final Map<String, Reserva> reservas = new HashMap<>();

    public HotelFacade(HabitacionService habitacionService,
                       TarifaService tarifaService,
                       ServicioAdicionalService servicioAdicionalService,
                       FacturacionService facturacionService,
                       AccesoService accesoService) {
        this.habitacionService = habitacionService;
        this.tarifaService = tarifaService;
        this.servicioAdicionalService = servicioAdicionalService;
        this.facturacionService = facturacionService;
        this.accesoService = accesoService;
    }

    // ─────────────────────────────────────────────
    // 1. CREAR RESERVA
    // ─────────────────────────────────────────────
    public ReservaResponseDTO crearReserva(ReservaRequestDTO dto) {
        // Subsistema 1: Verificar y reservar habitación
        Habitacion habitacion = habitacionService.buscarHabitacion(dto.getNumeroHabitacion())
                .orElseThrow(() -> new RuntimeException("Habitación no encontrada: " + dto.getNumeroHabitacion()));

        if (!habitacion.isDisponible()) {
            throw new RuntimeException("Habitación " + dto.getNumeroHabitacion() + " no está disponible.");
        }

        habitacionService.reservarHabitacion(dto.getNumeroHabitacion());

        // Subsistema 2: Calcular tarifa
        boolean esTemporadaAlta = tarifaService.esTemporadaAlta(dto.getFechaIngreso());
        double totalBase = tarifaService.calcularTotalHabitacion(
                habitacion, dto.getFechaIngreso(), dto.getFechaSalida()
        );

        // Crear reserva
        String reservaId = UUID.randomUUID().toString();
        Reserva reserva = new Reserva(
                reservaId,
                dto.getNombreHuesped(),
                dto.getEmailHuesped(),
                dto.getTelefonoHuesped(),
                habitacion,
                dto.getFechaIngreso(),
                dto.getFechaSalida()
        );
        reserva.setTemporadaAlta(esTemporadaAlta);
        reserva.setTotalBase(totalBase);
        reserva.setTotalFinal(totalBase);

        reservas.put(reservaId, reserva);

        System.out.println("[HotelFacade] Reserva creada: " + reservaId);
        return mapToDTO(reserva);
    }

    // ─────────────────────────────────────────────
    // 2. AGREGAR SERVICIO
    // ─────────────────────────────────────────────
    public ReservaResponseDTO agregarServicio(String reservaId, TipoServicio tipoServicio) {
        Reserva reserva = obtenerReserva(reservaId);

        if (reserva.getEstado() == EstadoReserva.CHECKOUT || reserva.getEstado() == EstadoReserva.CANCELADA) {
            throw new RuntimeException("No se pueden agregar servicios a una reserva en estado: " + reserva.getEstado());
        }

        // Subsistema 3: Agregar servicio adicional
        servicioAdicionalService.agregarServicio(reserva, tipoServicio);

        // Recalcular totales
        double totalServicios = servicioAdicionalService.calcularTotalServicios(reserva);
        reserva.setTotalServicios(totalServicios);
        reserva.setTotalFinal(reserva.getTotalBase() + totalServicios);

        System.out.println("[HotelFacade] Servicio " + tipoServicio + " agregado a reserva: " + reservaId);
        return mapToDTO(reserva);
    }

    // ─────────────────────────────────────────────
    // 3. CHECK-IN
    // ─────────────────────────────────────────────
    public ReservaResponseDTO realizarCheckIn(String reservaId) {
        Reserva reserva = obtenerReserva(reservaId);

        if (reserva.getEstado() != EstadoReserva.CONFIRMADA) {
            throw new RuntimeException("La reserva debe estar CONFIRMADA para hacer check-in. Estado actual: " + reserva.getEstado());
        }

        // Subsistema 5: Generar llave digital
        String llave = accesoService.generarLlaveDigital(reservaId, reserva.getHabitacion().getNumero());
        reserva.setLlaveDigital(llave);
        reserva.setEstado(EstadoReserva.CHECKIN);

        System.out.println("[HotelFacade] Check-in realizado para reserva: " + reservaId + " | Llave: " + llave);
        return mapToDTO(reserva);
    }

    // ─────────────────────────────────────────────
    // 4. CHECK-OUT
    // ─────────────────────────────────────────────
    public FacturaDTO realizarCheckOut(String reservaId) {
        Reserva reserva = obtenerReserva(reservaId);

        if (reserva.getEstado() != EstadoReserva.CHECKIN) {
            throw new RuntimeException("Debe haberse realizado el check-in antes del check-out. Estado actual: " + reserva.getEstado());
        }

        // Subsistema 3: Calcular total servicios final
        double totalServicios = servicioAdicionalService.calcularTotalServicios(reserva);
        reserva.setTotalServicios(totalServicios);
        reserva.setTotalFinal(reserva.getTotalBase() + totalServicios);
        reserva.setEstado(EstadoReserva.CHECKOUT);

        // Liberar habitación
        habitacionService.liberarHabitacion(reserva.getHabitacion().getNumero());

        // Subsistema 4: Generar factura
        FacturaDTO factura = facturacionService.generarFactura(reserva, totalServicios);

        System.out.println("[HotelFacade] Check-out realizado para reserva: " + reservaId);
        return factura;
    }

    // ─────────────────────────────────────────────
    // CONSULTAS
    // ─────────────────────────────────────────────
    public List<HabitacionDTO> consultarDisponibilidad() {
        return habitacionService.consultarDisponibilidad()
                .stream().map(this::mapHabitacionToDTO).toList();
    }

    public List<HabitacionDTO> todasLasHabitaciones() {
        return habitacionService.todasLasHabitaciones()
                .stream().map(this::mapHabitacionToDTO).toList();
    }

    public ReservaResponseDTO obtenerReservaDTO(String reservaId) {
        return mapToDTO(obtenerReserva(reservaId));
    }

    public FacturaDTO obtenerFactura(String reservaId) {
        Reserva reserva = obtenerReserva(reservaId);
        double totalServicios = servicioAdicionalService.calcularTotalServicios(reserva);
        return facturacionService.generarFactura(reserva, totalServicios);
    }

    // ─────────────────────────────────────────────
    // HELPERS
    // ─────────────────────────────────────────────
    private Reserva obtenerReserva(String reservaId) {
        Reserva reserva = reservas.get(reservaId);
        if (reserva == null) {
            throw new RuntimeException("Reserva no encontrada: " + reservaId);
        }
        return reserva;
    }

    private ReservaResponseDTO mapToDTO(Reserva reserva) {
        ReservaResponseDTO dto = new ReservaResponseDTO();
        dto.setId(reserva.getId());
        dto.setNombreHuesped(reserva.getNombreHuesped());
        dto.setEmailHuesped(reserva.getEmailHuesped());
        dto.setTelefonoHuesped(reserva.getTelefonoHuesped());
        dto.setHabitacion(mapHabitacionToDTO(reserva.getHabitacion()));
        dto.setFechaIngreso(reserva.getFechaIngreso());
        dto.setFechaSalida(reserva.getFechaSalida());
        dto.setNumeroNoches(reserva.getNumeroNoches());
        dto.setEstado(reserva.getEstado());
        dto.setServiciosAdicionales(reserva.getServiciosAdicionales());
        dto.setLlaveDigital(reserva.getLlaveDigital());
        dto.setTemporadaAlta(reserva.isTemporadaAlta());
        dto.setTotalBase(reserva.getTotalBase());
        dto.setTotalServicios(reserva.getTotalServicios());
        dto.setTotalFinal(reserva.getTotalFinal());
        return dto;
    }

    private HabitacionDTO mapHabitacionToDTO(Habitacion h) {
        HabitacionDTO dto = new HabitacionDTO();
        dto.setNumero(h.getNumero());
        dto.setTipo(h.getTipo());
        dto.setPrecioBase(h.getPrecioBase());
        dto.setDisponible(h.isDisponible());
        dto.setDescripcion(h.getDescripcion());
        return dto;
    }
}
