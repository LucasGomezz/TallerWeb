package com.tallerwebi.dominio.repositorio;
import com.tallerwebi.dominio.modelo.productoTienda;

import java.util.List;
public interface RepositorioTienda{
    productoTienda buscar(Long id);
    void crear(productoTienda productoTienda);
    void eliminar(productoTienda productoTienda);
    void actualizar(productoTienda productoTienda);
    List<productoTienda> listAll();
}
