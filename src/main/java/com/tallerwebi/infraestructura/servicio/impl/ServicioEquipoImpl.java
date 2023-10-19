package com.tallerwebi.infraestructura.servicio.impl;

import com.tallerwebi.dominio.modelo.Equipo;
import com.tallerwebi.dominio.modelo.Jugador;
import com.tallerwebi.dominio.repositorio.RepositorioEquipo;
import com.tallerwebi.dominio.repositorio.RepositorioJugador;
import com.tallerwebi.infraestructura.servicio.ServicioEquipo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ServicioEquipoImpl implements ServicioEquipo {

    @Autowired
    private RepositorioEquipo repositorioEquipo;
    @Autowired
    private RepositorioJugador repositorioJugador;

    @Autowired
    public ServicioEquipoImpl(RepositorioJugador repositorioJugador, RepositorioEquipo respositorioEquipo){
        this.repositorioEquipo = respositorioEquipo;
        this.repositorioJugador= repositorioJugador;
    }


    @Override
    public Equipo buscarEquipo(Long idEquipo) {
        return this.repositorioEquipo.buscar(idEquipo);
    }

    @Override
    public List<Equipo> listAll() {
        return repositorioEquipo.listAll();
    }

    @Override
    public void inicializarEquipos() {
        inicializarEquipos("Boston Celtics", "Jason Tatum", "AL Horford","images/logoEquipos/celtics.png", "verde");
        inicializarEquipos("Chicago Bulls", "Lonzo Ball", "Andre Drummond","images/logoEquipos/bulls.png", "rojo");
        inicializarEquipos("Cleveland Cavaliers", "Sharife Cooper", "Tristan Thompson","images/logoEquipos/cleveland.png", "rojo");
        inicializarEquipos("LA Clippers", "Russell Westbrook", "Kawhi Leonard","images/logoEquipos/clippers.png", "azul");
        inicializarEquipos("New York Knicks", "Donte DiVincenzo", "Josh Hart","images/logoEquipos/knicks.png", "naranja");
        inicializarEquipos("Los Angeles Lakers", "LeBron James", "Anthony Davis","images/logoEquipos/lakers.png", "amarillo");
        inicializarEquipos("Miami Heat", "Tyler Herro", "Duncan Robinson","images/logoEquipos/miami.png", "rojo");
        inicializarEquipos("Dallas Mavericks", "Luka Doncic", "Dwight Powell","images/logoEquipos/dallas.png", "azul");
        inicializarEquipos("Milwaukee Bucks", "Damian Lillard", "Giannis Antetokounmpo","images/logoEquipos/bucks.png", "verde");
        inicializarEquipos("Golden State Warriors", "Stephen Curry", "Klay Thompson","images/logoEquipos/warriors.png", "azul");
    }



    @Override
    public void inicializarEquipos(String nombreEquipo,String nombreJugador1, String nombreJugador2, String logo, String color) {
        Equipo e = repositorioEquipo.buscarPorNombre(nombreEquipo);
        if(e==null){
            Jugador jugador1 = repositorioJugador.buscar(nombreJugador1);
            Jugador jugador2 = repositorioJugador.buscar(nombreJugador2);
            Equipo equipo = new Equipo();
            equipo.setLogo(logo);
            equipo.setColor(color);
            equipo.setNombre(nombreEquipo);
            equipo.setJugador1(jugador1);
            equipo.setJugador2(jugador2);
            repositorioEquipo.crear(equipo);
        }
    }
}