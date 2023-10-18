package com.tallerwebi.infraestructura.servicio;

import com.tallerwebi.dominio.modelo.Equipo;

import java.util.List;

public interface ServicioEquipo {
    Equipo buscarEquipo(Long idEquipo);
    List<Equipo> listAll();
    void inicializarEquipos();
    void inicializarEquipos(String nombreEquipo,String nombreJugador1, String nombreJugador2, String logo, String color);
}
