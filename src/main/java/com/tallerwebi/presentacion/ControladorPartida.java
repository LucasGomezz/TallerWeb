package com.tallerwebi.presentacion;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorPartida {
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
    }

