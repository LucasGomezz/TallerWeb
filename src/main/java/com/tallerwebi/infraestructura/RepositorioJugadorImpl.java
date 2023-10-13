package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.Jugador;
import com.tallerwebi.dominio.RepositorioJugador;
import com.tallerwebi.dominio.Usuario;
import javax.inject.Inject;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository("repositorioJugador")
public class RepositorioJugadorImpl implements RepositorioJugador {

    @Inject
    private SessionFactory sessionFactory;
    @Override
    public Jugador buscar(Long id) {
        return this.sessionFactory.getCurrentSession().get(Jugador.class, id);
    }
    @Override
    public void guardar(Jugador jugador) {
        sessionFactory.getCurrentSession().save(jugador);
    }
}

