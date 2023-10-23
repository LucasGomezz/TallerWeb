package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.modelo.Equipo;

public class PartidoDTO {
    private Integer puntajeJugador = 0;
    private Integer puntajePc = 0;
    private Equipo equipoJugador;
    private Equipo equipoPC;

    public Integer getPuntajeJugador() {
        return puntajeJugador;
    }

    public void setPuntajeJugador(Integer puntajeJugador) {
        this.puntajeJugador = puntajeJugador;
    }

    public Integer getPuntajePc() {
        return puntajePc;
    }

    public void setPuntajePc(Integer puntajePc) {
        this.puntajePc = puntajePc;
    }

    public Equipo getEquipoJugador() {
        return equipoJugador;
    }

    public void setEquipoJugador(Equipo equipoJugador) {
        this.equipoJugador = equipoJugador;
    }

    public Equipo getEquipoPC() {
        return equipoPC;
    }

    public void setEquipoPC(Equipo equipoPC) {
        this.equipoPC = equipoPC;
    }
}
