package com.nubi.colecciones;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.Indexed;

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

    public Notificacion() {
    }

    public Notificacion(String tipo, String remitente, String destinatario, String comentario) {
        this.tipo = tipo;
        this.remitente = remitente;
        this.destinatario = destinatario;
        this.comentario = comentario;
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

    @Override
    public String toString() {
        return "Notificacion{" +
                "id='" + id + '\'' +
                ", tipo='" + tipo + '\'' +
                ", remitente='" + remitente + '\'' +
                ", destinatario='" + destinatario + '\'' +
                ", comentario='" + comentario + '\'' +
                '}';
    }
}
