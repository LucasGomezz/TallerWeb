package com.tallerwebi.infraestructura.servicio;

import com.tallerwebi.dominio.modelo.Inventario;
import com.tallerwebi.dominio.modelo.ProductoTienda;

import java.util.List;

public interface ServicioInventario {

    List<Inventario> listAll();

    void agregar(ProductoTienda producto, int dinero);
}
