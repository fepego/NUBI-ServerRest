package com.nubi.colecciones;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.Indexed;

import java.util.Date;

/**
 * Created by Felipe on 8/10/2016.
 */
@Entity
public class Notificacion {
    @Indexed
    @Id
    private String id;
    private String tipo;
    @Indexed
    private String remitente;
    @Indexed
    private String destinatario;
    private String comentario;
    private boolean estadoLectura;
    private Date  horaNotificacion;
    private String sitio;

    public Notificacion() {
    }

    public Notificacion(String tipo, String remitente, String destinatario, String comentario) {
        this.tipo = tipo;
        this.remitente = remitente;
        this.destinatario = destinatario;
        this.comentario = comentario;
        this.estadoLectura= false;
    }

    public Notificacion(String tipo, String remitente, String destinatario, String comentario, boolean estadoLectura,
                        Date horaNotificacion) {

        this.tipo = tipo;
        this.remitente = remitente;
        this.destinatario = destinatario;
        this.comentario = comentario;
        this.estadoLectura = estadoLectura;
        this.horaNotificacion = horaNotificacion;
    }

    public Notificacion(String tipo, String remitente, String destinatario, String comentario, boolean estadoLectura, Date horaNotificacion, String sitio) {
        this.tipo = tipo;
        this.remitente = remitente;
        this.destinatario = destinatario;
        this.comentario = comentario;
        this.estadoLectura = estadoLectura;
        this.horaNotificacion = horaNotificacion;
        this.sitio = sitio;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getRemitente() {
        return remitente;
    }

    public void setRemitente(String remitente) {
        this.remitente = remitente;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public boolean isEstadoLectura() {
        return estadoLectura;
    }

    public void setEstadoLectura(boolean estadoLectura) {
        this.estadoLectura = estadoLectura;
    }

    public Date getHoraNotificacion() {
        return horaNotificacion;
    }

    public void setHoraNotificacion(Date horaNotificacion) {
        this.horaNotificacion = horaNotificacion;
    }

    public String getSitio() {
        return sitio;
    }

    public void setSitio(String sitio) {
        this.sitio = sitio;
    }

    @Override
    public String toString() {
        return "Notificacion{" +
                "id='" + id + '\'' +
                ", tipo='" + tipo + '\'' +
                ", remitente='" + remitente + '\'' +
                ", destinatario='" + destinatario + '\'' +
                ", comentario='" + comentario + '\'' +
                ", estadoLectura=" + estadoLectura +
                ", horaNotificacion=" + horaNotificacion +
                '}';
    }
}
