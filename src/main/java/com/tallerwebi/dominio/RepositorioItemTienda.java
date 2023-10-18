package com.tallerwebi.dominio;
import java.util.List;
public interface RepositorioItemTienda {
    ItemTienda buscar(Long id);
    void crear(ItemTienda itemTienda);
    List<ItemTienda> listAll();
}
