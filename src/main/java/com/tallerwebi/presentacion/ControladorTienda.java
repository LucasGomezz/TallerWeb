package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.modelo.ProductoTienda;
import com.tallerwebi.infraestructura.servicio.ServicioInventario;
import com.tallerwebi.infraestructura.servicio.ServicioTienda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ControladorTienda {
    private ServicioTienda servicioTienda;

    private ServicioInventario servicionInventario;

    @Autowired
    public ControladorTienda(ServicioTienda servicioTienda, ServicioInventario servicionInventario){
        this.servicioTienda = servicioTienda;
        this.servicionInventario = servicionInventario;
    }
    @RequestMapping("/tienda")
    public ModelAndView irATienda() {
        ModelMap modelo = new ModelMap();
        modelo.put("productos", servicioTienda.listAll());
        modelo.put("dinero", servicioTienda.mostrarDinero());
        return new ModelAndView("tienda",modelo);
    }

    @RequestMapping(value = "/comprar", method = RequestMethod.POST)
    public ModelAndView comprar(@RequestParam(required = false) Long idProducto, Integer dinero, HttpServletRequest request) {
        ProductoTienda producto = servicioTienda.buscar(idProducto);
        Long id = (Long) request.getSession().getAttribute("id");
        servicionInventario.agregar(producto, dinero, id);
        return new ModelAndView("redirect:/tienda");
    }

}