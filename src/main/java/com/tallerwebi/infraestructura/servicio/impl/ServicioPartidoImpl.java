package com.tallerwebi.infraestructura.servicio.impl;


import com.tallerwebi.dominio.modelo.Jugador;
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
    public Long inicializarPartido(Long idEquipo1,Long idEquipo2){
        Partido partido=new Partido();
        partido.setPuntosPc(0);
        partido.setPuntosUsuario(0);
        Equipo equipoJugador = servicioEquipo.buscarEquipo(idEquipo1);
        equipoJugador.getJugador1().setImagen("images/JUGADOR-LOCAL-CON-PELOTA.png");
        equipoJugador.getJugador2().setImagen("images/JUGADOR-LOCAL.png");
        Equipo equipoPc = servicioEquipo.buscarEquipo(idEquipo2);
        equipoPc.getJugador1().setImagen("images/JUGADOR-VISITANTE.png");
        equipoPc.getJugador2().setImagen("images/JUGADOR-VISITANTE.png");
        partido.setEquipoPc(equipoPc);
        partido.setEquipoJugador(equipoJugador);
        return repositorioPartido.guardar(partido);
    }

    @Override
    public PartidoDTO sumar(Integer puntajeYo, Integer puntajeRival) {
        Integer porcentaje = probabilidad();
        PartidoDTO partidoDTO = new PartidoDTO();
        if(porcentaje >= 60){
            partidoDTO.setPuntajePc(puntajeRival += 2);
            partidoDTO.setPuntajeJugador(puntajeYo);
            return partidoDTO;
        }else{
            partidoDTO.setPuntajeJugador(puntajeYo += 2);
            partidoDTO.setPuntajePc(puntajeRival);
            return partidoDTO;
        }
    }

    @Override
    public PartidoDTO guardarResultados(Integer puntajeYo, Integer puntajeRival) {
        PartidoDTO partido = new PartidoDTO();
        partido.setPuntajeJugador(puntajeYo);
        partido.setPuntajePc(puntajeRival);
        return partido;
    }

    @Override
    public void actualizarPartido(Partido partido) {
        repositorioPartido.actualizar(partido);
    }

    private Integer probabilidad(){
        Random random = new Random();
        Integer numeroAleatorio = random.nextInt(10) + 1;
        Integer porcentaje = numeroAleatorio * 10;
        return porcentaje;
    }
    public Boolean compararStats(Integer dado, String accion, Long idEquipo1, Long idEquipo2) {
        Integer stat1 = 0;
        Integer stat2 = 0;
        //HACER LOGICA DADO
        switch (accion) {
            case "driblear":
                stat1 = servicioEquipo.buscarEquipo(idEquipo1).getJugador1().getDrible();
                stat2 = servicioEquipo.buscarEquipo(idEquipo2).getJugador1().getRobo();
                break;
            case "tirar":
                stat1 = servicioEquipo.buscarEquipo(idEquipo1).getJugador1().getTiro();
                stat2 = servicioEquipo.buscarEquipo(idEquipo2).getJugador1().getTapa();
                break;
            case "pasar":
                stat1 = servicioEquipo.buscarEquipo(idEquipo1).getJugador1().getPase();
                stat2 = servicioEquipo.buscarEquipo(idEquipo2).getJugador1().getIntercepcion();
                break;
            case "robar":
                stat1 = servicioEquipo.buscarEquipo(idEquipo1).getJugador1().getRobo();
                stat2 = servicioEquipo.buscarEquipo(idEquipo2).getJugador1().getDrible();
                break;
            case "tapar":
                stat1 = servicioEquipo.buscarEquipo(idEquipo1).getJugador1().getTapa();
                stat2 = servicioEquipo.buscarEquipo(idEquipo2).getJugador1().getTiro();
                break;
            case "interceptar":
                stat1 = servicioEquipo.buscarEquipo(idEquipo1).getJugador1().getIntercepcion();
                stat2 = servicioEquipo.buscarEquipo(idEquipo2).getJugador1().getPase();
                break;

            default:
                break;
        }
        if (stat1 > stat2) {
            return true;
        }
        return false;
    }




}
