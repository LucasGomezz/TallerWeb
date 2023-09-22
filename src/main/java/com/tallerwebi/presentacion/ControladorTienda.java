package com.tallerwebi.presentacion;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class ControladorTienda {
    @RequestMapping("/home")
    public ModelAndView irATienda() {

        return new ModelAndView("tienda");
    }
    @RequestMapping("/partida")
    public ModelAndView irANuevaPartida() {

        return new ModelAndView("nueva-partida");
    }
}