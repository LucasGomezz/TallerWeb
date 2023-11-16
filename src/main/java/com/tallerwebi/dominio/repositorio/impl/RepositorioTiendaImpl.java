package com.tallerwebi.dominio.repositorio.impl;


import com.tallerwebi.dominio.modelo.productoTienda;
import com.tallerwebi.dominio.repositorio.RepositorioTienda;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("repositorioTienda")
public class RepositorioTiendaImpl implements RepositorioTienda {
    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioTiendaImpl(SessionFactory sessionFactory){this.sessionFactory = sessionFactory;}

    @Override
    public productoTienda buscar(Long idProducto) {
        final Session session = sessionFactory.getCurrentSession();
        return(productoTienda) session.createCriteria(productoTienda.class).add(Restrictions.eq("idProducto", idProducto)).uniqueResult();
    }
    @Override
    public void crear(productoTienda productoTienda) {
        sessionFactory.getCurrentSession().save(productoTienda);
    }
    @Override
    public List<productoTienda> listAll() {
        return sessionFactory.getCurrentSession().createCriteria(productoTienda.class).list();
    }
    @Override
    public void eliminar(productoTienda productoTienda) {
        sessionFactory.getCurrentSession().delete(productoTienda);
    }
    @Override
    public void actualizar(productoTienda productoTienda) {
        sessionFactory.getCurrentSession().update(productoTienda);
    }
}
