package com.tallerwebi.dominio.repositorio;
import com.tallerwebi.dominio.modelo.Inventario;
import com.tallerwebi.dominio.modelo.ProductoTienda;

import java.util.List;

public interface RepositorioInventario {

    Inventario buscar(Long idProductoInventario);
    List<Inventario> listAll();

    void agregar(ProductoTienda producto, int dinero);
}