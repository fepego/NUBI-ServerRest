package com.nubi.colecciones;

import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Felipe on 24/08/2016.
 */
@Entity
public class SitiosEstudio {
    @Id
    private String nombre;
    @Embedded
    private Localizacion localizacion;
    @Embedded
    private Estado estado;
    private String toleranciaRuido;
    @Embedded
    private Ruta ruta;
    @Embedded
    private List<Semilla> semilla;


    public SitiosEstudio() {
        semilla=new ArrayList<Semilla>();
    }

    public SitiosEstudio(String nombre, Localizacion localizacion, Estado estado,
                         String toleranciaRuido, Ruta ruta, List<Semilla> semilla) {
        this.nombre = nombre;
        this.localizacion = localizacion;
        this.estado = estado;
        this.toleranciaRuido = toleranciaRuido;
        this.ruta = ruta;
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

    public Ruta getRuta() {
        return ruta;
    }

    public void setRuta(Ruta ruta) {
        this.ruta = ruta;
    }

    public List<Semilla> getSemilla() {
        return semilla;
    }

    public void setSemilla(List<Semilla> semilla) {
        this.semilla = semilla;
    }

    @Override
    public String toString() {
        return "SitiosEstudio{" +
                "nombre='" + nombre + '\'' +
                ", localizacion=" + localizacion +
                ", estado=" + estado +
                ", toleranciaRuido='" + toleranciaRuido + '\'' +
                ", ruta=" + ruta +
                ", semilla=" + semilla +
                '}';
    }
}
