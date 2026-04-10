package com.hotel.reservas.service;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AccesoService {

    public String generarLlaveDigital(String reservaId, String numeroHabitacion) {
        // Generamos un UUID único combinando reservaId y habitación
        String semilla = reservaId + "-" + numeroHabitacion;
        String llave = "HOTEL-" + UUID.nameUUIDFromBytes(semilla.getBytes()).toString().toUpperCase();
        System.out.printf("[AccesoService] Llave digital generada para reserva %s, habitación %s: %s%n",
                reservaId, numeroHabitacion, llave);
        return llave;
    }

    public boolean validarLlave(String llave, String reservaId, String numeroHabitacion) {
        String llaveEsperada = "HOTEL-" + UUID.nameUUIDFromBytes(
                (reservaId + "-" + numeroHabitacion).getBytes()
        ).toString().toUpperCase();
        return llaveEsperada.equals(llave);
    }
}
