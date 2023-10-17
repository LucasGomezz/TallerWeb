package com.tallerwebi.dominio;

import java.util.List;

public interface ServicioEquipo {
    Equipo buscarEquipo(Long idEquipo);
    List<Equipo> listAll();
    void inicializarEquipos();
    void inicializarEquipos(String nombreEquipo,String nombreJugador1, String nombreJugador2, String logo, String color);
}
