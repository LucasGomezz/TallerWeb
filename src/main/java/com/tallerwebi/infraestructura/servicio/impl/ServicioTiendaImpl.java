package com.tallerwebi.infraestructura.servicio.impl;

import com.tallerwebi.dominio.modelo.productoTienda;
import com.tallerwebi.dominio.repositorio.RepositorioTienda;
import com.tallerwebi.infraestructura.servicio.ServicioTienda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ServicioTiendaImpl implements ServicioTienda {
    @Autowired
    private RepositorioTienda repositorioTienda;

    @Autowired
    public ServicioTiendaImpl(RepositorioTienda repositorioTienda){
        this.repositorioTienda = repositorioTienda;

    }

    @Override
    public productoTienda buscar(Long id) {
        return this.repositorioTienda.buscar(id);
    }

    @Override
    public void inicializarItemTienda(String imagen, String nombre, Integer precio) {
        productoTienda productoTienda =new productoTienda();
        productoTienda.setImagen(imagen);
        productoTienda.setNombre(nombre);
        productoTienda.setPrecio(precio);
        repositorioTienda.crear(productoTienda);
    }

//    @Override
//    public void inicializarItemTienda() {
//        final String imagen="images/ITEM.png";
//        inicializarItemTienda(imagen,"PowerShoot",40);
//        inicializarItemTienda(imagen,"PowerPass",35);
//        inicializarItemTienda(imagen,"PowerDrible",35);
//        inicializarItemTienda(imagen,"GatoradeSteal",35);
//        inicializarItemTienda(imagen,"GatoradeIntercept",35);
//        inicializarItemTienda(imagen,"GatoradeBlock",40);
//    }
@Override
public void inicializarItemTienda() {
//    final String imagen="images/ITEM.png";
//    inicializarItemTienda(imagen,"PowerShoot",40);
//    inicializarItemTienda(imagen,"PowerPass",35);
//    inicializarItemTienda(imagen,"PowerDrible",35);
//    inicializarItemTienda(imagen,"GatoradeSteal",35);
//    inicializarItemTienda(imagen,"GatoradeIntercept",35);
//    inicializarItemTienda(imagen,"GatoradeBlock",40);
}
    @Override
    public List<productoTienda> listAll() {
        return repositorioTienda.listAll();
    }
}
