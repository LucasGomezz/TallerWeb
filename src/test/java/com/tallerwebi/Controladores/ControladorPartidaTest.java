package com.tallerwebi.Controladores;

import com.tallerwebi.dominio.modelo.Equipo;
import com.tallerwebi.dominio.modelo.Jugador;
import com.tallerwebi.dominio.modelo.Partido;
import com.tallerwebi.infraestructura.servicio.ServicioPartido;
import com.tallerwebi.infraestructura.servicio.ServicioTienda;
import com.tallerwebi.presentacion.ControladorPartida;
import com.tallerwebi.presentacion.PartidoDTO;
import org.dom4j.rule.Mode;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.parallel.Resources;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.OneToOne;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase;
import static org.mockito.Mockito.mock;


public class ControladorPartidaTest {
    ServicioTienda servicioTienda = mock(ServicioTienda.class);
    ServicioPartido servicioPartido = mock(ServicioPartido.class);
    ControladorPartida controladorPartida = new ControladorPartida(null,servicioPartido,null,servicioTienda);

    @Test
    public void siElPuntajeLlegaA21ElPartidoTermina(){
        Jugador jugador1 = new Jugador();
        jugador1.setId(1L);
        jugador1.setNombre("Jason Tatum");
        jugador1.setDrible(81);
        jugador1.setTiro(83);
        jugador1.setPase(84);
        jugador1.setRobo(78);
        jugador1.setTapa(79);
        jugador1.setIntercepcion(76);
        Jugador jugador2 = new Jugador();
        jugador2.setId(2L);
        jugador2.setNombre("AL Horford");
        jugador2.setDrible(82);
        jugador2.setTiro(80);
        jugador2.setPase(81);
        jugador2.setRobo(75);
        jugador2.setTapa(80);
        jugador2.setIntercepcion(77);
        Equipo equipo1 = new Equipo();
        equipo1.setIdEquipo(1L);
        equipo1.setColor("verde");
        equipo1.setLogo("images/logoEquipos/celtics.png");
        equipo1.setNombre("Boston Celtics");
        equipo1.setJugador1(jugador1);
        equipo1.setJugador2(jugador2);
        Jugador jugador3 = new Jugador();
        jugador3.setId(3L);
        jugador3.setNombre("Lonzo Ball");
        jugador3.setDrible(85);
        jugador3.setTiro(84);
        jugador3.setPase(83);
        jugador3.setRobo(79);
        jugador3.setTapa(75);
        jugador3.setIntercepcion(78);
        Jugador jugador4 = new Jugador();
        jugador4.setId(4L);
        jugador4.setNombre("Andre Drummond");
        jugador4.setDrible(80);
        jugador4.setTiro(81);
        jugador4.setPase(82);
        jugador4.setRobo(76);
        jugador4.setTapa(78);
        jugador4.setIntercepcion(80);
        Equipo equipo2 = new Equipo();
        equipo2.setIdEquipo(2L);
        equipo2.setColor("rojo");
        equipo2.setLogo("images/logoEquipos/bulls.png");
        equipo2.setNombre("Chicago Bulls");
        equipo2.setJugador1(jugador3);
        equipo2.setJugador2(jugador4);
        PartidoDTO partido = new PartidoDTO();
        partido.setIdPartido(1L);
        partido.setPuntajeJugador(21);
        partido.setPuntajePc(19);
        partido.setEquipoJugador(equipo1);
        partido.setEquipoPC(equipo2);
        partido.setPosicion(1);
        partido.setDadoPC(2);
        partido.setDadoJugador(2);
        partido.setTengoLaPelota(true);
        partido.setTienePelotaJugador(1);

        ModelAndView mav = whenElPartidoTermina(partido);
        thenDevuelveVistaPartidoResultado(mav);
    }

    private void thenDevuelveVistaPartidoResultado(ModelAndView mav) {
        assertThat(mav.getViewName(), equalToIgnoringCase("partido-resultado"));
    }


    private ModelAndView whenElPartidoTermina(PartidoDTO partido) {
        return controladorPartida.irAPartido(partido.getIdPartido());
    }

    @Test
    public void siExisteUnaPartidaGuardadaSeRetoma(){
        ModelAndView mav =  whenExisteUnaPartidaGuardada();
        thenSeRetoma(mav);
    }
    private ModelAndView whenExisteUnaPartidaGuardada() {
        return controladorPartida.checkGuardado();
    }
    private void thenSeRetoma(ModelAndView mav){
        assertThat(mav.getViewName(), equalToIgnoringCase("partidaGuardada"));
    }

}

