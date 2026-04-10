package com.hotel.reservas.service;

import com.hotel.reservas.model.Habitacion;
import com.hotel.reservas.model.TipoHabitacion;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class HabitacionService {

    private final Map<String, Habitacion> habitaciones = new LinkedHashMap<>();

    public HabitacionService() {
        inicializarHabitaciones();
    }

    private void inicializarHabitaciones() {
        // 6 Habitaciones Sencillas
        habitaciones.put("101", new Habitacion("101", TipoHabitacion.SENCILLA, 80.0, "Habitación sencilla con vista al jardín"));
        habitaciones.put("102", new Habitacion("102", TipoHabitacion.SENCILLA, 80.0, "Habitación sencilla con vista a la piscina"));
        habitaciones.put("103", new Habitacion("103", TipoHabitacion.SENCILLA, 85.0, "Habitación sencilla premium con balcón"));
        habitaciones.put("104", new Habitacion("104", TipoHabitacion.SENCILLA, 80.0, "Habitación sencilla estándar piso 1"));
        habitaciones.put("105", new Habitacion("105", TipoHabitacion.SENCILLA, 80.0, "Habitación sencilla estándar piso 1"));
        habitaciones.put("106", new Habitacion("106", TipoHabitacion.SENCILLA, 85.0, "Habitación sencilla con vista a la montaña"));

        // 6 Habitaciones Dobles
        habitaciones.put("201", new Habitacion("201", TipoHabitacion.DOBLE, 130.0, "Habitación doble con cama king size"));
        habitaciones.put("202", new Habitacion("202", TipoHabitacion.DOBLE, 130.0, "Habitación doble con dos camas individuales"));
        habitaciones.put("203", new Habitacion("203", TipoHabitacion.DOBLE, 140.0, "Habitación doble premium con jacuzzi"));
        habitaciones.put("204", new Habitacion("204", TipoHabitacion.DOBLE, 130.0, "Habitación doble con vista al mar"));
        habitaciones.put("205", new Habitacion("205", TipoHabitacion.DOBLE, 135.0, "Habitación doble con terraza privada"));
        habitaciones.put("206", new Habitacion("206", TipoHabitacion.DOBLE, 130.0, "Habitación doble familiar"));

        // 3 Suites
        habitaciones.put("301", new Habitacion("301", TipoHabitacion.SUITE, 250.0, "Suite presidencial con sala de estar"));
        habitaciones.put("302", new Habitacion("302", TipoHabitacion.SUITE, 220.0, "Suite junior con vista panorámica"));
        habitaciones.put("303", new Habitacion("303", TipoHabitacion.SUITE, 280.0, "Suite de lujo con piscina privada"));
    }

    public List<Habitacion> consultarDisponibilidad() {
        return habitaciones.values().stream()
                .filter(Habitacion::isDisponible)
                .collect(Collectors.toList());
    }

    public List<Habitacion> consultarDisponibilidadPorTipo(TipoHabitacion tipo) {
        return habitaciones.values().stream()
                .filter(h -> h.isDisponible() && h.getTipo() == tipo)
                .collect(Collectors.toList());
    }

    public List<Habitacion> todasLasHabitaciones() {
        return new ArrayList<>(habitaciones.values());
    }

    public Optional<Habitacion> buscarHabitacion(String numero) {
        return Optional.ofNullable(habitaciones.get(numero));
    }

    public boolean reservarHabitacion(String numero) {
        Habitacion h = habitaciones.get(numero);
        if (h != null && h.isDisponible()) {
            h.setDisponible(false);
            System.out.println("[HabitacionService] Habitación " + numero + " reservada.");
            return true;
        }
        return false;
    }

    public void liberarHabitacion(String numero) {
        Habitacion h = habitaciones.get(numero);
        if (h != null) {
            h.setDisponible(true);
            System.out.println("[HabitacionService] Habitación " + numero + " liberada.");
        }
    }
}
