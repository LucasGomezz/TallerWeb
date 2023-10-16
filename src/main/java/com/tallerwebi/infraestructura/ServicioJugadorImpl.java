package com.tallerwebi.infraestructura;

import com.google.gson.Gson;
import com.tallerwebi.dominio.Jugador;
import com.tallerwebi.dominio.RepositorioJugador;
import com.tallerwebi.dominio.ServicioJugador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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



    public Boolean compararStats(Jugador jugador1, Jugador jugador2, String stat){
        Integer stat1=0;
        Integer stat2=0;
        switch(stat){
            case "drible":
                stat1=jugador1.getDrible();
                stat2=jugador2.getDrible();
                break;
            case "tiro":
                stat1=jugador1.getTiro();
                stat2=jugador2.getTiro();
                break;
            case "pase":
                stat1=jugador1.getPase();
                stat2=jugador2.getPase();
                break;
            case "robo":
                stat1=jugador1.getRobo();
                stat2=jugador2.getRobo();
                break;
            case "tapa":
                stat1=jugador1.getTapa();
                stat2=jugador2.getTapa();
                break;
            case "intercepcion":
                stat1=jugador1.getIntercepcion();
                stat2=jugador2.getIntercepcion();
                break;}
        if(stat1>stat2){
           /** Gson gson=new Gson();
            String json=gson.toJson(true);*/
            return true;}
        return false;}
}



