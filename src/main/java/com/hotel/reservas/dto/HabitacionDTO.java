package com.hotel.reservas.dto;

import com.hotel.reservas.model.TipoHabitacion;

public class HabitacionDTO {
    private String numero;
    private TipoHabitacion tipo;
    private double precioBase;
    private boolean disponible;
    private String descripcion;

    public String getNumero() { return numero; }
    public void setNumero(String v) { this.numero = v; }
    public TipoHabitacion getTipo() { return tipo; }
    public void setTipo(TipoHabitacion v) { this.tipo = v; }
    public double getPrecioBase() { return precioBase; }
    public void setPrecioBase(double v) { this.precioBase = v; }
    public boolean isDisponible() { return disponible; }
    public void setDisponible(boolean v) { this.disponible = v; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String v) { this.descripcion = v; }
}
