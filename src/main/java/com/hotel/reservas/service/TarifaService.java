package com.hotel.reservas.service;

import com.hotel.reservas.model.Habitacion;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;

@Service
public class TarifaService {

    // Meses de temporada alta: Diciembre, Enero, Julio, Agosto
    private static final double FACTOR_TEMPORADA_ALTA = 1.5;
    private static final double FACTOR_TEMPORADA_BAJA = 1.0;

    public boolean esTemporadaAlta(LocalDate fecha) {
        Month mes = fecha.getMonth();
        return mes == Month.DECEMBER || mes == Month.JANUARY
                || mes == Month.JULY || mes == Month.AUGUST;
    }

    public double calcularPrecioNoche(Habitacion habitacion, LocalDate fechaIngreso) {
        double factor = esTemporadaAlta(fechaIngreso) ? FACTOR_TEMPORADA_ALTA : FACTOR_TEMPORADA_BAJA;
        double precio = habitacion.getPrecioBase() * factor;
        System.out.printf("[TarifaService] Habitación %s | Precio base: $%.2f | Factor: %.1f | Precio noche: $%.2f%n",
                habitacion.getNumero(), habitacion.getPrecioBase(), factor, precio);
        return precio;
    }

    public double calcularTotalHabitacion(Habitacion habitacion, LocalDate fechaIngreso, LocalDate fechaSalida) {
        long noches = fechaIngreso.until(fechaSalida).getDays();
        double precioNoche = calcularPrecioNoche(habitacion, fechaIngreso);
        double total = precioNoche * noches;
        System.out.printf("[TarifaService] Total habitación: $%.2f (%d noches x $%.2f)%n", total, noches, precioNoche);
        return total;
    }

    public double getFactorTemporada(LocalDate fechaIngreso) {
        return esTemporadaAlta(fechaIngreso) ? FACTOR_TEMPORADA_ALTA : FACTOR_TEMPORADA_BAJA;
    }
}
