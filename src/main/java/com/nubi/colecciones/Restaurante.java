package com.nubi.colecciones;

import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Felipe on 22/08/2016.
 */
@Entity
public class Restaurante {
    @Id
    private String nombre;
    @Embedded
    private Localizacion localizacion;
    @Embedded
    private Estado estado;
    @Embedded
    private List<Comentario> comentarios;
    @Embedded
    private List<Ruta> rutas;

    public Restaurante() {
        comentarios= new ArrayList<Comentario>();
        rutas= new ArrayList<Ruta>();
    }

    public Restaurante(String nombre, Localizacion localizacion, Estado estado, List<Comentario> comentarios, List<Ruta> rutas) {
        this.nombre = nombre;
        this.localizacion = localizacion;
        this.estado = estado;
        this.comentarios = comentarios;
        this.rutas = rutas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Localizacion getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(Localizacion localizacion) {
        this.localizacion = localizacion;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public List<Ruta> getRutas() {
        return rutas;
    }

    public void setRutas(List<Ruta> rutas) {
        this.rutas = rutas;
    }
}
