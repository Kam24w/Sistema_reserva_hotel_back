package com.hotel.reservas.dto;

import com.hotel.reservas.model.ReservationStatus;
import com.hotel.reservas.model.ServiceType;

import java.time.LocalDate;
import java.util.List;

public class ReservationResponseDTO {
    private String id;
    private String guestName;
    private String guestEmail;
    private String guestPhone;
    private RoomDTO room;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private long nightsCount;
    private ReservationStatus status;
    private List<ServiceType> additionalServices;
    private String digitalKey;
    private boolean highSeason;
    private double baseTotal;
    private double servicesTotal;
    private double finalTotal;

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getGuestName() { return guestName; }
    public void setGuestName(String v) { this.guestName = v; }
    public String getGuestEmail() { return guestEmail; }
    public void setGuestEmail(String v) { this.guestEmail = v; }
    public String getGuestPhone() { return guestPhone; }
    public void setGuestPhone(String v) { this.guestPhone = v; }
    public RoomDTO getRoom() { return room; }
    public void setRoom(RoomDTO v) { this.room = v; }
    public LocalDate getCheckInDate() { return checkInDate; }
    public void setCheckInDate(LocalDate v) { this.checkInDate = v; }
    public LocalDate getCheckOutDate() { return checkOutDate; }
    public void setCheckOutDate(LocalDate v) { this.checkOutDate = v; }
    public long getNightsCount() { return nightsCount; }
    public void setNightsCount(long v) { this.nightsCount = v; }
    public ReservationStatus getStatus() { return status; }
    public void setStatus(ReservationStatus v) { this.status = v; }
    public List<ServiceType> getAdditionalServices() { return additionalServices; }
    public void setAdditionalServices(List<ServiceType> v) { this.additionalServices = v; }
    public String getDigitalKey() { return digitalKey; }
    public void setDigitalKey(String v) { this.digitalKey = v; }
    public boolean isHighSeason() { return highSeason; }
    public void setHighSeason(boolean v) { this.highSeason = v; }
    public double getBaseTotal() { return baseTotal; }
    public void setBaseTotal(double v) { this.baseTotal = v; }
    public double getServicesTotal() { return servicesTotal; }
    public void setServicesTotal(double v) { this.servicesTotal = v; }
    public double getFinalTotal() { return finalTotal; }
    public void setFinalTotal(double v) { this.finalTotal = v; }
}
