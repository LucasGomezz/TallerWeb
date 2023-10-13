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

    @OneToOne
    private Equipo equipoJugador;
    @OneToOne
    private Equipo equipoPc;


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

    public Equipo getEquipoJugador() {
        return equipoJugador;
    }

    public void setEquipoJugador(Equipo equipoJugador) {
        this.equipoJugador = equipoJugador;
    }

    public Equipo getEquipoPc() {
        return equipoPc;
    }

    public void setEquipoPc(Equipo equipoPc) {
        this.equipoPc = equipoPc;
    }
}
