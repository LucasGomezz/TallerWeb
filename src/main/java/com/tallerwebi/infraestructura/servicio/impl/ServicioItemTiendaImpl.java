package com.tallerwebi.infraestructura.servicio.impl;

import com.tallerwebi.dominio.modelo.ItemTienda;
import com.tallerwebi.dominio.repositorio.RepositorioItemTienda;
import com.tallerwebi.infraestructura.servicio.ServicioItemTienda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ServicioItemTiendaImpl implements ServicioItemTienda {
    @Autowired
    private RepositorioItemTienda repositorioItemTienda;

    @Autowired
    public ServicioItemTiendaImpl(RepositorioItemTienda repositorioItemTienda){
        this.repositorioItemTienda = repositorioItemTienda;

    }

    @Override
    public ItemTienda buscar(Long id) {
        return this.repositorioItemTienda.buscar(id);
    }

    @Override
    public void inicializarItemTienda(String imagen, String nombre, Integer precio) {
        ItemTienda itemTienda=new ItemTienda();
        itemTienda.setImagen(imagen);
        itemTienda.setNombre(nombre);
        itemTienda.setPrecio(precio);
        repositorioItemTienda.crear(itemTienda);
    }

    @Override
    public void inicializarItemTienda() {
        final String imagen="images/ITEM.png";
        inicializarItemTienda(imagen,"PowerShoot",40);
        inicializarItemTienda(imagen,"PowerPass",35);
        inicializarItemTienda(imagen,"PowerDrible",35);
        inicializarItemTienda(imagen,"GatoradeSteal",35);
        inicializarItemTienda(imagen,"GatoradeIntercept",35);
        inicializarItemTienda(imagen,"GatoradeBlock",40);
    }
    @Override
    public List<ItemTienda> listAll() {
        return repositorioItemTienda.listAll();
    }
}
