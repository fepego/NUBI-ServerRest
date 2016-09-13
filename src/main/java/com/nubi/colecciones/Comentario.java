package com.nubi.colecciones;

import org.mongodb.morphia.annotations.*;

import org.mongodb.morphia.utils.IndexDirection;

import java.util.Date;

/**
 * Created by Felipe on 23/08/2016.
 */
@Entity
@Indexes({@Index(value = "id", expireAfterSeconds = 3600),
        @Index(value= "registro")
})
public class Comentario {
    @Id
    private int idComentario;
    private String usuario;
    private String post;
    private Date registro;

    public Comentario() {
    }

    public Comentario(int idComentario, String usuario, String post, Date registro) {
        this.idComentario = idComentario;
        this.usuario = usuario;
        this.post = post;
        this.registro = registro;
    }

    public int getIdComentario() {
        return idComentario;
    }

    public void setIdComentario(int idComentario) {
        this.idComentario = idComentario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public Date getRegistro() {
        return registro;
    }

    public void setRegistro(Date registro) {
        this.registro = registro;
    }
}
