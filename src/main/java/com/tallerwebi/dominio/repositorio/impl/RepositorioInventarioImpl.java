package com.tallerwebi.dominio.repositorio.impl;


import com.tallerwebi.dominio.modelo.Inventario;
import com.tallerwebi.dominio.modelo.ItemTienda;
import com.tallerwebi.dominio.modelo.Jugador;
import com.tallerwebi.dominio.repositorio.RepositorioInventario;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("repositorioInventario")
public class RepositorioInventarioImpl implements RepositorioInventario{

    @Override
    public void agregarElementoAlInventario(Jugador jugador, ItemTienda elemento, int cantidad) {

    }

    @Override
    public void eliminarElementoDelInventario(Jugador jugador, ItemTienda elemento, int cantidad) {

    }

    @Override
    public List<Inventario> obtenerInventarioDeJugador(Jugador jugador) {
        return null;
    }
}
