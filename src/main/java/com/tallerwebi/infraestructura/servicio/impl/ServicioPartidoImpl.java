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
    public Integer tirarDado(){
        Random rand = new Random();
        //PONGO EN 10 PARA BAJAR LA DIFICULTAD
        Integer dado= rand.nextInt(10) + 1;
        return dado;
    }

    public Boolean compararStats(Integer dadoJugador, String accion, Long idEquipo1, Long idEquipo2) {
        Integer statJugador = 0;
        Integer statPc = 0;
        Integer dadoPc=tirarDado();
        switch (accion) {
            case "driblear":
                statJugador = servicioEquipo.buscarEquipo(idEquipo1).getJugador1().getDrible()+dadoJugador;
                statPc = servicioEquipo.buscarEquipo(idEquipo2).getJugador1().getRobo()+dadoPc;
                break;
            case "tirar":
                statJugador = servicioEquipo.buscarEquipo(idEquipo1).getJugador1().getTiro()+dadoJugador;
                statPc = servicioEquipo.buscarEquipo(idEquipo2).getJugador1().getTapa()+dadoPc;
                break;
            case "pasar":
                statJugador = servicioEquipo.buscarEquipo(idEquipo1).getJugador1().getPase()+dadoJugador;
                statPc = servicioEquipo.buscarEquipo(idEquipo2).getJugador1().getIntercepcion()+dadoPc;
                break;
            case "robar":
                statJugador = servicioEquipo.buscarEquipo(idEquipo1).getJugador1().getRobo()+dadoJugador;
                statPc = servicioEquipo.buscarEquipo(idEquipo2).getJugador1().getDrible()+dadoPc;
                break;
            case "tapar":
                statJugador = servicioEquipo.buscarEquipo(idEquipo1).getJugador1().getTapa()+dadoJugador;
                statPc = servicioEquipo.buscarEquipo(idEquipo2).getJugador1().getTiro()+dadoPc;
                break;
            case "interceptar":
                statJugador = servicioEquipo.buscarEquipo(idEquipo1).getJugador1().getIntercepcion()+dadoJugador;
                statPc = servicioEquipo.buscarEquipo(idEquipo2).getJugador1().getPase()+dadoPc;
                break;

            default:
                break;
        }
        if (statJugador > statPc) {
            return true;
        }
        return false;
    }

    public void guardarPartidoFinal(PartidoDTO partidoDTO){
        Long id =inicializarPartido(partidoDTO.getEquipoJugador().getIdEquipo(),partidoDTO.getEquipoPC().getIdEquipo());
        buscarPartido(id).setPuntosUsuario(partidoDTO.getPuntajeJugador());
        buscarPartido(id).setPuntosPc(partidoDTO.getPuntajePc());
    }

}
