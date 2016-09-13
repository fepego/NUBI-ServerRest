package com.nubi.colecciones;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Indexed;

/**
 * Created by Felipe on 23/08/2016.
 */
@Entity
public class Estado {
    private double disponibilidad;
    private int numMaxUsuarios;
    private int numActualesUsuarios;

    public Estado() {
    }

    public Estado(double disponibilidad, int numMaxUsuarios, int numActualesUsuarios) {
        this.disponibilidad = disponibilidad;
        this.numMaxUsuarios = numMaxUsuarios;
        this.numActualesUsuarios = numActualesUsuarios;
    }

    public double getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(double disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public int getNumMaxUsuarios() {
        return numMaxUsuarios;
    }

    public void setNumMaxUsuarios(int numMaxUsuarios) {
        this.numMaxUsuarios = numMaxUsuarios;
    }

    public int getNumActualesUsuarios() {
        return numActualesUsuarios;
    }

    public void setNumActualesUsuarios(int numActualesUsuarios) {
        this.numActualesUsuarios = numActualesUsuarios;
    }
}
