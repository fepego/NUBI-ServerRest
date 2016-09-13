package com.nubi.colecciones;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.Indexes;

/**
 * Created by Felipe on 23/08/2016.
 */
@Entity
@Indexes({@Index(value="logInicio",expireAfterSeconds = 604800 ),
        @Index(value="latInicio")
})
public class Ruta {
    private double logInicio;
    private double latInicio;
    private String shape;

    public Ruta() {
    }

    public Ruta(double logInicio, double latInicio, String shape) {
        this.logInicio = logInicio;
        this.latInicio = latInicio;
        this.shape = shape;
    }

    public double getLogInicio() {
        return logInicio;
    }

    public void setLogInicio(double logInicio) {
        this.logInicio = logInicio;
    }

    public double getLatInicio() {
        return latInicio;
    }

    public void setLatInicio(double latInicio) {
        this.latInicio = latInicio;
    }

    public String getShape() {
        return shape;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }
}
