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

    Integer accionPc = 0;

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
        partido.setGuardable(true);
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

    @Override
    public Integer elegirAccionPc(Integer posicion) {
        if (posicion == 1) {
            this.accionPc = 2;
        } else {
            Random rand = new Random();
            this.accionPc = rand.nextInt(3) + 1;
        }
        return this.accionPc;
    }
    @Override
    public void tirarDado(String tipoDeAccion, PartidoDTO partido) {
        Random rand = new Random();
        if (tipoDeAccion.equals("tirar") || tipoDeAccion.equals("pasar") || tipoDeAccion.equals("driblear")) {
            Integer dadoJugador = rand.nextInt(11) + 10;
            partido.setDadoJugador(dadoJugador);
            Integer dadoPC = rand.nextInt(15) + 1;
            partido.setDadoPC(dadoPC);
        } else {
            Integer dadoJugador = rand.nextInt(15) + 1;
            Integer dadoPC = rand.nextInt(11) + 10;
            partido.setDadoJugador(dadoJugador);
            partido.setDadoPC(dadoPC);
        }
    }

    @Override
    public Boolean compararStats(Integer dadoJugador, Integer dadoPC, String accion, Long idEquipo1, Long idEquipo2, Integer jugador, Integer posicion) {

        Boolean resultado = false;
        switch (accion) {
            case "driblear":
                resultado = driblearStats(dadoJugador, dadoPC, idEquipo1, idEquipo2, jugador);
                break;
            case "tirar":
                resultado = tirarStats(dadoJugador, dadoPC, idEquipo1, idEquipo2, jugador);
                break;
            case "pasar":
                resultado = pasarStats(dadoJugador, dadoPC, idEquipo1, idEquipo2, jugador);
                break;
            case "robar":
                resultado = robarStats(dadoJugador, dadoPC, idEquipo1, idEquipo2, jugador, posicion);
                break;
            case "tapar":
                resultado = taparStats(dadoJugador, dadoPC, idEquipo1, idEquipo2, jugador, posicion);
                break;
            case "interceptar":
                resultado = interceptarStats(dadoJugador, dadoPC, idEquipo1, idEquipo2, jugador, posicion);
                break;
            default:
                break;
        }
        return resultado;
    }
    @Override
    public Boolean driblearStats(Integer dadoJugador, Integer dadoPC, Long idEquipo1, Long idEquipo2, Integer jugador) {
        Integer statJugador = 0;
        Integer statPc = 0;
        this.accionPc = 0;
        if (jugador == 1) {
            statJugador = servicioEquipo.buscarEquipo(idEquipo1).getJugador1().getDrible() + dadoJugador;
            statPc = servicioEquipo.buscarEquipo(idEquipo2).getJugador1().getRobo() + dadoPC;
        } else {
            statJugador = servicioEquipo.buscarEquipo(idEquipo1).getJugador2().getDrible() + dadoJugador;
            statPc = servicioEquipo.buscarEquipo(idEquipo2).getJugador2().getRobo() + dadoPC;
        }
        if (statJugador > statPc) {
            return true;
        }
        return false;
    }
    @Override
    public Boolean tirarStats(Integer dadoJugador, Integer dadoPC, Long idEquipo1, Long idEquipo2, Integer jugador) {
        Integer statJugador = 0;
        Integer statPc = 0;
        this.accionPc = 0;
        if (jugador == 1) {
            statJugador = servicioEquipo.buscarEquipo(idEquipo1).getJugador1().getTiro() + dadoJugador;
            statPc = servicioEquipo.buscarEquipo(idEquipo2).getJugador1().getTapa() + dadoPC;
        } else {
            statJugador = servicioEquipo.buscarEquipo(idEquipo1).getJugador2().getTiro() + dadoJugador;
            statPc = servicioEquipo.buscarEquipo(idEquipo2).getJugador2().getTapa() + dadoPC;
        }
        if (statJugador > statPc) {
            return true;
        }
        return false;
    }
    @Override
    public Boolean pasarStats(Integer dadoJugador, Integer dadoPC, Long idEquipo1, Long idEquipo2, Integer jugador) {
        Integer statJugador = 0;
        Integer statPc = 0;
        this.accionPc = 0;
        if (jugador == 1) {
            statJugador = servicioEquipo.buscarEquipo(idEquipo1).getJugador1().getPase() + dadoJugador;
            statPc = servicioEquipo.buscarEquipo(idEquipo2).getJugador1().getIntercepcion() + dadoPC;
        } else {
            statJugador = servicioEquipo.buscarEquipo(idEquipo1).getJugador2().getPase() + dadoJugador;
            statPc = servicioEquipo.buscarEquipo(idEquipo2).getJugador2().getIntercepcion() + dadoPC;
        }
        if (statJugador > statPc) {
            return true;
        }
        return false;
    }
    @Override
    public Boolean robarStats(Integer dadoJugador, Integer dadoPC, Long idEquipo1, Long idEquipo2, Integer jugador, Integer posicion) {
        Integer statJugador = 0;
        Integer statPc = 0;
        this.accionPc = elegirAccionPc(posicion);
        if(accionPc == 2 && posicion > 2){
            this.accionPc = 3;
        }
        if (jugador == 1) {
            if (accionPc == 1) {
                statJugador = servicioEquipo.buscarEquipo(idEquipo1).getJugador1().getRobo() + dadoJugador + 5;
                statPc = servicioEquipo.buscarEquipo(idEquipo2).getJugador1().getDrible() + dadoPC;
            } else if (accionPc == 2) {
                statJugador = servicioEquipo.buscarEquipo(idEquipo1).getJugador1().getTapa() + dadoJugador;
                statPc = servicioEquipo.buscarEquipo(idEquipo2).getJugador1().getTiro() + dadoPC;
            } else {
                statJugador = servicioEquipo.buscarEquipo(idEquipo1).getJugador1().getIntercepcion() + dadoJugador;
                statPc = servicioEquipo.buscarEquipo(idEquipo2).getJugador1().getPase() + dadoPC;
            }
        } else {
            if (accionPc == 1) {
                statJugador = servicioEquipo.buscarEquipo(idEquipo1).getJugador2().getRobo() + dadoJugador + 5;
                statPc = servicioEquipo.buscarEquipo(idEquipo2).getJugador2().getDrible() + dadoPC;
            } else if (accionPc == 2) {
                statJugador = servicioEquipo.buscarEquipo(idEquipo1).getJugador2().getTapa() + dadoJugador;
                statPc = servicioEquipo.buscarEquipo(idEquipo2).getJugador2().getTiro() + dadoPC;
            } else {
                statJugador = servicioEquipo.buscarEquipo(idEquipo1).getJugador2().getIntercepcion() + dadoJugador;
                statPc = servicioEquipo.buscarEquipo(idEquipo2).getJugador2().getPase() + dadoPC;
            }
        }
        if (statJugador > statPc) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean interceptarStats(Integer dadoJugador, Integer dadoPC, Long idEquipo1, Long idEquipo2, Integer jugador, Integer posicion) {
        Integer statJugador = 0;
        Integer statPc = 0;
        this.accionPc = elegirAccionPc(posicion);
        if(accionPc == 2 && posicion > 2){
            this.accionPc = 3;
        }
        if (jugador == 1) {
            if (accionPc == 3) {
                statJugador = servicioEquipo.buscarEquipo(idEquipo1).getJugador1().getIntercepcion() + dadoJugador + 5;
                statPc = servicioEquipo.buscarEquipo(idEquipo2).getJugador1().getPase() + dadoPC;
            } else if (accionPc == 2) {
                statJugador = servicioEquipo.buscarEquipo(idEquipo1).getJugador1().getTapa() + dadoJugador;
                statPc = servicioEquipo.buscarEquipo(idEquipo2).getJugador1().getTiro() + dadoPC;
            } else {
                statJugador = servicioEquipo.buscarEquipo(idEquipo1).getJugador1().getRobo() + dadoJugador;
                statPc = servicioEquipo.buscarEquipo(idEquipo2).getJugador1().getDrible() + dadoPC;
            }
        } else {
            if (accionPc == 3) {
                statJugador = servicioEquipo.buscarEquipo(idEquipo1).getJugador2().getIntercepcion() + dadoJugador + 5;
                statPc = servicioEquipo.buscarEquipo(idEquipo2).getJugador2().getPase() + dadoPC;
            } else if (accionPc == 2) {
                statJugador = servicioEquipo.buscarEquipo(idEquipo1).getJugador2().getTapa() + dadoJugador;
                statPc = servicioEquipo.buscarEquipo(idEquipo2).getJugador2().getTiro() + dadoPC;
            } else {
                statJugador = servicioEquipo.buscarEquipo(idEquipo1).getJugador2().getRobo() + dadoJugador;
                statPc = servicioEquipo.buscarEquipo(idEquipo2).getJugador2().getDrible() + dadoPC;
            }
        }
        if (statJugador > statPc) {
            return true;
        }
        return false;
    }
    @Override
    public Boolean taparStats(Integer dadoJugador, Integer dadoPC, Long idEquipo1, Long idEquipo2, Integer jugador, Integer posicion) {
        Integer statJugador = 0;
        Integer statPc = 0;
        this.accionPc = elegirAccionPc(posicion);
        if(accionPc == 2 && posicion > 2){
            this.accionPc = 3;
        }
        if (jugador == 1) {
            if (accionPc == 2) {
                statJugador = servicioEquipo.buscarEquipo(idEquipo1).getJugador1().getTapa() + dadoJugador + 5;
                statPc = servicioEquipo.buscarEquipo(idEquipo2).getJugador1().getTiro() + dadoPC;
            } else if (accionPc == 1) {
                statJugador = servicioEquipo.buscarEquipo(idEquipo1).getJugador1().getRobo() + dadoJugador;
                statPc = servicioEquipo.buscarEquipo(idEquipo2).getJugador1().getDrible() + dadoPC;
            } else {
                statJugador = servicioEquipo.buscarEquipo(idEquipo1).getJugador1().getIntercepcion() + dadoJugador;
                statPc = servicioEquipo.buscarEquipo(idEquipo2).getJugador1().getPase() + dadoPC;
            }
        } else {
            if (accionPc == 2) {
                statJugador = servicioEquipo.buscarEquipo(idEquipo1).getJugador2().getTapa() + dadoJugador + 5;
                statPc = servicioEquipo.buscarEquipo(idEquipo2).getJugador2().getTiro() + dadoPC;
            } else if (accionPc == 1) {
                statJugador = servicioEquipo.buscarEquipo(idEquipo1).getJugador2().getRobo() + dadoJugador;
                statPc = servicioEquipo.buscarEquipo(idEquipo2).getJugador2().getDrible() + dadoPC;
            } else {
                statJugador = servicioEquipo.buscarEquipo(idEquipo1).getJugador2().getIntercepcion() + dadoJugador;
                statPc = servicioEquipo.buscarEquipo(idEquipo2).getJugador2().getPase() + dadoPC;
            }
        }
        if (statJugador > statPc) {
            return true;
        }
        return false;
    }
    @Override
    public void actualizar(Long id) {
        repositorioPartido.actualizar(buscarPartido(id));
    }
    @Override
    public void guardarPuntajeFinal(Long id, PartidoDTO partidoDTO) {
        buscarPartido(id).setPuntosUsuario(partidoDTO.getPuntajeJugador());
        buscarPartido(id).setPuntosPc(partidoDTO.getPuntajePc());
        buscarPartido(id).setGuardable(false);
        actualizar(id);
    }

    @Override
    public void guardarPartido(Long id, PartidoDTO partidoDTO) {
        buscarPartido(id).setPuntosUsuario(partidoDTO.getPuntajeJugador());
        buscarPartido(id).setPuntosPc(partidoDTO.getPuntajePc());
        buscarPartido(id).setEquipoJugador(partidoDTO.getEquipoJugador());
        buscarPartido(id).setEquipoPc(partidoDTO.getEquipoPC());
        buscarPartido(id).setPosicion(partidoDTO.getPosicion());
        buscarPartido(id).setTengoLaPelota(partidoDTO.getTengoLaPelota());
        buscarPartido(id).setTienePelotaJugador(partidoDTO.getTienePelotaJugador());
        actualizar(id);
    }

    @Override
    public Long buscarPartidoGuardado() {
        List<Partido> partidos = repositorioPartido.listAll();
        for (Partido partido : partidos) {
            if (partido.getGuardable()) {
                return partido.getId();
            }
        }
        return null;
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
