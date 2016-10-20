package com.nubi.colecciones;

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

    @Id
    private String id;
    private Date fechaPublicacion;
    private long  horaPublicacion;
    private String estado;
    private String comentario;
    @Reference
    private SitiosEstudio sitioEst;
    @Reference
    private Restaurante restaurante;
    @Reference
    private Fotocopiadora fotocopiadora;
    private String usuario;
    private int like;
    private int dislike;

    public Alerta() {
        like=0;
        dislike=0;
    }

    public Alerta(Date fechaPublicacion, long horaPublicacion, String estado, SitiosEstudio sitioEst, String usuario) {
        this.fechaPublicacion = fechaPublicacion;
        this.horaPublicacion = horaPublicacion;
        this.estado = estado;
        this.sitioEst = sitioEst;
        this.usuario = usuario;
        like=0;
        dislike=0;
    }

    public Alerta(Date fechaPublicacion, long horaPublicacion, String estado, Restaurante restaurante, String usuario) {
        this.fechaPublicacion = fechaPublicacion;
        this.horaPublicacion = horaPublicacion;
        this.estado = estado;
        this.restaurante = restaurante;
        this.usuario = usuario;
        like=0;
        dislike=0;
    }

    public Alerta(Date fechaPublicacion, long horaPublicacion, String estado, Fotocopiadora fotocopiadora, String usuario) {
        this.fechaPublicacion = fechaPublicacion;
        this.horaPublicacion = horaPublicacion;
        this.estado = estado;
        this.fotocopiadora = fotocopiadora;
        this.usuario = usuario;
        like=0;
        dislike=0;
    }

    public Alerta(Date fechaPublicacion, long horaPublicacion, String estado, String comentario, SitiosEstudio sitioEst, String usuario) {
        this.fechaPublicacion = fechaPublicacion;
        this.horaPublicacion = horaPublicacion;
        this.estado = estado;
        this.comentario = comentario;
        this.sitioEst = sitioEst;
        this.usuario = usuario;
        like=0;
        dislike=0;
    }

    public Alerta(Date fechaPublicacion, long horaPublicacion, String estado, String comentario, Restaurante restaurante, String usuario) {
        this.fechaPublicacion = fechaPublicacion;
        this.horaPublicacion = horaPublicacion;
        this.estado = estado;
        this.comentario = comentario;
        this.restaurante = restaurante;
        this.usuario = usuario;
        like=0;
        dislike=0;
    }

    public Alerta(Date fechaPublicacion, long horaPublicacion, String estado, String comentario, Fotocopiadora fotocopiadora, String usuario) {
        this.fechaPublicacion = fechaPublicacion;
        this.horaPublicacion = horaPublicacion;
        this.estado = estado;
        this.comentario = comentario;
        this.fotocopiadora = fotocopiadora;
        this.usuario = usuario;
        like=0;
        dislike=0;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
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

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getDislike() {
        return dislike;
    }

    public void setDislike(int dislike) {
        this.dislike = dislike;
    }

    @Override
    public String toString() {
        return "Alerta{" +
                "id='" + id + '\'' +
                ", fechaPublicacion=" + fechaPublicacion +
                ", horaPublicacion=" + horaPublicacion +
                ", estado='" + estado + '\'' +
                ", comentario='" + comentario + '\'' +
                ", sitioEst=" + sitioEst +
                ", restaurante=" + restaurante +
                ", fotocopiadora=" + fotocopiadora +
                ", usuario='" + usuario + '\'' +
                ", like=" + like +
                ", dislike=" + dislike +
                '}';
    }
}
