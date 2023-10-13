package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.Equipo;
import com.tallerwebi.dominio.RepositorioEquipo;
import com.tallerwebi.dominio.RepositorioJugador;
import com.tallerwebi.dominio.ServicioEquipo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ServicioEquipoImpl implements ServicioEquipo {
    private RepositorioEquipo repositorioEquipo;

    @Autowired
    public ServicioEquipoImpl(RepositorioJugador repositorioJugador){
        this.repositorioEquipo = repositorioEquipo;}

    @Override
    public Equipo buscarEquipo(String nombre) {
        return this.repositorioEquipo.buscar(nombre);
    }
}
