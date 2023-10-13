package com.tallerwebi.dominio;

import javax.persistence.*;
import java.util.List;

@Entity
public class Partido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer puntosUsuario;
    private Integer puntosPc;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Usuario usuario;
    @Transient
    private List<Jugador> jugadoresUsuario;
    @Transient
    private List<Jugador> jugadoresPc;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPuntosUsuario() {
        return puntosUsuario;
    }
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public void setPuntosUsuario(Integer puntosUsuario) {
        this.puntosUsuario = puntosUsuario;
    }

    public Integer getPuntosPc() {
        return puntosPc;
    }

    public void setPuntosPc(Integer puntosPc) {
        this.puntosPc = puntosPc;
    }

    public List<Jugador> getJugadoresUsuario() {
        return jugadoresUsuario;
    }

    public void setJugadoresUsuario(List<Jugador> jugadoresUsuario) {
        this.jugadoresUsuario = jugadoresUsuario;
    }

    public List<Jugador> getJugadoresPc() {
        return jugadoresPc;
    }

    public void setJugadoresPc(List<Jugador> jugadoresPc) {
        this.jugadoresPc = jugadoresPc;
    }
}
