package com.tallerwebi.dominio.repositorio;
import com.tallerwebi.dominio.modelo.ItemTienda;

import java.util.List;
public interface RepositorioItemTienda {
    ItemTienda buscar(Long id);
    void crear(ItemTienda itemTienda);
    void eliminar(ItemTienda itemTienda);
    void actualizar(ItemTienda itemTienda);
    List<ItemTienda> listAll();
}
