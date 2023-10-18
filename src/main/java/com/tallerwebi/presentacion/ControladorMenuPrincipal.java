package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.ServicioEquipo;
import com.tallerwebi.dominio.ServicioItemTienda;
import com.tallerwebi.dominio.ServicioJugador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorMenuPrincipal {
    @Autowired
    private ServicioEquipo servicioEquipo;
    @Autowired
    private ServicioJugador servicioJugador;
    @Autowired
    private ServicioItemTienda servicioItemTienda;

    @RequestMapping("/menuPrincipal")
    public ModelAndView irAMenuPrincipal() {
        servicioJugador.inicializarJugador();
        servicioEquipo.inicializarEquipos();
        servicioItemTienda.inicializarItemTienda();
        return new ModelAndView("menuPrincipal");
    }


}
