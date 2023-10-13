package com.tallerwebi.dominio;

import org.springframework.stereotype.Service;


public interface ServicioJugador {
    void registrar(String nombre, Integer drible, Integer tiro,Integer pase , Integer robo, Integer tapa,
                   Integer intercepcion, String imagen);
    Jugador buscarJugador(String nombre);
}
