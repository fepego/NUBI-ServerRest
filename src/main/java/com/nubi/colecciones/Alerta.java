package com.nubi.colecciones;

import org.apache.commons.codec.binary.Base64;
import org.mongodb.morphia.annotations.*;

import java.util.Date;

/**
 * Created by felipe on 13/09/16.
 */
@Entity
@Indexes({@Index(value="fechaPublicacion"),
        @Index(value="horaPublicacion")
})
public class Alerta {

    private long  horaPublicacion;
    private String estado;
    private String comentario;
    @Reference
    private SitiosEstudio sitioEst;
    @Reference
    private Restaurante restaurante;
    @Reference
    private Fotocopiadora fotocopiadora;

    public Alerta() {
    }

    public Alerta(long horaPublicacion, String estado,
                  String comentario, SitiosEstudio sitioEst, Restaurante restaurante, Fotocopiadora fotocopiadora) {
        this.horaPublicacion = horaPublicacion;
        this.estado = estado;
        this.comentario = comentario;
        this.sitioEst = sitioEst;
        this.restaurante = restaurante;
        this.fotocopiadora = fotocopiadora;
    }

    public long getHoraPublicacion() {
        return horaPublicacion;
    }

    public void setHoraPublicacion(long horaPublicacion) {
        this.horaPublicacion = horaPublicacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public SitiosEstudio getSitioEst() {
        return sitioEst;
    }

    public void setSitioEst(SitiosEstudio sitioEst) {
        this.sitioEst = sitioEst;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }

    public Fotocopiadora getFotocopiadora() {
        return fotocopiadora;
    }

    public void setFotocopiadora(Fotocopiadora fotocopiadora) {
        this.fotocopiadora = fotocopiadora;
    }

    @Override
    public String toString() {
        return "Alerta{" +
                "horaPublicacion=" + horaPublicacion +
                ", estado='" + estado + '\'' +
                ", comentario='" + comentario + '\'' +
                ", sitioEst=" + sitioEst +
                ", restaurante=" + restaurante +
                ", fotocopiadora=" + fotocopiadora +
                '}';
    }
}
