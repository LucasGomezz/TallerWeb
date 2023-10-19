package com.tallerwebi.presentacion;

public class PartidoDTO {
    private Integer puntajeYo = 0;
    private Integer puntajeRival = 0;

    public Integer getPuntajeYo() {
        return this.puntajeYo;
    }

    public void setPuntajeYo(Integer puntajeYo) {
        this.puntajeYo = puntajeYo;
    }

    public Integer getPuntajeRival() {
        return puntajeRival;
    }

    public void setPuntajeRival(Integer puntajeRival) {
        this.puntajeRival = puntajeRival;
    }
}
