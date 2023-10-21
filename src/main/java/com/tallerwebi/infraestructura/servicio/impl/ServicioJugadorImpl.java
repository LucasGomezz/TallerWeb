package com.tallerwebi.infraestructura.servicio.impl;

import com.tallerwebi.dominio.modelo.Jugador;
import com.tallerwebi.dominio.repositorio.RepositorioJugador;
import com.tallerwebi.infraestructura.servicio.ServicioJugador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ServicioJugadorImpl implements ServicioJugador {

    private RepositorioJugador repositorioJugador;

    @Autowired
    public ServicioJugadorImpl(RepositorioJugador repositorioJugador) {
        this.repositorioJugador = repositorioJugador;
    }

    @Override
    public Jugador buscarJugador(String nombre) {
        return this.repositorioJugador.buscar(nombre);
    }


    @Override
    public void inicializarJugador() {
        inicializarJugador("Jason Tatum", 71, 73, 75, 77, 80, 84, "");
        inicializarJugador("AL Horford", 70, 72, 76, 78, 81, 83, "");
        inicializarJugador("Lonzo Ball", 70, 73, 74, 77, 82, 85, "");
        inicializarJugador("Andre Drummond", 71, 72, 76, 79, 80, 84, "");
        inicializarJugador("Sharife Cooper", 70, 72, 75, 78, 81, 85, "");
        inicializarJugador("Tristan Thompson", 70, 74, 75, 77, 80, 84, "");
        inicializarJugador("Russell Westbrook", 71, 73, 76, 78, 82, 85, "");
        inicializarJugador("Kawhi Leonard", 71, 72, 75, 79, 80, 83, "");
        inicializarJugador("Donte DiVincenzo", 70, 73, 75, 79, 81, 84, "");
        inicializarJugador("Josh Hart", 70, 72, 74, 77, 82, 85, "");
        inicializarJugador("LeBron James", 71, 73, 74, 76, 84, 84, "");
        inicializarJugador("Anthony Davis", 71, 74, 76, 77, 80, 83, "");
        inicializarJugador("Tyler Herro", 70, 73, 76, 78, 82, 84, "");
        inicializarJugador("Duncan Robinson", 70, 71, 75, 79, 80, 85, "");
        inicializarJugador("Luka Doncic", 71, 73, 77, 79, 81, 84, "");
        inicializarJugador("Dwight Powell", 71, 72, 74, 77, 82, 84, "");
        inicializarJugador("Damian Lillard", 70, 74, 75, 79, 81, 83, "");
        inicializarJugador("Giannis Antetokounmpo", 70, 71, 76, 78, 84, 85, "");
        inicializarJugador("Stephen Curry", 70, 85, 76, 77, 81, 78, "");
        inicializarJugador("Klay Thompson", 71, 84, 75, 76, 80, 84, "");


    }

    @Override
    public void inicializarJugador(String nombre, Integer drible, Integer tiro, Integer pase, Integer robo, Integer tapa, Integer intercepcion, String imagen) {
        Jugador jugador = new Jugador();
        jugador.setNombre(nombre);
        jugador.setDrible(drible);
        jugador.setPase(pase);
        jugador.setRobo(robo);
        jugador.setTiro(tiro);
        jugador.setIntercepcion(intercepcion);
        jugador.setTapa(tapa);
        jugador.setImagen(imagen);
        repositorioJugador.guardar(jugador);

    }
}



