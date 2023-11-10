package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.modelo.Equipo;
import com.tallerwebi.dominio.modelo.Partido;
import com.tallerwebi.infraestructura.servicio.ServicioEquipo;
import com.tallerwebi.infraestructura.servicio.ServicioJugador;
import com.tallerwebi.infraestructura.servicio.ServicioPartido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Part;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ControladorPartida {

    PartidoDTO partidoNuevo = new PartidoDTO();

    //Se pueden usar todos los servicios necesarios
    private ServicioJugador servicioJugador;
    private ServicioEquipo servicioEquipo;
    private ServicioPartido servicioPartido;

    //Los controladores se comunica con los servicios y saben si estatodo bien gracias a las exceptions
    @Autowired
    public ControladorPartida(ServicioJugador servicioJugador, ServicioEquipo servicioEquipo, ServicioPartido servicioPartido) {
        this.servicioJugador = servicioJugador;
        this.servicioEquipo = servicioEquipo;
        this.servicioPartido = servicioPartido;
    }

    @RequestMapping(value = "/check-guardado")
    public ModelAndView checkGuardado() {
        ModelMap modelo = new ModelMap();
        Long hayGuardado = servicioPartido.buscarPartidoGuardado();
        modelo.put("hayGuardado", hayGuardado);
        if (hayGuardado != null) {
            return new ModelAndView("menuPrincipal", modelo);
        } else {
            return new ModelAndView("redirect:elegir-equipo");
        }
    }

    @RequestMapping(value = "/recuperar-partida", method = {RequestMethod.POST})
    public ModelAndView recuperarPartida(@RequestParam(required = true) Boolean respuesta) {
        Long idPartido = servicioPartido.buscarPartidoGuardado();
        if (respuesta) {
            return new ModelAndView("redirect:partido?idPartido=" + idPartido);
        } else {
            Partido partido = servicioPartido.buscarPartido(idPartido);
            partido.setGuardable(false);
            servicioPartido.actualizar(idPartido);
            return new ModelAndView("redirect:elegir-equipo");
        }
    }

    @RequestMapping(value = "/elegir-equipo", method = {RequestMethod.GET})
    public ModelAndView elegirEquipo(@RequestParam(required = false) Long idEquipo1, @RequestParam(required = false) Long idEquipo2) {
        ModelMap modelo = new ModelMap();
        modelo.put("equipos", servicioEquipo.listAll());
        modelo.put("idEquipo1", idEquipo1);
        modelo.put("idEquipo2", idEquipo2);
        modelo.put("equipo1", servicioEquipo.buscarEquipo(idEquipo1));
        modelo.put("equipo2", servicioEquipo.buscarEquipo(idEquipo2));


        return new ModelAndView("elegir-equipo", modelo);
    }

    @RequestMapping(value = "/seleccionarEquipo", method = {RequestMethod.POST})
    public ModelAndView seleccionarEquipo(@RequestParam(required = false) Long idEquipo1, @RequestParam(required = false) Long idEquipo2) {
        String redirect = "redirect:/elegir-equipo";
        if (idEquipo1 != null) {
            redirect += "?idEquipo1=" + idEquipo1;
        }
        if (idEquipo2 != null) {
            redirect += (idEquipo1 != null ? "&" : "?") + "idEquipo2=" + idEquipo2;
        }
        return new ModelAndView(redirect);
    }


    @RequestMapping("/partido-items")
    public ModelAndView irAItems() {
        ModelMap modelo = new ModelMap();
        return new ModelAndView("partido-items", modelo);
    }

    @RequestMapping(value = "/iniciarPartida", method = {RequestMethod.GET})
    public ModelAndView iniciarPartida(@RequestParam(required = true) Long idEquipo1, @RequestParam(required = true) Long idEquipo2) {
        Long idPartido = servicioPartido.inicializarPartido(idEquipo1, idEquipo2);
        partidoNuevo = new PartidoDTO();
        partidoNuevo.setEquipoJugador(servicioPartido.buscarPartido(idPartido).getEquipoJugador());
        partidoNuevo.setEquipoPC(servicioPartido.buscarPartido(idPartido).getEquipoPc());
        partidoNuevo.setIdPartido(idPartido);
        partidoNuevo.getEquipoPC().getJugador1().setImagen("images/JUGADOR-VISITANTE.png");
        partidoNuevo.getEquipoPC().getJugador2().setImagen("images/JUGADOR-VISITANTE.png");
        partidoNuevo.getEquipoJugador().getJugador1().setImagen("images/JUGADOR-LOCAL-CON-PELOTA.png");
        partidoNuevo.getEquipoJugador().getJugador2().setImagen("images/JUGADOR-LOCAL.png");
        return new ModelAndView("redirect:partido?idPartido=" + idPartido);
    }


    @RequestMapping(path = "/partido", method = RequestMethod.GET)
    public ModelAndView irAPartido(@RequestParam(required = true) Long idPartido) {
        //www.web.unlam.com/partido?idEquipo1=1
        //www.web.unlam.com/partido/1/2
        //@ModelAttribute= objeto entero
        //@PathVariable= solo el valor
        //@RequestParam= tambien
        ModelMap modelo = new ModelMap();
        modelo.put("partido", partidoNuevo);
        partidoNuevo.setImagenes();
        if (partidoNuevo.getPuntajeJugador() < 21 && partidoNuevo.getPuntajePc() < 21) {
            return new ModelAndView("partido", modelo);
        } else {
            return new ModelAndView("redirect:partido-resultado?idPartido=" + idPartido);
        }
    }


    @RequestMapping(value = "/acciones", method = RequestMethod.POST)
    public ModelAndView realizarAcciones(@RequestParam(required = true) String tipoAccion) {
        Long idPartido = partidoNuevo.getIdPartido();
        Integer jugador = partidoNuevo.getTienePelotaJugador();
        servicioPartido.tirarDado(tipoAccion, partidoNuevo);
        Integer dadoJugador = partidoNuevo.getDadoJugador();
        Integer dadoPC = partidoNuevo.getDadoPC();
        Boolean resultado = servicioPartido.compararStats(dadoJugador, dadoPC, tipoAccion, partidoNuevo.getEquipoJugador().getIdEquipo(), partidoNuevo.getEquipoPC().getIdEquipo(), jugador);

        if (tipoAccion.equals("tirar")) {
            if (resultado) {
                partidoNuevo.setTengoLaPelota(true);
                return new ModelAndView("redirect:partido-aro?idPartido=" + idPartido);
            } else {
                partidoNuevo.setTengoLaPelota(false);
                return new ModelAndView("redirect:partido?idPartido=" + idPartido);
            }
        }
        if (resultado) {
            partidoNuevo.setTengoLaPelota(true);

            if (tipoAccion.equals("pasar")) {
                if (partidoNuevo.getTienePelotaJugador() == 1) {
                    partidoNuevo.setTienePelotaJugador(2);
                } else {
                    partidoNuevo.setTienePelotaJugador(1);
                }
            }
            return new ModelAndView("redirect:posicion?resultado=" + true + "&idPartido=" + idPartido);
        } else {
            partidoNuevo.setTengoLaPelota(false);
            verificaLaAccionPc(servicioPartido.retornarAccionPc());
            return new ModelAndView("redirect:posicion?resultado=" + false + "&idPartido=" + idPartido);
        }
    }

    private void verificaLaAccionPc(String accionPc) {
        if (accionPc.equals("tirar")) {
            int puntos = (partidoNuevo.getPosicion() == 1) ? 2 : 3;
            partidoNuevo.setPuntajePc(partidoNuevo.getPuntajePc() + puntos);
        }

        if (accionPc.equals("pasar")) {
            if (partidoNuevo.getTienePelotaJugador() == 1) {
                partidoNuevo.setTienePelotaJugador(2);
            } else {
                partidoNuevo.setTienePelotaJugador(1);
            }
        }
    }


    @RequestMapping(value = "/partido-aro", method = RequestMethod.GET)
    public ModelAndView irAlAro(@RequestParam(required = true) Long idPartido) {
        ModelMap modelo = new ModelMap();

        return new ModelAndView("partido-aro", modelo);
    }

    @RequestMapping(value = "/posicion", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView calcularPosicion(@RequestParam(required = true) Boolean resultado, Long idPartido) {
        Integer posicion = partidoNuevo.getPosicion();
        if (resultado) {
            if (posicion < 4) {
                posicion++;
            }
        } else {
            if (posicion > 1) {
                posicion--;
            }
        }
        partidoNuevo.setPosicion(posicion);

        return new ModelAndView("redirect:partido?idPartido=" + idPartido);
    }


    @RequestMapping("/partido-resultado")
    public ModelAndView irAlResultado(@RequestParam(required = true) Long idPartido) {
        ModelMap modelo = new ModelMap();
        String mensaje;
        if (partidoNuevo.getPuntajeJugador() > partidoNuevo.getPuntajePc()) {
            mensaje = "GANASTE";
        } else {
            mensaje = "PERDISTE";
        }
        modelo.put("mensaje", mensaje);
        servicioPartido.guardarPuntajeFinal(idPartido, partidoNuevo);
        return new ModelAndView("partido-resultado", modelo);
    }

    @RequestMapping(value = "/tira", method = RequestMethod.POST)
    public ModelAndView procesarValorBarra(@RequestParam Integer resultadoBarra) {
        if (resultadoBarra >= 85) {
            if (partidoNuevo.getPosicion() == 4) {
                partidoNuevo.setPuntajeJugador(partidoNuevo.getPuntajeJugador() + 2);
            } else {
                partidoNuevo.setPuntajeJugador(partidoNuevo.getPuntajeJugador() + 3);
            }
        }
        partidoNuevo.setTengoLaPelota(false);
        partidoNuevo.setPosicion(4);
        Long idPartido = partidoNuevo.getIdPartido();
        return new ModelAndView("redirect:partido?idPartido=" + idPartido);
    }

    @RequestMapping(path = "/guardarPartido")
    public ModelAndView guardarPartido() {
        Long idPartido = partidoNuevo.getIdPartido();
        servicioPartido.guardarPartido(idPartido, partidoNuevo);
        ModelMap modelo = new ModelMap();
        modelo.put("idUltimoPartido", idPartido);
        return new ModelAndView("menuPrincipal", modelo);
    }

    @RequestMapping(value = "/historial", method = RequestMethod.GET)
    public ModelAndView mostrarHistorial() {
        ModelMap modelo = new ModelMap();
        List<Partido> partidos = servicioPartido.listAll();
        modelo.put("partidos", partidos);
        return new ModelAndView("historial", modelo);
    }
}

