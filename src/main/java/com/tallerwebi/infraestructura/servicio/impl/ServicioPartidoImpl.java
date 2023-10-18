package com.tallerwebi.infraestructura.servicio.impl;


import com.tallerwebi.dominio.modelo.Partido;
import com.tallerwebi.dominio.modelo.Equipo;
import com.tallerwebi.dominio.repositorio.RepositorioPartido;
import com.tallerwebi.infraestructura.servicio.ServicioEquipo;
import com.tallerwebi.infraestructura.servicio.ServicioPartido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ServicioPartidoImpl implements ServicioPartido {
    @Autowired
    RepositorioPartido repositorioPartido;
    @Autowired
    ServicioEquipo servicioEquipo;

    @Autowired
    public ServicioPartidoImpl(RepositorioPartido repositorioPartido, ServicioEquipo servicioEquipo){
        this.repositorioPartido = repositorioPartido;
        this.servicioEquipo = servicioEquipo;
    }


    @Override
    public Partido buscarPartido(Long id) {
        return this.repositorioPartido.buscar(id);
    }

    @Override
    public List<Partido> listAll() {
        return repositorioPartido.listAll();
    }

    @Override
    public Long inicializarPartido() {
        return inicializarPartido(0,0,null, null);
        //Se tienen que pasar los equipos seleccionados
    }

    @Override
    public Long inicializarPartido(Integer puntosUsuario, Integer puntosPc, Equipo equipoJugador, Equipo equipoPc) {
        Partido partido=new Partido();
        partido.setPuntosPc(puntosPc);
        partido.setPuntosUsuario(puntosUsuario);
        partido.setEquipoJugador(equipoJugador);
        partido.setEquipoPc(equipoPc);
        return repositorioPartido.crear(partido);
    }

    @Override
    public void guardarEquipoJugador(Long idEquipo, Long idPartido) {
        Partido partido = buscarPartido(idPartido);
        Equipo equipo = servicioEquipo.buscarEquipo(idEquipo);
        partido.setEquipoJugador(equipo);

    }
    @Override
    public void guardarEquipoPc(Long idEquipo, Long idPartido) {
        Partido partido = buscarPartido(idPartido);
        Equipo equipo = servicioEquipo.buscarEquipo(idEquipo);
        partido.setEquipoPc(equipo);

    }
}
