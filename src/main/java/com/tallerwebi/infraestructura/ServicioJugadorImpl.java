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
    public ServicioJugadorImpl(RepositorioJugador repositorioJugador){
        this.repositorioJugador = repositorioJugador;};

    @Override
    public void registrar(String nombre, Integer drible, Integer tiro, Integer pase, Integer robo,
                          Integer tapa, Integer intercepcion, String imagen) {
        Jugador jugador = new Jugador();
        this.repositorioJugador.guardar(jugador);
        jugador.setNombre(nombre);
        jugador.setDrible(drible);
        jugador.setTiro(tiro);
        jugador.setPase(pase);
        jugador.setRobo(robo);
        jugador.setTapa(tapa);
        jugador.setIntercepcion(intercepcion);
        jugador.setImagen(imagen);
}

    @Override
    public Jugador buscarJugador(String nombre) {
        return this.repositorioJugador.buscar(nombre);
    }
}



