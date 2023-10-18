package com.tallerwebi.infraestructura.servicio;

import com.tallerwebi.dominio.modelo.ItemTienda;

import java.util.List;

public interface ServicioItemTienda {
    ItemTienda buscar(Long id);
    void inicializarItemTienda(String imagen, String nombre, Integer precio);
    void inicializarItemTienda();
    List<ItemTienda> listAll();
}
