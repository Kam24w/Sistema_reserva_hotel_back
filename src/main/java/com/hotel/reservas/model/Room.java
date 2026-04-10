package com.hotel.reservas.model;

public class Room {
    private String number;
    private RoomType type;
    private double basePrice;
    private boolean available;
    private String description;

    public Room(String number, RoomType type, double basePrice, String description) {
        this.number = number;
        this.type = type;
        this.basePrice = basePrice;
        this.available = true;
        this.description = description;
    }

    public String getNumber() { return number; }
    public RoomType getType() { return type; }
    public double getBasePrice() { return basePrice; }
    public boolean isAvailable() { return available; }
    public String getDescription() { return description; }
    public void setAvailable(boolean available) { this.available = available; }
}
