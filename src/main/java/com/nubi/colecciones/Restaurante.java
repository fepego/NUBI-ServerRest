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
    private boolean accesoCondicion;
    @Embedded
    private List<Semilla> semilla;


    public Restaurante() {
        comentarios= new ArrayList<Comentario>();
        semilla= new ArrayList<Semilla>();

    }

    public Restaurante(String nombre, Localizacion localizacion, Estado estado, List<Comentario> comentarios,
                       boolean accesoCondicion, List<Semilla> semilla) {
        this.nombre = nombre;
        this.localizacion = localizacion;
        this.estado = estado;
        this.comentarios = comentarios;
        this.accesoCondicion = accesoCondicion;
        this.semilla = semilla;
    }

    public Restaurante(String nombre, Localizacion localizacion, Estado estado,
                       List<Comentario> comentarios, boolean accesoCondicion, List<Semilla> semilla, boolean accesoCondicion1) {
        this.nombre = nombre;
        this.localizacion = localizacion;
        this.estado = estado;
        this.comentarios = comentarios;
        this.accesoCondicion = accesoCondicion;
        this.semilla = semilla;
        this.accesoCondicion = accesoCondicion1;
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
        return "Restaurante{" +
                "nombre='" + nombre + '\'' +
                ", localizacion=" + localizacion +
                ", estado=" + estado +
                ", comentarios=" + comentarios +
                ", accesoCondicion=" + accesoCondicion +
                ", semilla=" + semilla +
                '}';
    }
}
