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
        partidoNuevo.setEquipoJugador(servicioPartido.buscarPartido(idPartido).getEquipoJugador());
        partidoNuevo.setEquipoPC(servicioPartido.buscarPartido(idPartido).getEquipoPc());
        partidoNuevo.setIdPartido(idPartido);
        modelo.put("partido", partidoNuevo);
        modelo.addAttribute("miPuntaje", partidoNuevo.getPuntajeJugador().toString());
        modelo.addAttribute("puntajeRival", partidoNuevo.getPuntajePc().toString());
        return new ModelAndView("partido", modelo);
    }

//    @RequestMapping(value = "/acciones", method = RequestMethod.POST)
//    public ModelAndView realizarAcciones(@RequestParam(required = true) String tipoAccion, Long idEquipo1, Long idEquipo2, Long idPartido, @ModelAttribute("partido") PartidoDTO partidoEntero)  {
//        Boolean resultado = servicioPartido.compararStats(tipoAccion, idEquipo1, idEquipo2);
//        partidoEntero.getIdPartido();
//        return new ModelAndView("redirect:posicion?resultado=" + resultado + "&idPartido=" + idPartido);
//    }

    @RequestMapping(value = "/acciones", method = RequestMethod.POST)
    public ModelAndView realizarAcciones(@RequestParam(required = true) String tipoAccion) {
        Long idPartido = partidoNuevo.getIdPartido();
        if (!tipoAccion.equals("tirar")) {
            Boolean resultado = servicioPartido.compararStats(tipoAccion, partidoNuevo.getEquipoJugador().getIdEquipo(), partidoNuevo.getEquipoPC().getIdEquipo());
            return new ModelAndView("redirect:posicion?resultado=" + true + "&idPartido=" + idPartido);
        } else {
            Boolean resultado = servicioPartido.compararStats(tipoAccion, partidoNuevo.getEquipoJugador().getIdEquipo(), partidoNuevo.getEquipoPC().getIdEquipo());
            if(resultado){
            return new ModelAndView("redirect:partido-aro?idPartido=" + idPartido);}
            else{
                return new ModelAndView("redirect:partido?idPartido=" + idPartido);
            }
        }
    }
    @RequestMapping(value = "/partido-aro", method = RequestMethod.GET)
    public ModelAndView irAlAro(@RequestParam(required = true)Long idPartido) {
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

    @RequestMapping(value = "/obtenerPuntaje", method = RequestMethod.GET)
    @ResponseBody
    public PartidoDTO obtenerPuntaje() {
        PartidoDTO partidoNuevo = new PartidoDTO();
        return partidoNuevo;
    }


    @RequestMapping("/partido-resultado")
    public ModelAndView irAlResultado() {
        ModelMap modelo = new ModelMap();

        return new ModelAndView("partido-resultado", modelo);
    }

    @RequestMapping(value = "/tira", method = RequestMethod.POST)
    public ModelAndView procesarValorBarra(@RequestParam Integer resultadoBarra) {
        if(resultadoBarra >= 85){
            if(partidoNuevo.getPosicion()== 4){
                partidoNuevo.setPuntajeJugador(partidoNuevo.getPuntajeJugador() + 2);
            }else {
                partidoNuevo.setPuntajeJugador(partidoNuevo.getPuntajeJugador() + 3);
            }
        }
        Long idPartido = partidoNuevo.getIdPartido();
        return new ModelAndView("redirect:partido?idPartido=" + idPartido);
    }

}

