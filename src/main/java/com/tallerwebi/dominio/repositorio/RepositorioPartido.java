package com.tallerwebi.dominio.repositorio;

import com.tallerwebi.dominio.modelo.Partido;

import java.util.List;

public interface RepositorioPartido {
    Partido buscar (Long id);
    Long crear(Partido partido);
    List<Partido> listAll();
}
