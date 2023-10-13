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
//Los controladores se comunica con los servicios y saben si esta todo bien gracias a las exceptions
    @Autowired
    public ControladorPartida(ServicioJugador servicioJugador){this.servicioJugador = servicioJugador;}

        @RequestMapping("/nueva-partida")
        public ModelAndView irANuevaPartida() {

            return new ModelAndView("partido");
        }
        @RequestMapping("/partido")
        public ModelAndView irAPartido() {
            servicioJugador.registrar("carlos",1,1,1,1,1,1,"...");
            Jugador jugador = servicioJugador.buscarJugador("carlos");
            ModelMap modelo=new ModelMap();
            modelo.addAttribute("jugador", jugador);
            return new ModelAndView("partido",modelo);
        }
    @RequestMapping("/partido-aro")
    public ModelAndView irAlAro() {
        String nombre = "lucas";
        Integer drible = 12;
        Integer tiro = 12;
        Integer pase = 12;
        Integer robo = 12;
        Integer tapa = 12;
        Integer intercepcion = 12;
        String imagen  = "Lucas";
        servicioJugador.registrar(nombre,drible,tiro,pase,robo,tapa,intercepcion,imagen);

        return new ModelAndView("partido-aro");
    }
    @RequestMapping("/partido-resultado")
    public ModelAndView irAlResultado() {

        return new ModelAndView("partido-resultado");
    }

    }

