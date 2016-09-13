package com.nubi.colecciones;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

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

    public Fotocopiadora() {
    }

    public Fotocopiadora(String nombre, Localizacion localizacion, boolean autoServicio, Estado estado) {
        this.nombre = nombre;
        this.localizacion = localizacion;
        this.autoServicio = autoServicio;
        this.estado = estado;
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
}
