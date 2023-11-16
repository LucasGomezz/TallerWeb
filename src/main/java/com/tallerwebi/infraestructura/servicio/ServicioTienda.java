package com.tallerwebi.infraestructura.servicio;

import com.tallerwebi.dominio.modelo.productoTienda;

import java.util.List;

public interface ServicioTienda {
    productoTienda buscar(Long id);
    void inicializarItemTienda(String imagen, String nombre, Integer precio);
    void inicializarItemTienda();
    List<productoTienda> listAll();
}
