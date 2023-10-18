package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.ServicioEquipo;
import com.tallerwebi.dominio.ServicioItemTienda;
import com.tallerwebi.dominio.ServicioJugador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class ControladorTienda {
    private ServicioItemTienda servicioItemTienda;

    @Autowired
    public ControladorTienda(ServicioItemTienda servicioItemTienda){
        this.servicioItemTienda = servicioItemTienda;
    }
    @RequestMapping("/tienda")
    public ModelAndView irATienda() {
        ModelMap modelo = new ModelMap();
        modelo.put("itemsTienda", servicioItemTienda.listAll());
        return new ModelAndView("tienda",modelo);
    }

}