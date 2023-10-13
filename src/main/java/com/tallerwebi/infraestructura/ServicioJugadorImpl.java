package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.Jugador;
import com.tallerwebi.dominio.RepositorioJugador;
import com.tallerwebi.dominio.ServicioJugador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.annotation.ServletSecurity;
import javax.transaction.Transactional;

@Service
@Transactional
public class ServicioJugadorImpl implements ServicioJugador {

    private RepositorioJugador repositorioJugador;

    @Autowired
    public ServicioJugadorImpl(RepositorioJugador repositorioJugador){this.repositorioJugador = repositorioJugador;}


    @Override
    public Jugador buscarJugador(String nombre) {
        return this.repositorioJugador.buscar(nombre);
    }
}



