package com.tallerwebi.dominio.repositorio;
import com.tallerwebi.dominio.modelo.Inventario;
import com.tallerwebi.dominio.modelo.productoTienda;

import java.util.List;

public interface RepositorioInventario {

    Inventario buscar(Long idProductoInventario);
    List<Inventario> listAll();

    void agregar(productoTienda producto, int dinero);
}