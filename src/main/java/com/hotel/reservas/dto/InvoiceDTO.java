package com.hotel.reservas.dto;

import com.hotel.reservas.model.ServiceType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class InvoiceDTO {
    private String invoiceNumber;
    private String reservationId;
    private LocalDateTime emissionDate;
    private String guestName;
    private String guestEmail;
    private String roomNumber;
    private String roomType;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private long nightsCount;
    private boolean highSeason;
    private double baseNightPrice;
    private double seasonFactor;
    private double roomSubtotal;
    private List<Map<String, Object>> servicesDetail;
    private double servicesTotal;
    private double finalTotal;
    private String digitalKey;

    public String getInvoiceNumber() { return invoiceNumber; }
    public void setInvoiceNumber(String v) { this.invoiceNumber = v; }
    public String getReservationId() { return reservationId; }
    public void setReservationId(String v) { this.reservationId = v; }
    public LocalDateTime getEmissionDate() { return emissionDate; }
    public void setEmissionDate(LocalDateTime v) { this.emissionDate = v; }
    public String getGuestName() { return guestName; }
    public void setGuestName(String v) { this.guestName = v; }
    public String getGuestEmail() { return guestEmail; }
    public void setGuestEmail(String v) { this.guestEmail = v; }
    public String getRoomNumber() { return roomNumber; }
    public void setRoomNumber(String v) { this.roomNumber = v; }
    public String getRoomType() { return roomType; }
    public void setRoomType(String v) { this.roomType = v; }
    public LocalDate getCheckInDate() { return checkInDate; }
    public void setCheckInDate(LocalDate v) { this.checkInDate = v; }
    public LocalDate getCheckOutDate() { return checkOutDate; }
    public void setCheckOutDate(LocalDate v) { this.checkOutDate = v; }
    public long getNightsCount() { return nightsCount; }
    public void setNightsCount(long v) { this.nightsCount = v; }
    public boolean isHighSeason() { return highSeason; }
    public void setHighSeason(boolean v) { this.highSeason = v; }
    public double getBaseNightPrice() { return baseNightPrice; }
    public void setBaseNightPrice(double v) { this.baseNightPrice = v; }
    public double getSeasonFactor() { return seasonFactor; }
    public void setSeasonFactor(double v) { this.seasonFactor = v; }
    public double getRoomSubtotal() { return roomSubtotal; }
    public void setRoomSubtotal(double v) { this.roomSubtotal = v; }
    public List<Map<String, Object>> getServicesDetail() { return servicesDetail; }
    public void setServicesDetail(List<Map<String, Object>> v) { this.servicesDetail = v; }
    public double getServicesTotal() { return servicesTotal; }
    public void setServicesTotal(double v) { this.servicesTotal = v; }
    public double getFinalTotal() { return finalTotal; }
    public void setFinalTotal(double v) { this.finalTotal = v; }
    public String getDigitalKey() { return digitalKey; }
    public void setDigitalKey(String v) { this.digitalKey = v; }
}
