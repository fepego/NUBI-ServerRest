package com.nubi.colecciones;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.Indexes;

/**
 * Created by Felipe on 23/08/2016.
 */
@Entity
@Indexes({@Index(value="longInicio",expireAfterSeconds = 604800 ),
        @Index(value="latInicio",expireAfterSeconds = 604800),
        @Index(value="latDestino",expireAfterSeconds = 604800),
        @Index(value = "longDestino",expireAfterSeconds = 604800)
})
public class Ruta {

    private double longInicio;
    private double latInicio;
    private double latDestino;
    private double longDestino;
    private double distancia;
    private String shape;

    public Ruta() {
    }

    public Ruta(double longInicio, double latInicio, double latDestino, double longDestino, double distancia, String shape) {
        this.longInicio = longInicio;
        this.latInicio = latInicio;
        this.latDestino = latDestino;
        this.longDestino = longDestino;
        this.distancia = distancia;
        this.shape = shape;
    }

    public double getLongInicio() {
        return longInicio;
    }

    public void setLongInicio(double longInicio) {
        this.longInicio = longInicio;
    }

    public double getLatInicio() {
        return latInicio;
    }

    public void setLatInicio(double latInicio) {
        this.latInicio = latInicio;
    }

    public double getLatDestino() {
        return latDestino;
    }

    public void setLatDestino(double latDestino) {
        this.latDestino = latDestino;
    }

    public double getLongDestino() {
        return longDestino;
    }

    public void setLongDestino(double longDestino) {
        this.longDestino = longDestino;
    }

    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }

    public String getShape() {
        return shape;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }

    @Override
    public String toString() {
        return "Ruta{" +
                "longInicio=" + longInicio +
                ", latInicio=" + latInicio +
                ", latDestino=" + latDestino +
                ", longDestino=" + longDestino +
                ", distancia=" + distancia +
                ", shape='" + shape + '\'' +
                '}';
    }
}
