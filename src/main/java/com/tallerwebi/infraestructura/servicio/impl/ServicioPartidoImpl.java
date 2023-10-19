package com.tallerwebi.infraestructura.servicio.impl;


import com.tallerwebi.dominio.modelo.Partido;
import com.tallerwebi.dominio.modelo.Equipo;
import com.tallerwebi.dominio.repositorio.RepositorioPartido;
import com.tallerwebi.infraestructura.servicio.ServicioEquipo;
import com.tallerwebi.infraestructura.servicio.ServicioPartido;
import com.tallerwebi.presentacion.PartidoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Random;

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

    @Override
    public PartidoDTO sumar(Integer puntajeYo, Integer puntajeRival) {
        Integer porcentaje = probabilidad();
        PartidoDTO partidoDTO = new PartidoDTO();
        if(porcentaje >= 60){
            partidoDTO.setPuntajeRival(puntajeRival += 2);
            partidoDTO.setPuntajeYo(puntajeYo);
            return partidoDTO;
        }else{
            partidoDTO.setPuntajeYo(puntajeYo += 2);
            partidoDTO.setPuntajeRival(puntajeRival);
            return partidoDTO;
        }
    }

    @Override
    public PartidoDTO guardarResultados(Integer puntajeYo, Integer puntajeRival) {
        PartidoDTO partido = new PartidoDTO();
        partido.setPuntajeYo(puntajeYo);
        partido.setPuntajeRival(puntajeRival);
        return partido;
    }

    private Integer probabilidad(){
        Random random = new Random();
        Integer numeroAleatorio = random.nextInt(10) + 1;
        Integer porcentaje = numeroAleatorio * 10;
        return porcentaje;
    }
}
