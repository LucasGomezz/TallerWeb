package com.tallerwebi.presentacion;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorMenuPrincipal {
    @RequestMapping("/menuPrincipal")
    public ModelAndView irAMenuPrincipal() {

        return new ModelAndView("menuPrincipal");
    }
    @RequestMapping("/partido-items")
    public ModelAndView irAItems() {

        return new ModelAndView("partido-items");
    }

}
