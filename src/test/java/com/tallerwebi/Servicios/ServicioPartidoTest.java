package com.tallerwebi.Servicios;

import com.tallerwebi.dominio.repositorio.RepositorioPartido;
import com.tallerwebi.infraestructura.servicio.ServicioEquipo;
import com.tallerwebi.infraestructura.servicio.ServicioPartido;
import com.tallerwebi.infraestructura.servicio.ServicioTienda;
import com.tallerwebi.infraestructura.servicio.impl.ServicioPartidoImpl;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;
import com.tallerwebi.dominio.modelo.Partido;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase;
import static org.mockito.Mockito.mock;

public class ServicioPartidoTest {

    RepositorioPartido repositorioPartido=mock(RepositorioPartido.class);
    ServicioEquipo servicioEquipo=mock(ServicioEquipo.class);
    ServicioPartido servicioPartido=new ServicioPartidoImpl(repositorioPartido, servicioEquipo);
    Partido partido= new Partido();

    @Test
    public void siExisteUnaPartidaGuardadaSeRetoma(){
        partido.setId(1L);
        partido.setGuardable(true);
        Long id = whenExisteUnaPartidaGuardada();
        thenSeRetoma(id);
    }
    private Long whenExisteUnaPartidaGuardada() {
       return servicioPartido.buscarPartidoGuardado();
    }
    private void thenSeRetoma(Long id){
        assertThat(id, equalTo(1L));
    }
}
