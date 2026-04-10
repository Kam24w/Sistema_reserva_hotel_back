package com.hotel.reservas.dto;

import com.hotel.reservas.model.TipoServicio;

public class ServicioRequestDTO {
    private TipoServicio tipoServicio;

    public TipoServicio getTipoServicio() { return tipoServicio; }
    public void setTipoServicio(TipoServicio tipoServicio) { this.tipoServicio = tipoServicio; }
}
