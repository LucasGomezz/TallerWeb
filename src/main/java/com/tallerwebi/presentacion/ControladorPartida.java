package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.modelo.Equipo;
import com.tallerwebi.dominio.modelo.Partido;
import com.tallerwebi.infraestructura.servicio.ServicioEquipo;
import com.tallerwebi.infraestructura.servicio.ServicioJugador;
import com.tallerwebi.infraestructura.servicio.ServicioPartido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ControladorPartida {

    //Se pueden usar todos los servicios necesarios
    private ServicioJugador servicioJugador;
    private ServicioEquipo servicioEquipo;
    private ServicioPartido servicioPartido;

    //Los controladores se comunica con los servicios y saben si estatodo bien gracias a las exceptions
    @Autowired
    public ControladorPartida(ServicioJugador servicioJugador, ServicioEquipo servicioEquipo,ServicioPartido servicioPartido) {
        this.servicioJugador = servicioJugador;
        this.servicioEquipo = servicioEquipo;
        this.servicioPartido = servicioPartido;
    }

    @RequestMapping(value="/iniciarPartida",method = {RequestMethod.GET})
    public ModelAndView iniciarPartida(){
        Long id = servicioPartido.inicializarPartido();
        return new ModelAndView("redirect:/elegir-equipo?idPartido=" + id);
    }

    @RequestMapping(value = "/elegir-equipo", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView elegirEquipo(@RequestParam(value = "idPartido",required = true) Long idPartido) {
        ModelMap modelo = new ModelMap();

        Partido partido = servicioPartido.buscarPartido(idPartido);
        modelo.put("equipo1", partido.getEquipoJugador());
        modelo.put("equipo2", partido.getEquipoPc());
        modelo.put("equipos", servicioEquipo.listAll());
        modelo.put("idPartido", idPartido);

        return new ModelAndView("elegir-equipo", modelo);
    }

    @RequestMapping(value = "/elegirEquipoUno", method = {RequestMethod.POST})
    public ModelAndView elegirEquipoUno(@RequestParam(required = true) Long idEquipo, @RequestParam(required = true) Long idPartido) {
        ModelMap modelo = new ModelMap();
        modelo.put("equipos", servicioEquipo.listAll());
        servicioPartido.guardarEquipoJugador(idEquipo,idPartido);
        return new ModelAndView("redirect:/elegir-equipo?idPartido=" + idPartido, modelo);
    }

    @RequestMapping(value = "/elegirEquipoDos", method = {RequestMethod.POST})
    public ModelAndView elegirEquipoDos(@RequestParam(required = true) Long idEquipo2, @RequestParam(required = true) Long idPartido) {
        ModelMap modelo = new ModelMap();
        modelo.put("equipos", servicioEquipo.listAll());
        servicioPartido.guardarEquipoPc(idEquipo2,idPartido);
        return new ModelAndView("redirect:/elegir-equipo?idPartido=" + idPartido, modelo);
    }


    @RequestMapping("/partido-items")//Hacer verificacion de que esten los dos equipos
    public ModelAndView irAItems() {
        ModelMap modelo = new ModelMap();
        return new ModelAndView("partido-items", modelo);
    }

    @RequestMapping(path = "/partido", method = RequestMethod.GET)
    public ModelAndView irAPartido(@RequestParam(required = true) Long idPartido) {
        //www.web.unlam.com/partido?idEquipo1=1
        //www.web.unlam.com/partido/1/2
        //@ModelAttribute= objeto entero
        //@PathVariable= solo el valor
        //@RequestParam= tambien
        ModelMap modelo = new ModelMap();
        Partido partido = servicioPartido.buscarPartido(idPartido);
        PartidoDTO partidoNuevo = new PartidoDTO();
        aplicarImagenesAJugadores(partido);
        modelo.put("partido",partido);
        modelo.addAttribute("miPuntaje", partidoNuevo.getPuntajeYo().toString());
        modelo.addAttribute("puntajeRival", partidoNuevo.getPuntajeRival().toString());
        return new ModelAndView("partido", modelo);
    }

    private static void aplicarImagenesAJugadores(Partido partido) {
        Equipo equipo1 = partido.getEquipoJugador();
        equipo1.getJugador1().setImagen("images/JUGADOR-LOCAL-CON-PELOTA.png");
        equipo1.getJugador2().setImagen("images/JUGADOR-LOCAL.png");
        Equipo equipo2 = partido.getEquipoPc();
        equipo2.getJugador1().setImagen("images/JUGADOR-VISITANTE.png");
        equipo2.getJugador2().setImagen("images/JUGADOR-VISITANTE.png");
    }

    @RequestMapping( value = "/obtenerPuntaje", method = RequestMethod.GET)
    @ResponseBody
    public PartidoDTO obtenerPuntaje() {
        PartidoDTO partidoNuevo = new PartidoDTO();
        return partidoNuevo;
    }
    List<PartidoDTO> partido = new ArrayList<>();

    @RequestMapping( value = "/sumar", method = RequestMethod.POST)
    @ResponseBody
    public PartidoDTO sumarPuntaje(Integer puntajeYo, Integer puntajeRival) {
        PartidoDTO puntajes = servicioPartido.sumar(puntajeYo, puntajeRival);
        partido.add(puntajes);
        return puntajes;
    }

    @RequestMapping("/partido-aro")
    public ModelAndView irAlAro() {
        ModelMap modelo = new ModelMap();
        return new ModelAndView("partido-aro", modelo);
    }

    @RequestMapping("/partido-resultado")
    public ModelAndView irAlResultado() {
        ModelMap modelo = new ModelMap();

        return new ModelAndView("partido-resultado", modelo);
    }

}

