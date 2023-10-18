package com.tallerwebi.infraestructura.servicio;

import com.tallerwebi.dominio.modelo.Equipo;
import com.tallerwebi.dominio.modelo.Partido;

import java.util.List;

public interface ServicioPartido {
    Partido buscarPartido(Long id);
    List<Partido> listAll();
    Long inicializarPartido();
    Long inicializarPartido(Integer puntosUsuario, Integer puntosPc, Equipo equipoJugador, Equipo equipoPc);
    void guardarEquipoJugador(Long idEquipo,Long idPartido);
    void guardarEquipoPc(Long idEquipo,Long idPartido);
}
