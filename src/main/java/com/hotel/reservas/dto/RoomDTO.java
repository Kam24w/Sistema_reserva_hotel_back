package com.hotel.reservas.dto;

import com.hotel.reservas.model.RoomType;

public class RoomDTO {
    private String number;
    private RoomType type;
    private double basePrice;
    private boolean available;
    private String description;

    public String getNumber() { return number; }
    public void setNumber(String v) { this.number = v; }
    public RoomType getType() { return type; }
    public void setType(RoomType v) { this.type = v; }
    public double getBasePrice() { return basePrice; }
    public void setBasePrice(double v) { this.basePrice = v; }
    public boolean isAvailable() { return available; }
    public void setAvailable(boolean v) { this.available = v; }
    public String getDescription() { return description; }
    public void setDescription(String v) { this.description = v; }
}
