package com.tallerwebi.dominio;

public interface RepositorioJugador {
    Jugador buscar(Long id);
    void guardar(Jugador jugador);
}

