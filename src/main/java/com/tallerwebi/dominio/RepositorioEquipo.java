package com.tallerwebi.dominio;

import java.util.List;

public interface RepositorioEquipo {
    Equipo buscar (Long id);
    Equipo buscarPorNombre (String id);
    void crear(Equipo equipo);
    List<Equipo> listAll();
}
