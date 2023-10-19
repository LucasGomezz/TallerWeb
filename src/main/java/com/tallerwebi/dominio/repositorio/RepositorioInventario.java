package com.tallerwebi.dominio.repositorio;
import com.tallerwebi.dominio.modelo.Inventario;
import com.tallerwebi.dominio.modelo.Jugador;
import com.tallerwebi.dominio.modelo.ItemTienda;

import java.util.List;

public interface RepositorioInventario {



    void agregarElementoAlInventario(Jugador jugador, ItemTienda elemento, int cantidad);
    void eliminarElementoDelInventario(Jugador jugador, ItemTienda elemento, int cantidad);

    List<Inventario> obtenerInventarioDeJugador(Jugador jugador);

}