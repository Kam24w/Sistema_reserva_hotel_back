package com.hotel.reservas.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Reservation {
    private String id;
    private String guestName;
    private String guestEmail;
    private String guestPhone;
    private Room room;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private ReservationStatus status;
    private List<ServiceType> additionalServices;
    private String digitalKey;
    private double baseTotal;
    private double servicesTotal;
    private double finalTotal;
    private boolean highSeason;

    public Reservation(String id, String guestName, String guestEmail, String guestPhone,
                   Room room, LocalDate checkInDate, LocalDate checkOutDate) {
        this.id = id;
        this.guestName = guestName;
        this.guestEmail = guestEmail;
        this.guestPhone = guestPhone;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.status = ReservationStatus.CONFIRMED;
        this.additionalServices = new ArrayList<>();
    }

    public String getId() { return id; }
    public String getGuestName() { return guestName; }
    public String getGuestEmail() { return guestEmail; }
    public String getGuestPhone() { return guestPhone; }
    public Room getRoom() { return room; }
    public LocalDate getCheckInDate() { return checkInDate; }
    public LocalDate getCheckOutDate() { return checkOutDate; }
    public ReservationStatus getStatus() { return status; }
    public List<ServiceType> getAdditionalServices() { return additionalServices; }
    public String getDigitalKey() { return digitalKey; }
    public double getBaseTotal() { return baseTotal; }
    public double getServicesTotal() { return servicesTotal; }
    public double getFinalTotal() { return finalTotal; }
    public boolean isHighSeason() { return highSeason; }

    public void setStatus(ReservationStatus status) { this.status = status; }
    public void setDigitalKey(String digitalKey) { this.digitalKey = digitalKey; }
    public void setBaseTotal(double baseTotal) { this.baseTotal = baseTotal; }
    public void setServicesTotal(double servicesTotal) { this.servicesTotal = servicesTotal; }
    public void setFinalTotal(double finalTotal) { this.finalTotal = finalTotal; }
    public void setHighSeason(boolean highSeason) { this.highSeason = highSeason; }

    public void addService(ServiceType service) {
        this.additionalServices.add(service);
    }

    public long getNightsCount() {
        return checkInDate.until(checkOutDate).getDays();
    }
}
