package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.Equipo;
import com.tallerwebi.dominio.Jugador;
import com.tallerwebi.dominio.RepositorioEquipo;
import com.tallerwebi.dominio.RepositorioJugador;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("repositorioEquipo")
public class RepositoriosEquipoImpl  implements RepositorioEquipo{

    private SessionFactory sessionFactory;

    @Autowired
    public RepositoriosEquipoImpl(SessionFactory sessionFactory){this.sessionFactory = sessionFactory;}

    @Override
    public void agregarJugador(Jugador jugador) {
        sessionFactory.getCurrentSession().save(jugador);
    }

    @Override
    public Equipo buscar(String nombre) {
        final Session session = sessionFactory.getCurrentSession();
        return(Equipo) session.createCriteria(Equipo.class).add(Restrictions.eq("nombre", nombre)).uniqueResult();
    }

    @Override
    public void crear(Equipo equipo) {
        sessionFactory.getCurrentSession().save(equipo);
    }
}
