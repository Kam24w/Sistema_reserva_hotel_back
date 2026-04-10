package com.hotel.reservas.model;

public enum TipoServicio {
    SPA(50.0, "Spa y relajación"),
    DESAYUNO(15.0, "Desayuno incluido por día"),
    TRASLADO(30.0, "Traslado aeropuerto");

    private final double costo;
    private final String descripcion;

    TipoServicio(double costo, String descripcion) {
        this.costo = costo;
        this.descripcion = descripcion;
    }

    public double getCosto() { return costo; }
    public String getDescripcion() { return descripcion; }
}
