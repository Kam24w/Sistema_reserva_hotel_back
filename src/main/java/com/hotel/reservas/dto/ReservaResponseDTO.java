package com.hotel.reservas.dto;

import com.hotel.reservas.model.EstadoReserva;
import com.hotel.reservas.model.TipoServicio;

import java.time.LocalDate;
import java.util.List;

public class ReservaResponseDTO {
    private String id;
    private String nombreHuesped;
    private String emailHuesped;
    private String telefonoHuesped;
    private HabitacionDTO habitacion;
    private LocalDate fechaIngreso;
    private LocalDate fechaSalida;
    private long numeroNoches;
    private EstadoReserva estado;
    private List<TipoServicio> serviciosAdicionales;
    private String llaveDigital;
    private boolean temporadaAlta;
    private double totalBase;
    private double totalServicios;
    private double totalFinal;

    // Getters y Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getNombreHuesped() { return nombreHuesped; }
    public void setNombreHuesped(String v) { this.nombreHuesped = v; }
    public String getEmailHuesped() { return emailHuesped; }
    public void setEmailHuesped(String v) { this.emailHuesped = v; }
    public String getTelefonoHuesped() { return telefonoHuesped; }
    public void setTelefonoHuesped(String v) { this.telefonoHuesped = v; }
    public HabitacionDTO getHabitacion() { return habitacion; }
    public void setHabitacion(HabitacionDTO v) { this.habitacion = v; }
    public LocalDate getFechaIngreso() { return fechaIngreso; }
    public void setFechaIngreso(LocalDate v) { this.fechaIngreso = v; }
    public LocalDate getFechaSalida() { return fechaSalida; }
    public void setFechaSalida(LocalDate v) { this.fechaSalida = v; }
    public long getNumeroNoches() { return numeroNoches; }
    public void setNumeroNoches(long v) { this.numeroNoches = v; }
    public EstadoReserva getEstado() { return estado; }
    public void setEstado(EstadoReserva v) { this.estado = v; }
    public List<TipoServicio> getServiciosAdicionales() { return serviciosAdicionales; }
    public void setServiciosAdicionales(List<TipoServicio> v) { this.serviciosAdicionales = v; }
    public String getLlaveDigital() { return llaveDigital; }
    public void setLlaveDigital(String v) { this.llaveDigital = v; }
    public boolean isTemporadaAlta() { return temporadaAlta; }
    public void setTemporadaAlta(boolean v) { this.temporadaAlta = v; }
    public double getTotalBase() { return totalBase; }
    public void setTotalBase(double v) { this.totalBase = v; }
    public double getTotalServicios() { return totalServicios; }
    public void setTotalServicios(double v) { this.totalServicios = v; }
    public double getTotalFinal() { return totalFinal; }
    public void setTotalFinal(double v) { this.totalFinal = v; }
}
