package com.hotel.reservas.dto;

import com.hotel.reservas.model.TipoServicio;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class FacturaDTO {
    private String numeroFactura;
    private String reservaId;
    private LocalDateTime fechaEmision;
    private String nombreHuesped;
    private String emailHuesped;
    private String numeroHabitacion;
    private String tipoHabitacion;
    private LocalDate fechaIngreso;
    private LocalDate fechaSalida;
    private long numeroNoches;
    private boolean temporadaAlta;
    private double precioBaseNoche;
    private double factorTemporada;
    private double subtotalHabitacion;
    private List<Map<String, Object>> detalleServicios;
    private double totalServicios;
    private double totalFinal;
    private String llaveDigital;

    public String getNumeroFactura() { return numeroFactura; }
    public void setNumeroFactura(String v) { this.numeroFactura = v; }
    public String getReservaId() { return reservaId; }
    public void setReservaId(String v) { this.reservaId = v; }
    public LocalDateTime getFechaEmision() { return fechaEmision; }
    public void setFechaEmision(LocalDateTime v) { this.fechaEmision = v; }
    public String getNombreHuesped() { return nombreHuesped; }
    public void setNombreHuesped(String v) { this.nombreHuesped = v; }
    public String getEmailHuesped() { return emailHuesped; }
    public void setEmailHuesped(String v) { this.emailHuesped = v; }
    public String getNumeroHabitacion() { return numeroHabitacion; }
    public void setNumeroHabitacion(String v) { this.numeroHabitacion = v; }
    public String getTipoHabitacion() { return tipoHabitacion; }
    public void setTipoHabitacion(String v) { this.tipoHabitacion = v; }
    public LocalDate getFechaIngreso() { return fechaIngreso; }
    public void setFechaIngreso(LocalDate v) { this.fechaIngreso = v; }
    public LocalDate getFechaSalida() { return fechaSalida; }
    public void setFechaSalida(LocalDate v) { this.fechaSalida = v; }
    public long getNumeroNoches() { return numeroNoches; }
    public void setNumeroNoches(long v) { this.numeroNoches = v; }
    public boolean isTemporadaAlta() { return temporadaAlta; }
    public void setTemporadaAlta(boolean v) { this.temporadaAlta = v; }
    public double getPrecioBaseNoche() { return precioBaseNoche; }
    public void setPrecioBaseNoche(double v) { this.precioBaseNoche = v; }
    public double getFactorTemporada() { return factorTemporada; }
    public void setFactorTemporada(double v) { this.factorTemporada = v; }
    public double getSubtotalHabitacion() { return subtotalHabitacion; }
    public void setSubtotalHabitacion(double v) { this.subtotalHabitacion = v; }
    public List<Map<String, Object>> getDetalleServicios() { return detalleServicios; }
    public void setDetalleServicios(List<Map<String, Object>> v) { this.detalleServicios = v; }
    public double getTotalServicios() { return totalServicios; }
    public void setTotalServicios(double v) { this.totalServicios = v; }
    public double getTotalFinal() { return totalFinal; }
    public void setTotalFinal(double v) { this.totalFinal = v; }
    public String getLlaveDigital() { return llaveDigital; }
    public void setLlaveDigital(String v) { this.llaveDigital = v; }
}
