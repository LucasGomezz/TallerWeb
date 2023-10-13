package com.tallerwebi.dominio;

import org.springframework.stereotype.Repository;

@Repository("repositorioJugador")
public interface RepositorioJugador {
    Jugador buscar(String nombre);
    void guardar(Jugador jugador);
}

