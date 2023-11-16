package com.tallerwebi.infraestructura.servicio;

import com.tallerwebi.dominio.modelo.Inventario;
import com.tallerwebi.dominio.modelo.productoTienda;

import java.util.List;

public interface ServicioInventario {

    List<Inventario> listAll();

    void agregar(productoTienda producto, int dinero);
}
