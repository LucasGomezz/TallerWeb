package com.tallerwebi.dominio.repositorio;

import com.tallerwebi.dominio.modelo.Usuario;

public interface RepositorioUsuario {

    Usuario buscarUsuario(String email, String password);
    void guardar(Usuario usuario);
    Usuario buscar(String email);
    void modificar(Usuario usuario);
    Integer mostrarDinero();
    void modificarDinero(Integer dinero, Integer precio);
}

