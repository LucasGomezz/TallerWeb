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

    Integer accionPc;

    @Autowired
    public ServicioPartidoImpl(RepositorioPartido repositorioPartido, ServicioEquipo servicioEquipo) {
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
    public Long inicializarPartido(Long idEquipo1, Long idEquipo2) {
        Partido partido = new Partido();
        partido.setPuntosPc(0);
        partido.setPuntosUsuario(0);
        Equipo equipoJugador = servicioEquipo.buscarEquipo(idEquipo1);
        Equipo equipoPc = servicioEquipo.buscarEquipo(idEquipo2);
        partido.setEquipoPc(equipoPc);
        partido.setEquipoJugador(equipoJugador);
        return repositorioPartido.guardar(partido);
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

    public Integer tirarDadoDefendiendo() {
        Random rand = new Random();
        //PONGO EN 10 PARA BAJAR LA DIFICULTAD
        Integer dado = rand.nextInt(10) + 1;
        return dado;
    }

    public Integer tirarDadoAtacando() {
        Random rand = new Random();
        //PONGO EN 10 PARA BAJAR LA DIFICULTAD
        Integer dado = rand.nextInt(20) + 1;
        return dado;
    }

    public Integer elegirAccionPc() {
        Random rand = new Random();
        Integer accionPc = rand.nextInt(3) + 1;
        return accionPc;
    }


    public Boolean compararStats(Integer dadoJugador, String accion, Long idEquipo1, Long idEquipo2, Integer jugador) {
        Integer statJugador = 0;
        Integer statPc = 0;
        Integer dadoPcDefendiendo = tirarDadoDefendiendo();
        Integer dadoPcAtacando = tirarDadoAtacando();
        switch (accion) {
            case "driblear":
                this.accionPc = 0;
                if (jugador == 1) {
                    statJugador = servicioEquipo.buscarEquipo(idEquipo1).getJugador1().getDrible() + dadoJugador;
                    statPc = servicioEquipo.buscarEquipo(idEquipo2).getJugador1().getRobo() + dadoPcDefendiendo;
                } else {
                    statJugador = servicioEquipo.buscarEquipo(idEquipo1).getJugador2().getDrible() + dadoJugador;
                    statPc = servicioEquipo.buscarEquipo(idEquipo2).getJugador2().getRobo() + dadoPcDefendiendo;
                }
                break;
            case "tirar":
                this.accionPc = 0;
                if (jugador == 1) {
                    statJugador = servicioEquipo.buscarEquipo(idEquipo1).getJugador1().getTiro() + dadoJugador;
                    statPc = servicioEquipo.buscarEquipo(idEquipo2).getJugador1().getTapa() + dadoPcDefendiendo;
                } else {
                    statJugador = servicioEquipo.buscarEquipo(idEquipo1).getJugador2().getTiro() + dadoJugador;
                    statPc = servicioEquipo.buscarEquipo(idEquipo2).getJugador2().getTapa() + dadoPcDefendiendo;
                }
                break;
            case "pasar":
                this.accionPc = 0;
                if (jugador == 1) {
                    statJugador = servicioEquipo.buscarEquipo(idEquipo1).getJugador1().getPase() + dadoJugador;
                    statPc = servicioEquipo.buscarEquipo(idEquipo2).getJugador1().getIntercepcion() + dadoPcDefendiendo;
                } else {
                    statJugador = servicioEquipo.buscarEquipo(idEquipo1).getJugador2().getPase() + dadoJugador;
                    statPc = servicioEquipo.buscarEquipo(idEquipo2).getJugador2().getIntercepcion() + dadoPcDefendiendo;
                }
                break;
            case "robar":
                this.accionPc = elegirAccionPc();
                if (jugador == 1) {
                    if (accionPc == 1) {
                        statJugador = servicioEquipo.buscarEquipo(idEquipo1).getJugador1().getRobo() + dadoJugador + 5;
                        statPc = servicioEquipo.buscarEquipo(idEquipo2).getJugador1().getDrible() + dadoPcAtacando;
                    } else if (accionPc == 2) {
                        statJugador = servicioEquipo.buscarEquipo(idEquipo1).getJugador1().getTapa() + dadoJugador;
                        statPc = servicioEquipo.buscarEquipo(idEquipo2).getJugador1().getTiro() + dadoPcAtacando;
                    } else {
                        statJugador = servicioEquipo.buscarEquipo(idEquipo1).getJugador1().getIntercepcion() + dadoJugador;
                        statPc = servicioEquipo.buscarEquipo(idEquipo2).getJugador1().getPase() + dadoPcAtacando;
                    }
                } else {
                    if (accionPc == 1) {
                        statJugador = servicioEquipo.buscarEquipo(idEquipo1).getJugador2().getRobo() + dadoJugador + 5;
                        statPc = servicioEquipo.buscarEquipo(idEquipo2).getJugador2().getDrible() + dadoPcAtacando;
                    } else if (accionPc == 2) {
                        statJugador = servicioEquipo.buscarEquipo(idEquipo1).getJugador2().getTapa() + dadoJugador;
                        statPc = servicioEquipo.buscarEquipo(idEquipo2).getJugador2().getTiro() + dadoPcAtacando;
                    } else {
                        statJugador = servicioEquipo.buscarEquipo(idEquipo1).getJugador2().getIntercepcion() + dadoJugador;
                        statPc = servicioEquipo.buscarEquipo(idEquipo2).getJugador2().getPase() + dadoPcAtacando;
                    }
                }
                break;
            case "tapar":
                this.accionPc = elegirAccionPc();
                if (jugador == 1) {
                    if (accionPc == 2) {
                        statJugador = servicioEquipo.buscarEquipo(idEquipo1).getJugador1().getTapa() + dadoJugador + 5;
                        statPc = servicioEquipo.buscarEquipo(idEquipo2).getJugador1().getTiro() + dadoPcAtacando;
                    } else if (accionPc == 1) {
                        statJugador = servicioEquipo.buscarEquipo(idEquipo1).getJugador1().getRobo() + dadoJugador;
                        statPc = servicioEquipo.buscarEquipo(idEquipo2).getJugador1().getDrible() + dadoPcAtacando;
                    } else {
                        statJugador = servicioEquipo.buscarEquipo(idEquipo1).getJugador1().getIntercepcion() + dadoJugador;
                        statPc = servicioEquipo.buscarEquipo(idEquipo2).getJugador1().getPase() + dadoPcAtacando;
                    }
                } else {
                    if (accionPc == 2) {
                        statJugador = servicioEquipo.buscarEquipo(idEquipo1).getJugador2().getTapa() + dadoJugador + 5;
                        statPc = servicioEquipo.buscarEquipo(idEquipo2).getJugador2().getTiro() + dadoPcAtacando;
                    } else if (accionPc == 1) {
                        statJugador = servicioEquipo.buscarEquipo(idEquipo1).getJugador2().getRobo() + dadoJugador;
                        statPc = servicioEquipo.buscarEquipo(idEquipo2).getJugador2().getDrible() + dadoPcAtacando;
                    } else {
                        statJugador = servicioEquipo.buscarEquipo(idEquipo1).getJugador2().getIntercepcion() + dadoJugador;
                        statPc = servicioEquipo.buscarEquipo(idEquipo2).getJugador2().getPase() + dadoPcAtacando;
                    }
                }
                break;
            case "interceptar":
                this.accionPc = elegirAccionPc();
                if (jugador == 1) {
                    if (accionPc == 3) {
                        statJugador = servicioEquipo.buscarEquipo(idEquipo1).getJugador1().getIntercepcion() + dadoJugador + 5;
                        statPc = servicioEquipo.buscarEquipo(idEquipo2).getJugador1().getPase() + dadoPcAtacando;
                    } else if (accionPc == 2) {
                        statJugador = servicioEquipo.buscarEquipo(idEquipo1).getJugador1().getTapa() + dadoJugador;
                        statPc = servicioEquipo.buscarEquipo(idEquipo2).getJugador1().getTiro() + dadoPcAtacando;
                    } else {
                        statJugador = servicioEquipo.buscarEquipo(idEquipo1).getJugador1().getRobo() + dadoJugador;
                        statPc = servicioEquipo.buscarEquipo(idEquipo2).getJugador1().getDrible() + dadoPcAtacando;
                }}else{
                    if (accionPc == 3) {
                        statJugador = servicioEquipo.buscarEquipo(idEquipo1).getJugador2().getIntercepcion() + dadoJugador + 5;
                        statPc = servicioEquipo.buscarEquipo(idEquipo2).getJugador2().getPase() + dadoPcAtacando;
                    } else if (accionPc == 2) {
                        statJugador = servicioEquipo.buscarEquipo(idEquipo1).getJugador2().getTapa() + dadoJugador;
                        statPc = servicioEquipo.buscarEquipo(idEquipo2).getJugador2().getTiro() + dadoPcAtacando;
                    } else {
                        statJugador = servicioEquipo.buscarEquipo(idEquipo1).getJugador2().getRobo() + dadoJugador;
                        statPc = servicioEquipo.buscarEquipo(idEquipo2).getJugador2().getDrible() + dadoPcAtacando;
                    }
                }
                break;

            default:
                break;
        }
        if (statJugador > statPc) {
            return true;
        }
        return false;
    }


    public void guardarPuntajeFinal(Long id, PartidoDTO partidoDTO) {
        buscarPartido(id).setPuntosUsuario(partidoDTO.getPuntajeJugador());
        buscarPartido(id).setPuntosPc(partidoDTO.getPuntajePc());
    }

    @Override
    public String retornarAccionPc() {
        String accion = "";
        if (this.accionPc == 1) {
            accion = "driblear";
        } else if (this.accionPc == 2) {
            accion = "tirar";
        } else if (this.accionPc == 3) {
            accion = "pasar";
        }
        return accion;
    }

}
