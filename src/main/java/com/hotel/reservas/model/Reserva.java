package com.hotel.reservas.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Reserva {
    private String id;
    private String nombreHuesped;
    private String emailHuesped;
    private String telefonoHuesped;
    private Habitacion habitacion;
    private LocalDate fechaIngreso;
    private LocalDate fechaSalida;
    private EstadoReserva estado;
    private List<TipoServicio> serviciosAdicionales;
    private String llaveDigital;
    private double totalBase;
    private double totalServicios;
    private double totalFinal;
    private boolean temporadaAlta;

    public Reserva(String id, String nombreHuesped, String emailHuesped, String telefonoHuesped,
                   Habitacion habitacion, LocalDate fechaIngreso, LocalDate fechaSalida) {
        this.id = id;
        this.nombreHuesped = nombreHuesped;
        this.emailHuesped = emailHuesped;
        this.telefonoHuesped = telefonoHuesped;
        this.habitacion = habitacion;
        this.fechaIngreso = fechaIngreso;
        this.fechaSalida = fechaSalida;
        this.estado = EstadoReserva.CONFIRMADA;
        this.serviciosAdicionales = new ArrayList<>();
    }

    public String getId() { return id; }
    public String getNombreHuesped() { return nombreHuesped; }
    public String getEmailHuesped() { return emailHuesped; }
    public String getTelefonoHuesped() { return telefonoHuesped; }
    public Habitacion getHabitacion() { return habitacion; }
    public LocalDate getFechaIngreso() { return fechaIngreso; }
    public LocalDate getFechaSalida() { return fechaSalida; }
    public EstadoReserva getEstado() { return estado; }
    public List<TipoServicio> getServiciosAdicionales() { return serviciosAdicionales; }
    public String getLlaveDigital() { return llaveDigital; }
    public double getTotalBase() { return totalBase; }
    public double getTotalServicios() { return totalServicios; }
    public double getTotalFinal() { return totalFinal; }
    public boolean isTemporadaAlta() { return temporadaAlta; }

    public void setEstado(EstadoReserva estado) { this.estado = estado; }
    public void setLlaveDigital(String llaveDigital) { this.llaveDigital = llaveDigital; }
    public void setTotalBase(double totalBase) { this.totalBase = totalBase; }
    public void setTotalServicios(double totalServicios) { this.totalServicios = totalServicios; }
    public void setTotalFinal(double totalFinal) { this.totalFinal = totalFinal; }
    public void setTemporadaAlta(boolean temporadaAlta) { this.temporadaAlta = temporadaAlta; }

    public void agregarServicio(TipoServicio servicio) {
        this.serviciosAdicionales.add(servicio);
    }

    public long getNumeroNoches() {
        return fechaIngreso.until(fechaSalida).getDays();
    }
}
