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
    private long horaInicio;
    @Indexed
    private long horaFin;
    private double disponibilidad;
    private String tipoDia;
    private String dia;


    public Semilla() {
    }

    public Semilla(long horaInicio, long horaFin, double disponibilidad, String tipoDia, String dia) {
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.disponibilidad = disponibilidad;
        this.tipoDia = tipoDia;
        this.dia = dia;
    }

    public long getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(long horaInicio) {
        this.horaInicio = horaInicio;
    }

    public long getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(long horaFin) {
        this.horaFin = horaFin;
    }

    public double getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(double disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public String getTipoDia() {
        return tipoDia;
    }

    public void setTipoDia(String tipoDia) {
        this.tipoDia = tipoDia;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    @Override
    public String toString() {
        return "Semilla{" +
                "horaInicio=" + horaInicio +
                ", horaFin=" + horaFin +
                ", disponibilidad=" + disponibilidad +
                ", tipoDia='" + tipoDia + '\'' +
                ", dia='" + dia + '\'' +
                '}';
    }
}
