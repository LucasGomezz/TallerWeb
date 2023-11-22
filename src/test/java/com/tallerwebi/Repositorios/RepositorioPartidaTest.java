/*package com.tallerwebi.Repositorios;

import com.tallerwebi.SpringTest;
import com.tallerwebi.dominio.modelo.Partido;
import com.tallerwebi.dominio.repositorio.RepositorioPartido;
import com.tallerwebi.dominio.repositorio.impl.RepositorioPartidoImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

public class RepositorioPartidaTest extends SpringTest {

    @InjectMocks
    RepositorioPartido repositorioPartido;

    @Test
    public void sePuedeActualizarUnPartido() {
        Partido partido = new Partido();
        repositorioPartido.guardar(partido);
        assertThat(partido.getId(), notNullValue());
    }

    @Test
    public void queSePuedaBuscarUnPartido() {
        Partido partido1 = new Partido();
        partido1.setId(1L);
        Partido partido2 = new Partido();
        partido2.setId(2L);
        session().getCurrentSession().save(partido1);
        session().getCurrentSession().save(partido2);
        Partido buscado = repositorioPartido.buscar(1L);
        assertThat(buscado, notNullValue());
    }
}*/