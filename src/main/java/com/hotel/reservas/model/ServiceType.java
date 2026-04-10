package com.hotel.reservas.model;

public enum ServiceType {
    SPA(50.0, "Spa and relaxation"),
    BREAKFAST(15.0, "Breakfast included per day"),
    TRANSFER(30.0, "Airport transfer");

    private final double cost;
    private final String description;

    ServiceType(double cost, String description) {
        this.cost = cost;
        this.description = description;
    }

    public double getCost() { return cost; }
    public String getDescription() { return description; }
}
