package com.tallerwebi.infraestructura;


import com.tallerwebi.dominio.ItemTienda;
import com.tallerwebi.dominio.RepositorioItemTienda;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("repositorioItemTienda")
public class RepositorioItemTiendaImpl implements RepositorioItemTienda {
    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioItemTiendaImpl(SessionFactory sessionFactory){this.sessionFactory = sessionFactory;}

    @Override
    public ItemTienda buscar(Long id) {
        final Session session = sessionFactory.getCurrentSession();
        return(ItemTienda) session.createCriteria(ItemTienda.class).add(Restrictions.eq("id", id)).uniqueResult();
    }

    @Override
    public void crear(ItemTienda itemTienda) {
        sessionFactory.getCurrentSession().save(itemTienda);
    }
    @Override
    public List<ItemTienda> listAll() {
        return sessionFactory.getCurrentSession().createCriteria(ItemTienda.class).list();
    }
}
