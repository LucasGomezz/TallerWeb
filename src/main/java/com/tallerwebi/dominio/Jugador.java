package com.tallerwebi.dominio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Jugador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    //meter todos los stats en una clase Stats?
    private Integer drible;
    private Integer tiroDoble;
    private Integer tiroTriple;
    private Integer pase;
    private Integer robo;
    private Integer tape;
    private Integer intercepcion;
    private String imagen;

    public Jugador() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getDrible() {
        return drible;
    }

    public void setDrible(Integer drible) {
        this.drible = drible;
    }

    public Integer getTiroDoble() {
        return tiroDoble;
    }

    public void setTiroDoble(Integer tiroDoble) {
        this.tiroDoble = tiroDoble;
    }

    public Integer getTiroTriple() {
        return tiroTriple;
    }

    public void setTiroTriple(Integer tiroTriple) {
        this.tiroTriple = tiroTriple;
    }

    public Integer getPase() {
        return pase;
    }

    public void setPase(Integer pase) {
        this.pase = pase;
    }

    public Integer getRobo() {
        return robo;
    }

    public void setRobo(Integer robo) {
        this.robo = robo;
    }

    public Integer getTape() {
        return tape;
    }

    public void setTape(Integer tape) {
        this.tape = tape;
    }

    public Integer getIntercepcion() {
        return intercepcion;
    }

    public void setIntercepcion(Integer intercepcion) {
        this.intercepcion = intercepcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
