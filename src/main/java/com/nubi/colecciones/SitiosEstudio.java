package com.nubi.colecciones;

import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Indexed;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Felipe on 24/08/2016.
 */
@Entity
public class SitiosEstudio {
    @Id
    @Indexed
    private String nombre;
    @Embedded
    private Localizacion localizacion;
    @Embedded
    private Estado estado;
    private String toleranciaRuido;
    @Embedded
    private List<Comentario> comentarios;
    @Embedded
    private List<Semilla> semilla;
    private boolean accesoCondicion;
    private String tipoEspacio;



    public SitiosEstudio() {
        semilla=new ArrayList<Semilla>();
    }

    public SitiosEstudio(String nombre, Localizacion localizacion, Estado estado,
                         String toleranciaRuido, List<Comentario> comentarios, List<Semilla> semilla) {
        this.nombre = nombre;
        this.localizacion = localizacion;
        this.estado = estado;
        this.toleranciaRuido = toleranciaRuido;
        this.comentarios = comentarios;
        this.semilla = semilla;
    }

    public SitiosEstudio(String nombre, Localizacion localizacion, Estado estado, String toleranciaRuido, List<Comentario> comentarios, List<Semilla> semilla, boolean accesoCondicion) {
        this.nombre = nombre;
        this.localizacion = localizacion;
        this.estado = estado;
        this.toleranciaRuido = toleranciaRuido;
        this.comentarios = comentarios;
        this.semilla = semilla;
        this.accesoCondicion = accesoCondicion;
    }

    public SitiosEstudio(String nombre, Localizacion localizacion, Estado estado, String toleranciaRuido, List<Comentario> comentarios, List<Semilla> semilla, boolean accesoCondicion, String tipoEspacio) {
        this.nombre = nombre;
        this.localizacion = localizacion;
        this.estado = estado;
        this.toleranciaRuido = toleranciaRuido;
        this.comentarios = comentarios;
        this.semilla = semilla;
        this.accesoCondicion = accesoCondicion;
        this.tipoEspacio = tipoEspacio;
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

    public String getToleranciaRuido() {
        return toleranciaRuido;
    }

    public void setToleranciaRuido(String toleranciaRuido) {
        this.toleranciaRuido = toleranciaRuido;
    }

    public List<Semilla> getSemilla() {
        return semilla;
    }

    public void setSemilla(List<Semilla> semilla) {
        this.semilla = semilla;
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

    public String getTipoEspacio() {
        return tipoEspacio;
    }

    public void setTipoEspacio(String tipoEspacio) {
        this.tipoEspacio = tipoEspacio;
    }

    @Override
    public String toString() {
        return "SitiosEstudio{" +
                "nombre='" + nombre + '\'' +
                ", localizacion=" + localizacion +
                ", estado=" + estado +
                ", toleranciaRuido='" + toleranciaRuido + '\'' +
                ", comentarios=" + comentarios +
                ", semilla=" + semilla +
                ", accesoCondicion=" + accesoCondicion +
                ", tipoEspacio='" + tipoEspacio + '\'' +
                '}';
    }
}
