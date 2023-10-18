package com.tallerwebi.dominio;

import java.util.List;

public interface ServicioItemTienda {
    ItemTienda buscar(Long id);
    void inicializarItemTienda(String imagen, String nombre, Integer precio);
    void inicializarItemTienda();
    List<ItemTienda> listAll();
}
