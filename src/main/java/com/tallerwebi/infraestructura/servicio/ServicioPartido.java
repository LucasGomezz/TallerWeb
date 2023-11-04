package com.tallerwebi.infraestructura.servicio;

import com.tallerwebi.dominio.modelo.Partido;
import com.tallerwebi.presentacion.PartidoDTO;

import java.util.List;

public interface ServicioPartido {
    Partido buscarPartido(Long id);
    List<Partido> listAll();

    Long inicializarPartido(Long idEquipo1,Long idEquipo2);

    PartidoDTO guardarResultados(Integer puntajeYo, Integer puntajeRival);

    void actualizarPartido(Partido partido);

    Boolean compararStats(Integer dadoJugador, Integer dadoPc, String accion, Long idEquipo1, Long idEquipo2, Integer jugador);

    void guardarPuntajeFinal(Long id, PartidoDTO partidoDTO);

    String retornarAccionPc();
}
