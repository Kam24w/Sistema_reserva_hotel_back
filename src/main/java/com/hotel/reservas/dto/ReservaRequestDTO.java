package com.hotel.reservas.dto;

import java.time.LocalDate;

public class ReservaRequestDTO {
    private String nombreHuesped;
    private String emailHuesped;
    private String telefonoHuesped;
    private String numeroHabitacion;
    private LocalDate fechaIngreso;
    private LocalDate fechaSalida;

    public String getNombreHuesped() { return nombreHuesped; }
    public void setNombreHuesped(String nombreHuesped) { this.nombreHuesped = nombreHuesped; }
    public String getEmailHuesped() { return emailHuesped; }
    public void setEmailHuesped(String emailHuesped) { this.emailHuesped = emailHuesped; }
    public String getTelefonoHuesped() { return telefonoHuesped; }
    public void setTelefonoHuesped(String telefonoHuesped) { this.telefonoHuesped = telefonoHuesped; }
    public String getNumeroHabitacion() { return numeroHabitacion; }
    public void setNumeroHabitacion(String numeroHabitacion) { this.numeroHabitacion = numeroHabitacion; }
    public LocalDate getFechaIngreso() { return fechaIngreso; }
    public void setFechaIngreso(LocalDate fechaIngreso) { this.fechaIngreso = fechaIngreso; }
    public LocalDate getFechaSalida() { return fechaSalida; }
    public void setFechaSalida(LocalDate fechaSalida) { this.fechaSalida = fechaSalida; }
}
