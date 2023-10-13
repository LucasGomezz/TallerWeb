package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.Jugador;
import com.tallerwebi.dominio.ServicioJugador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorPartida {
//Se pueden usar todos los servicios necesarios
    private ServicioJugador servicioJugador;
//Los controladores se comunica con los servicios y saben si estatodo bien gracias a las exceptions
    @Autowired
    public ControladorPartida(ServicioJugador servicioJugador){this.servicioJugador = servicioJugador;}

        @RequestMapping("/nueva-partida")
        public ModelAndView irANuevaPartida() {

            return new ModelAndView("partido");
        }
        @RequestMapping("/partido")
        public ModelAndView irAPartido() {
            return new ModelAndView("partido");
        }
    @RequestMapping("/partido-aro")
    public ModelAndView irAlAro() {

        return new ModelAndView("partido-aro");
    }
    @RequestMapping("/partido-resultado")
    public ModelAndView irAlResultado() {

        return new ModelAndView("partido-resultado");
    }

    }

