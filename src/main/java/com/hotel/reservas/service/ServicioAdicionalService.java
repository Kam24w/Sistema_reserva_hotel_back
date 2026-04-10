package com.hotel.reservas.service;

import com.hotel.reservas.model.Reserva;
import com.hotel.reservas.model.TipoServicio;
import org.springframework.stereotype.Service;

@Service
public class ServicioAdicionalService {

    public void agregarServicio(Reserva reserva, TipoServicio tipoServicio) {
        reserva.agregarServicio(tipoServicio);
        System.out.printf("[ServicioAdicionalService] Servicio '%s' ($%.2f) agregado a reserva %s%n",
                tipoServicio.getDescripcion(), tipoServicio.getCosto(), reserva.getId());
    }

    public double calcularTotalServicios(Reserva reserva) {
        double total = reserva.getServiciosAdicionales().stream()
                .mapToDouble(servicio -> {
                    // Desayuno se cobra por día
                    if (servicio == TipoServicio.DESAYUNO) {
                        return servicio.getCosto() * reserva.getNumeroNoches();
                    }
                    return servicio.getCosto();
                })
                .sum();
        System.out.printf("[ServicioAdicionalService] Total servicios adicionales reserva %s: $%.2f%n",
                reserva.getId(), total);
        return total;
    }

    public double calcularCostoServicio(TipoServicio tipoServicio, long noches) {
        if (tipoServicio == TipoServicio.DESAYUNO) {
            return tipoServicio.getCosto() * noches;
        }
        return tipoServicio.getCosto();
    }
}
