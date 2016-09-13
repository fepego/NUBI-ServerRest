package com.nubi.colecciones;

import org.mongodb.morphia.annotations.Entity;

/**
 * Created by Felipe on 23/08/2016.
 */
@Entity
public class Localizacion {

    private double latitud;
    private double longitud;

    public Localizacion() {
    }

    public Localizacion(double latitud, double longitud) {
        this.latitud = latitud;
        this.longitud = longitud;
    }

    @Override
    public String toString() {
        return "Localizacion{" +
                "latitud=" + latitud +
                ", longitud=" + longitud +
                '}';
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }
}
