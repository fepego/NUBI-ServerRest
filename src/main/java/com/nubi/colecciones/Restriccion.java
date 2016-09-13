package com.nubi.colecciones;

import org.mongodb.morphia.annotations.Entity;

/**
 * Created by Felipe on 23/08/2016.
 */
@Entity
public class Restriccion {
    private boolean movilidad;
    private String gradoMovilidad;
    private boolean privacidad;

    public Restriccion() {
    }

    public Restriccion(boolean movilidad, String gradoMovilidad, boolean privacidad) {
        this.movilidad = movilidad;
        this.gradoMovilidad = gradoMovilidad;
        this.privacidad = privacidad;
    }

    public boolean isMovilidad() {
        return movilidad;
    }

    public void setMovilidad(boolean movilidad) {
        this.movilidad = movilidad;
    }

    public String getGradoMovilidad() {
        return gradoMovilidad;
    }

    public void setGradoMovilidad(String gradoMovilidad) {
        this.gradoMovilidad = gradoMovilidad;
    }

    public boolean isPrivacidad() {
        return privacidad;
    }

    public void setPrivacidad(boolean privacidad) {
        this.privacidad = privacidad;
    }
}
