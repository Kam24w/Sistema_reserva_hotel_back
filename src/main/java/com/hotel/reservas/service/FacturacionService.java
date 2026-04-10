package com.hotel.reservas.service;

import com.hotel.reservas.dto.FacturaDTO;
import com.hotel.reservas.model.Reserva;
import com.hotel.reservas.model.TipoServicio;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class FacturacionService {

    public FacturaDTO generarFactura(Reserva reserva, double totalServicios) {
        FacturaDTO factura = new FacturaDTO();

        factura.setNumeroFactura("FAC-" + reserva.getId().substring(0, 8).toUpperCase());
        factura.setReservaId(reserva.getId());
        factura.setFechaEmision(LocalDateTime.now());
        factura.setNombreHuesped(reserva.getNombreHuesped());
        factura.setEmailHuesped(reserva.getEmailHuesped());
        factura.setNumeroHabitacion(reserva.getHabitacion().getNumero());
        factura.setTipoHabitacion(reserva.getHabitacion().getTipo().name());
        factura.setFechaIngreso(reserva.getFechaIngreso());
        factura.setFechaSalida(reserva.getFechaSalida());
        factura.setNumeroNoches(reserva.getNumeroNoches());
        factura.setTemporadaAlta(reserva.isTemporadaAlta());
        factura.setPrecioBaseNoche(reserva.getHabitacion().getPrecioBase());
        factura.setFactorTemporada(reserva.isTemporadaAlta() ? 1.5 : 1.0);
        factura.setSubtotalHabitacion(reserva.getTotalBase());

        // Detalle de servicios
        List<Map<String, Object>> detalleServicios = new ArrayList<>();
        for (TipoServicio servicio : reserva.getServiciosAdicionales()) {
            Map<String, Object> detalle = new LinkedHashMap<>();
            detalle.put("servicio", servicio.name());
            detalle.put("descripcion", servicio.getDescripcion());
            double costo = servicio == TipoServicio.DESAYUNO
                    ? servicio.getCosto() * reserva.getNumeroNoches()
                    : servicio.getCosto();
            detalle.put("costo", costo);
            if (servicio == TipoServicio.DESAYUNO) {
                detalle.put("nota", "$" + servicio.getCosto() + " x " + reserva.getNumeroNoches() + " noches");
            }
            detalleServicios.add(detalle);
        }
        factura.setDetalleServicios(detalleServicios);
        factura.setTotalServicios(totalServicios);
        factura.setTotalFinal(reserva.getTotalBase() + totalServicios);
        factura.setLlaveDigital(reserva.getLlaveDigital());

        System.out.printf("[FacturacionService] Factura %s generada. Total: $%.2f%n",
                factura.getNumeroFactura(), factura.getTotalFinal());

        return factura;
    }
}
