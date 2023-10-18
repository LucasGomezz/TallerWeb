package com.tallerwebi.dominio.repositorio;
import com.tallerwebi.dominio.modelo.ItemTienda;

import java.util.List;
public interface RepositorioItemTienda {
    ItemTienda buscar(Long id);
    void crear(ItemTienda itemTienda);
    List<ItemTienda> listAll();
}
