package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.Jugador;
import com.tallerwebi.dominio.RepositorioJugador;
import com.tallerwebi.dominio.ServicioJugador;
import org.springframework.stereotype.Service;

import javax.servlet.annotation.ServletSecurity;

@Service("servicioJugador")
public class ServicioJugadorImpl implements ServicioJugador {
    private RepositorioJugador repoJugador;


    @Override
    public void registrar(String nombre, Integer drible, Integer tiro, Integer pase, Integer robo,
                          Integer tapa, Integer intercepcion, String imagen) {
        Jugador jugador = new Jugador();
        this.repoJugador.guardar(jugador);
        jugador.setNombre(nombre);
        jugador.setDrible(drible);
        jugador.setTiro(tiro);
        jugador.setPase(pase);
        jugador.setRobo(robo);
        jugador.setTapa(tapa);
        jugador.setIntercepcion(intercepcion);
        jugador.setImagen(imagen);
}
}



