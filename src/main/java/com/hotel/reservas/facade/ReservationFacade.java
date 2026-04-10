package com.hotel.reservas.facade;

import com.hotel.reservas.dto.*;
import com.hotel.reservas.model.*;
import com.hotel.reservas.service.*;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * ReservationFacade - Facade Pattern
 * Provides a unified and simplified interface for all
 * hotel operations, hiding the complexity of internal subsystems:
 * - RoomService
 * - PricingService
 * - AdditionalServiceService
 * - InvoicingService
 * - AccessService
 */
@Component
public class ReservationFacade {

    private final RoomService roomService;
    private final PricingService pricingService;
    private final AdditionalServiceService additionalServiceService;
    private final InvoicingService invoicingService;
    private final AccessService accessService;

    // In-memory reservations storage
    private final Map<String, Reservation> reservations = new HashMap<>();

    public ReservationFacade(RoomService roomService,
                            PricingService pricingService,
                            AdditionalServiceService additionalServiceService,
                            InvoicingService invoicingService,
                            AccessService accessService) {
        this.roomService = roomService;
        this.pricingService = pricingService;
        this.additionalServiceService = additionalServiceService;
        this.invoicingService = invoicingService;
        this.accessService = accessService;
    }

    // ─────────────────────────────────────────────
    // 1. CREATE RESERVATION
    // ─────────────────────────────────────────────
    public ReservationResponseDTO createReservation(ReservationRequestDTO dto) {
        // Subsystem 1: Verify and reserve room
        Room room = roomService.findRoom(dto.getRoomNumber())
                .orElseThrow(() -> new RuntimeException("Room not found: " + dto.getRoomNumber()));

        if (!room.isAvailable()) {
            throw new RuntimeException("Room " + dto.getRoomNumber() + " is not available.");
        }

        roomService.reserveRoom(dto.getRoomNumber());

        // Subsystem 2: Calculate pricing
        boolean isHighSeason = pricingService.isHighSeason(dto.getCheckInDate());
        double baseTotal = pricingService.calculateRoomTotal(
                room, dto.getCheckInDate(), dto.getCheckOutDate()
        );

        // Create reservation
        String reservationId = UUID.randomUUID().toString();
        Reservation reservation = new Reservation(
                reservationId,
                dto.getGuestName(),
                dto.getGuestEmail(),
                dto.getGuestPhone(),
                room,
                dto.getCheckInDate(),
                dto.getCheckOutDate()
        );
        reservation.setHighSeason(isHighSeason);
        reservation.setBaseTotal(baseTotal);
        reservation.setFinalTotal(baseTotal);

        reservations.put(reservationId, reservation);

        System.out.println("[ReservationFacade] Reservation created: " + reservationId);
        return mapToDTO(reservation);
    }

    // ─────────────────────────────────────────────
    // 2. ADD SERVICE
    // ─────────────────────────────────────────────
    public ReservationResponseDTO addService(String reservationId, ServiceType serviceType) {
        Reservation reservation = getReservation(reservationId);

        if (reservation.getStatus() == ReservationStatus.CHECK_OUT || reservation.getStatus() == ReservationStatus.CANCELLED) {
            throw new RuntimeException("Cannot add services to a reservation in status: " + reservation.getStatus());
        }

        // Subsystem 3: Add additional service
        additionalServiceService.addService(reservation, serviceType);

        // Recalculate totals
        double servicesTotal = additionalServiceService.calculateServicesTotal(reservation);
        reservation.setServicesTotal(servicesTotal);
        reservation.setFinalTotal(reservation.getBaseTotal() + servicesTotal);

        System.out.println("[ReservationFacade] Service " + serviceType + " added to reservation: " + reservationId);
        return mapToDTO(reservation);
    }

