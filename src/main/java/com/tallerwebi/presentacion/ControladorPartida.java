package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.Equipo;
import com.tallerwebi.dominio.Jugador;
import com.tallerwebi.dominio.ServicioEquipo;
import com.tallerwebi.dominio.ServicioJugador;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorPartida {

//Se pueden usar todos los servicios necesarios
    private ServicioJugador servicioJugador;
    private ServicioEquipo servicioEquipo;

//Los controladores se comunica con los servicios y saben si estatodo bien gracias a las exceptions
    @Autowired
    public ControladorPartida(ServicioJugador servicioJugador, ServicioEquipo servicioEquipo){
        this.servicioJugador = servicioJugador;
        this.servicioEquipo = servicioEquipo;
    }

    @RequestMapping(value = "/elegir-equipo", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView elegirEquipo(@RequestParam(required = false) Long idEquipo) {
        ModelMap modelo = new ModelMap();
        modelo.put("titulo", "Equipos: ");

        if (idEquipo != null) {
            modelo.put("equipos", servicioEquipo.listAll());
            modelo.put("equipo1", servicioEquipo.buscarEquipo(idEquipo));
            return new ModelAndView("elegir-equipo", modelo);
        } else {
            modelo.put("equipos", servicioEquipo.listAll());
            return new ModelAndView("elegir-equipo", modelo);
        }
    }


   /* @RequestMapping(path = "/partido", method = RequestMethod.GET)
    public ModelAndView irAPartido(@RequestParam(required=true) Long idEquipo1, Long idEquipo2) {
        //www.web.unlam.com/partido?idEquipo1=1
        //www.web.unlam.com/partido/1/2
        //@ModelAttribute= objeto entero
        //@PathVariable= solo el valor
        //@RequestParam= tambien
        ModelMap modelo = new ModelMap();
        Equipo equipo1=servicioEquipo.buscarEquipo(idEquipo1);
        equipo1.getJugador1().setImagen("images/JUGADOR-LOCAL-CON-PELOTA.png");
        equipo1.getJugador2().setImagen("images/JUGADOR-LOCAL.png");
        modelo.put("equipo1",equipo1);
        Equipo equipo2=servicioEquipo.buscarEquipo(idEquipo2);
        equipo2.getJugador1().setImagen("images/JUGADOR-VISITANTE.png");
        equipo2.getJugador2().setImagen("images/JUGADOR-VISITANTE.png");
        modelo.put("equipo2", equipo2);

        return new ModelAndView("partido", modelo);
    }*/

    @RequestMapping(path = "/partido", method = RequestMethod.GET)
    public ModelAndView irAPartido(){
        return new ModelAndView("partido");
    }
    
    @RequestMapping("/partido-items")
    public ModelAndView irAItems() {
        ModelMap modelo = new ModelMap();
        return new ModelAndView("partido-items",modelo);
    }

    @RequestMapping("/partido-aro")
    public ModelAndView irAlAro() {
        ModelMap modelo = new ModelMap();
        return new ModelAndView("partido-aro",modelo);
    }
    @RequestMapping("/partido-resultado")
    public ModelAndView irAlResultado() {
        ModelMap modelo = new ModelMap();

        return new ModelAndView("partido-resultado",modelo);
    }

}

