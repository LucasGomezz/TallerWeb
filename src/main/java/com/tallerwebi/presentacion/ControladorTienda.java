package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.modelo.productoTienda;
import com.tallerwebi.infraestructura.servicio.ServicioTienda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class ControladorTienda {
    private ServicioTienda servicioTienda;
    private ControladorInventario controladorInventario;
    private ControladorLogin controladorUsuario;

    @Autowired
    public ControladorTienda(ServicioTienda servicioTienda, ControladorInventario controladorInventario, ControladorLogin controladorUsuario){
        this.servicioTienda = servicioTienda;
        this.controladorInventario = controladorInventario;
        this.controladorUsuario = controladorUsuario;
    }
    @RequestMapping("/tienda")
    public ModelAndView irATienda() {
        ModelMap modelo = new ModelMap();
        modelo.put("productos", servicioTienda.listAll());
        modelo.put("dinero", controladorUsuario.mostrarDinero());
        return new ModelAndView("tienda",modelo);
    }

    @RequestMapping(value = "/comprar", method = RequestMethod.POST)
    public ModelAndView comprar(@RequestParam(required = false) Long idProducto, int dinero) {
        productoTienda producto = servicioTienda.buscar(idProducto);
        controladorInventario.agregar(producto, dinero);
        return new ModelAndView("redirect:/tienda");
    }

}