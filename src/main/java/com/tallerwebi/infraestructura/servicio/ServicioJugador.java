package com.tallerwebi.infraestructura.servicio;

import com.tallerwebi.dominio.modelo.Jugador;


public interface ServicioJugador {
    Jugador buscarJugador(String nombre);
    void inicializarJugador(String nombre, Integer drible, Integer tiro, Integer pase, Integer robo, Integer tapa, Integer intercepcion, String imagen);
    void inicializarJugador();
}
