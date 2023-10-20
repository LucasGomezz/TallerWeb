package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.modelo.Equipo;
import com.tallerwebi.dominio.modelo.Partido;
import com.tallerwebi.infraestructura.servicio.ServicioEquipo;
import com.tallerwebi.infraestructura.servicio.ServicioJugador;
import com.tallerwebi.infraestructura.servicio.ServicioPartido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ControladorPartida {

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


    @RequestMapping("/partido-items")//Hacer verificacion de que esten los dos equipos
    public ModelAndView irAItems() {
        ModelMap modelo = new ModelMap();
        return new ModelAndView("partido-items", modelo);
    }

    @RequestMapping(value="/iniciarPartida",method = {RequestMethod.GET})
    public ModelAndView iniciarPartida(@RequestParam(required = true) Long idEquipo1, @RequestParam(required = true) Long idEquipo2){
        Long idPartido = servicioPartido.inicializarPartido(idEquipo1, idEquipo2);
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
        PartidoDTO partidoNuevo = new PartidoDTO();

        modelo.put("partido", servicioPartido.buscarPartido(idPartido));
        modelo.addAttribute("miPuntaje", partidoNuevo.getPuntajeYo().toString());
        modelo.addAttribute("puntajeRival", partidoNuevo.getPuntajeRival().toString());
        return new ModelAndView("partido", modelo);
    }



    @RequestMapping(value = "/obtenerPuntaje", method = RequestMethod.GET)
    @ResponseBody
    public PartidoDTO obtenerPuntaje() {
        PartidoDTO partidoNuevo = new PartidoDTO();
        return partidoNuevo;
    }

    List<PartidoDTO> partido = new ArrayList<>();

    @RequestMapping(value = "/sumar", method = RequestMethod.POST)
    @ResponseBody
    public PartidoDTO sumarPuntaje(Integer puntajeYo, Integer puntajeRival) {
        PartidoDTO puntajes = servicioPartido.sumar(puntajeYo, puntajeRival);
        partido.add(puntajes);
        return puntajes;
    }

    @RequestMapping("/partido-aro")
    public ModelAndView irAlAro(@ModelAttribute PartidoDTO partidoDTO) {
        //servicioPartido.tirarAro(partidoDTO);//actualizar el partidoDTO despues de cada accion
        ModelMap modelo = new ModelMap();
        return new ModelAndView("partido-aro", modelo);
    }

    @RequestMapping("/partido-resultado")
    public ModelAndView irAlResultado() {
        ModelMap modelo = new ModelMap();

        return new ModelAndView("partido-resultado", modelo);
    }

}

