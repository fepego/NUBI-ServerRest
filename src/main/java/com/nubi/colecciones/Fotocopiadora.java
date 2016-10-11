package com.nubi.colecciones;

import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Felipe on 30/08/2016.
 */
@Entity
public class Fotocopiadora {

    @Id
    private String nombre;
    private Localizacion localizacion;
    private boolean autoServicio;
    private Estado estado;
    @Embedded
    private List<Comentario> comentarios;
    private boolean accesoCondicion;
    @Embedded
    private List<Semilla> semilla;
    public Fotocopiadora() {
        comentarios= new ArrayList<Comentario>();
        semilla= new ArrayList<Semilla>();
    }

    public Fotocopiadora(String nombre, Localizacion localizacion,
                         boolean autoServicio, Estado estado, List<Comentario> comentarios, boolean accesoCondicion, List<Semilla> semilla) {
        this.nombre = nombre;
        this.localizacion = localizacion;
        this.autoServicio = autoServicio;
        this.estado = estado;
        this.comentarios = comentarios;
        this.accesoCondicion = accesoCondicion;
        this.semilla = semilla;
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

    public boolean isAutoServicio() {
        return autoServicio;
    }

    public void setAutoServicio(boolean autoServicio) {
        this.autoServicio = autoServicio;
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

    public boolean isAccesoCondicion() {
        return accesoCondicion;
    }

    public void setAccesoCondicion(boolean accesoCondicion) {
        this.accesoCondicion = accesoCondicion;
    }

    public List<Semilla> getSemilla() {
        return semilla;
    }

    public void setSemilla(List<Semilla> semilla) {
        this.semilla = semilla;
    }

    @Override
    public String toString() {
        return "Fotocopiadora{" +
                "nombre='" + nombre + '\'' +
                ", localizacion=" + localizacion +
                ", autoServicio=" + autoServicio +
                ", estado=" + estado +
                ", comentarios=" + comentarios +
                ", accesoCondicion=" + accesoCondicion +
                ", semilla=" + semilla +
                '}';
    }
}