    // ─────────────────────────────────────────────
    // 3. CHECK-IN
    // ─────────────────────────────────────────────
    public ReservationResponseDTO performCheckIn(String reservationId) {
        Reservation reservation = getReservation(reservationId);

        if (reservation.getStatus() != ReservationStatus.CONFIRMED) {
            throw new RuntimeException("The reservation must be CONFIRMED to perform check-in. Current status: " + reservation.getStatus());
        }

        // Subsystem 5: Generate digital key
        String key = accessService.generateDigitalKey(reservationId, reservation.getRoom().getNumber());
        reservation.setDigitalKey(key);
        reservation.setStatus(ReservationStatus.CHECK_IN);

        System.out.println("[ReservationFacade] Check-in performed for reservation: " + reservationId + " | Key: " + key);
        return mapToDTO(reservation);
    }

    // ─────────────────────────────────────────────
    // 4. CHECK-OUT
    // ─────────────────────────────────────────────
    public InvoiceDTO performCheckOut(String reservationId) {
        Reservation reservation = getReservation(reservationId);

        if (reservation.getStatus() != ReservationStatus.CHECK_IN) {
            throw new RuntimeException("Check-in must be performed before check-out. Current status: " + reservation.getStatus());
        }

        // Subsystem 3: Calculate final services total
        double servicesTotal = additionalServiceService.calculateServicesTotal(reservation);
        reservation.setServicesTotal(servicesTotal);
        reservation.setFinalTotal(reservation.getBaseTotal() + servicesTotal);
        reservation.setStatus(ReservationStatus.CHECK_OUT);

        // Release room
        roomService.releaseRoom(reservation.getRoom().getNumber());

        // Subsystem 4: Generate invoice
        InvoiceDTO invoice = invoicingService.generateInvoice(reservation, servicesTotal);

        System.out.println("[ReservationFacade] Check-out performed for reservation: " + reservationId);
        return invoice;
    }

    // ─────────────────────────────────────────────
    // QUERIES
    // ─────────────────────────────────────────────
    public List<RoomDTO> getAvailability() {
        return roomService.getAvailableRooms()
                .stream().map(this::mapRoomToDTO).toList();
    }

    public List<RoomDTO> getAllRooms() {
        return roomService.getAllRooms()
                .stream().map(this::mapRoomToDTO).toList();
    }

    public ReservationResponseDTO getReservationDTO(String reservationId) {
        return mapToDTO(getReservation(reservationId));
    }

    public InvoiceDTO getInvoice(String reservationId) {
        Reservation reservation = getReservation(reservationId);
        double servicesTotal = additionalServiceService.calculateServicesTotal(reservation);
        return invoicingService.generateInvoice(reservation, servicesTotal);
    }

    // ─────────────────────────────────────────────
    // HELPERS
    // ─────────────────────────────────────────────
    private Reservation getReservation(String reservationId) {
        Reservation reservation = reservations.get(reservationId);
        if (reservation == null) {
            throw new RuntimeException("Reservation not found: " + reservationId);
        }
        return reservation;
    }

    private ReservationResponseDTO mapToDTO(Reservation reservation) {
        ReservationResponseDTO dto = new ReservationResponseDTO();
        dto.setId(reservation.getId());
        dto.setGuestName(reservation.getGuestName());
        dto.setGuestEmail(reservation.getGuestEmail());
        dto.setGuestPhone(reservation.getGuestPhone());
        dto.setRoom(mapRoomToDTO(reservation.getRoom()));
        dto.setCheckInDate(reservation.getCheckInDate());
        dto.setCheckOutDate(reservation.getCheckOutDate());
        dto.setNightsCount(reservation.getNightsCount());
        dto.setStatus(reservation.getStatus());
        dto.setAdditionalServices(reservation.getAdditionalServices());
        dto.setDigitalKey(reservation.getDigitalKey());
        dto.setHighSeason(reservation.isHighSeason());
        dto.setBaseTotal(reservation.getBaseTotal());
        dto.setServicesTotal(reservation.getServicesTotal());
        dto.setFinalTotal(reservation.getFinalTotal());
        return dto;
    }

    private RoomDTO mapRoomToDTO(Room r) {
        RoomDTO dto = new RoomDTO();
        dto.setNumber(r.getNumber());
        dto.setType(r.getType());
        dto.setBasePrice(r.getBasePrice());
        dto.setAvailable(r.isAvailable());
        dto.setDescription(r.getDescription());
        return dto;
    }
}
