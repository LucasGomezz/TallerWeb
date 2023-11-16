package com.tallerwebi.dominio.repositorio.impl;


import com.tallerwebi.dominio.modelo.Inventario;
import com.tallerwebi.dominio.modelo.productoTienda;
import com.tallerwebi.dominio.modelo.Usuario;
import com.tallerwebi.dominio.repositorio.RepositorioInventario;

import com.tallerwebi.dominio.repositorio.RepositorioUsuario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("repositorioInventario")
public class RepositorioInventarioImpl implements RepositorioInventario{

    private SessionFactory sessionFactory;
    private RepositorioUsuarioImpl repoUsuario;

    @Autowired
    public RepositorioInventarioImpl(SessionFactory sessionFactory, RepositorioUsuarioImpl repoUsuario){
        this.sessionFactory = sessionFactory;
        this.repoUsuario = repoUsuario;
    }

    @Override
    public Inventario buscar(Long idProductoInventario) {
        final Session session = sessionFactory.getCurrentSession();
        return(Inventario) session.createCriteria(Inventario.class).add(Restrictions.eq("id", idProductoInventario)).uniqueResult();
    }
    @Override
    public List<Inventario> listAll() {
        return sessionFactory.getCurrentSession().createCriteria(Inventario.class).list();
    }

    @Override
    public void agregar(productoTienda producto, int dinero) {
        final Session session = sessionFactory.getCurrentSession();
        Inventario inventario = new Inventario();
        Inventario productoBuscado = (Inventario) session.createCriteria(Inventario.class)
                .add(Restrictions.eq("nombre", producto.getNombre()))
                .uniqueResult();

        if(dinero > producto.getPrecio()){
            repoUsuario.modificarDinero(dinero, producto.getPrecio());
            if(productoBuscado != null){
                productoBuscado.setCantidad(productoBuscado.getCantidad() + 1);
                session.update(productoBuscado);
            } else{
                inventario.setIdProductoInventario(producto.getIdProducto());
                inventario.setCategoria(producto.getCategoria().getNombre());
                inventario.setPorcentaje(producto.getCategoria().getPorcentaje());
                inventario.setImagen(producto.getImagen());
                inventario.setNombre(producto.getNombre());
                inventario.setCantidad(inventario.getCantidad() + 1);
                session.save(inventario);
            }
        }
    }

//    @Override
//    public int incrementarCantidad(productoTienda producto, int cantidad){
//        boolean productoEnInventario = buscarPorNombre(producto.getNombre());
//        if(productoEnInventario){
//            return cantidad +1;
//        }else{
//            return cantidad;
//        }
//    }
//
//    @Override
//    public boolean buscarPorNombre(String nombreProducto) {
//        final Session session = sessionFactory.getCurrentSession();
//        Inventario inventario = (Inventario) session.createCriteria(Inventario.class)
//                .add(Restrictions.eq("nombre", nombreProducto))
//                .uniqueResult();
//        return inventario != null;
//    }

}
