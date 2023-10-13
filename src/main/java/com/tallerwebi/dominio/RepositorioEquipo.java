package com.tallerwebi.dominio;

public interface RepositorioEquipo {
    void agregarJugador(Jugador jugador);
    Equipo buscar (String nombre);
    void crear(Equipo equipo);
}
