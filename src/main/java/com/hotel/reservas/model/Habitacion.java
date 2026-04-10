package com.hotel.reservas.model;

public class Habitacion {
    private String numero;
    private TipoHabitacion tipo;
    private double precioBase;
    private boolean disponible;
    private String descripcion;

    public Habitacion(String numero, TipoHabitacion tipo, double precioBase, String descripcion) {
        this.numero = numero;
        this.tipo = tipo;
        this.precioBase = precioBase;
        this.disponible = true;
        this.descripcion = descripcion;
    }

    public String getNumero() { return numero; }
    public TipoHabitacion getTipo() { return tipo; }
    public double getPrecioBase() { return precioBase; }
    public boolean isDisponible() { return disponible; }
    public String getDescripcion() { return descripcion; }
    public void setDisponible(boolean disponible) { this.disponible = disponible; }
}
