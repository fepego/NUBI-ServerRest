package com.nubi.colecciones;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Indexed;

import java.util.Date;

/**
 * Created by felipe on 13/09/16.
 */
@Entity
public class Semilla {
    @Indexed
    private Date horaInicio;
    @Indexed
    private Date horaFin;
    private double disponibilidad;


    public Semilla() {
    }

    public Semilla(Date horaInicio, Date horaFin, double disponibilidad) {
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.disponibilidad = disponibilidad;
    }

    public Date getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Date getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(Date horaFin) {
        this.horaFin = horaFin;
    }

    public double getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(double disponibilidad) {
        this.disponibilidad = disponibilidad;
    }
}
