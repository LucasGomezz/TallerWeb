package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.modelo.Equipo;

import java.util.Random;

public class PartidoDTO {
    private Long idPartido;
    private Integer puntajeJugador = 0;
    private Integer puntajePc = 0;
    private Equipo equipoJugador;
    private Equipo equipoPC;
    private Integer posicion = 1;
    private Integer dado;
    private Boolean tengoLaPelota = true;
    private Integer tienePelotaJugador = 1;

    public Integer getPuntajeJugador() {
        return puntajeJugador;
    }

    public void setPuntajeJugador(Integer puntajeJugador) {
        this.puntajeJugador = puntajeJugador;
    }

    public Integer getPuntajePc() {
        return puntajePc;
    }

    public void setPuntajePc(Integer puntajePc) {
        this.puntajePc = puntajePc;
    }

    public Equipo getEquipoJugador() {
        return equipoJugador;
    }

    public void setEquipoJugador(Equipo equipoJugador) {
        this.equipoJugador = equipoJugador;
    }

    public Equipo getEquipoPC() {
        return equipoPC;
    }

    public void setEquipoPC(Equipo equipoPC) {
        this.equipoPC = equipoPC;
    }

    public Integer getPosicion() {
        return posicion;
    }

    public void setPosicion(Integer posicion) {
        this.posicion = posicion;
    }

    public Long getIdPartido() {
        return idPartido;
    }

    public void setIdPartido(Long idPartido) {
        this.idPartido = idPartido;
    }

    public Boolean getTengoLaPelota() {
        return tengoLaPelota;
    }

    public void setTengoLaPelota(Boolean tengoLaPelota) {
        this.tengoLaPelota = tengoLaPelota;
    }

    public Integer getDado() {
        return dado;
    }

    public void setDado(Integer dado) {
        this.dado = dado;
    }

    public Integer getTienePelotaJugador() {
        return tienePelotaJugador;
    }

    public void setTienePelotaJugador(Integer tienePelotaJugador) {
        this.tienePelotaJugador = tienePelotaJugador;
    }

    public Integer tirarDado() {
        Random rand = new Random();
        dado = rand.nextInt(20) + 1;
        return dado;
    }
    public void setImagenes() {
        if(getTengoLaPelota() && getTienePelotaJugador() == 1){
            getEquipoJugador().getJugador1().setImagen("images/JUGADOR-LOCAL-CON-PELOTA.png");
            getEquipoJugador().getJugador2().setImagen("images/JUGADOR-LOCAL.png");
            getEquipoPC().getJugador1().setImagen("images/JUGADOR-VISITANTE.png");
            getEquipoPC().getJugador2().setImagen("images/JUGADOR-VISITANTE.png");
        }else if(getTengoLaPelota() && getTienePelotaJugador() == 2) {
            getEquipoJugador().getJugador1().setImagen("images/JUGADOR-LOCAL.png");
            getEquipoJugador().getJugador2().setImagen("images/JUGADOR-LOCAL-CON-PELOTA.png");
            getEquipoPC().getJugador1().setImagen("images/JUGADOR-VISITANTE.png");
            getEquipoPC().getJugador2().setImagen("images/JUGADOR-VISITANTE.png");
        }else if(!getTengoLaPelota() && getTienePelotaJugador() == 1){
            getEquipoJugador().getJugador1().setImagen("images/JUGADOR-LOCAL.png");
            getEquipoJugador().getJugador2().setImagen("images/JUGADOR-LOCAL.png");
            getEquipoPC().getJugador1().setImagen("images/JUGADOR-VISITANTE-CON-PELOTA.png");
            getEquipoPC().getJugador2().setImagen("images/JUGADOR-VISITANTE.png");
        }else if(!getTengoLaPelota() && getTienePelotaJugador() == 2){
            getEquipoJugador().getJugador1().setImagen("images/JUGADOR-LOCAL.png");
            getEquipoJugador().getJugador2().setImagen("images/JUGADOR-LOCAL.png");
            getEquipoPC().getJugador1().setImagen("images/JUGADOR-VISITANTE.png");
            getEquipoPC().getJugador2().setImagen("images/JUGADOR-VISITANTE-CON-PELOTA.png");
        }
    }
}
