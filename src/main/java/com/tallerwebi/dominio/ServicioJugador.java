package com.tallerwebi.dominio;

import org.springframework.stereotype.Service;


public interface ServicioJugador {
    Jugador buscarJugador(String nombre);
    void inicializarJugador(String nombre, Integer drible, Integer tiro, Integer pase, Integer robo, Integer tapa, Integer intercepcion, String imagen);
    void inicializarJugador();
}
