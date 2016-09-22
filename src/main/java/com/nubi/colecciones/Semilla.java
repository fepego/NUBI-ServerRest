package com.nubi.colecciones;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Indexed;

/**
 * Created by felipe on 13/09/16.
 */
@Entity
public class Semilla {
    @Indexed
    private long horaInicio;
    @Indexed
    private long horaFin;
    private double probLibre;
    private double probMedia;
    private double probAlta;
    private String tipoDia;
    private String dia;


    public Semilla() {
    }

    public Semilla(long horaInicio, long horaFin, double probLibre,
                   double probMedia, double probAlta, String tipoDia, String dia) {
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.probLibre = probLibre;
        this.probMedia = probMedia;
        this.probAlta = probAlta;
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

    public double getProbLibre() {
        return probLibre;
    }

    public void setProbLibre(double probLibre) {
        this.probLibre = probLibre;
    }

    public double getProbMedia() {
        return probMedia;
    }

    public void setProbMedia(double probMedia) {
        this.probMedia = probMedia;
    }

    public double getProbAlta() {
        return probAlta;
    }

    public void setProbAlta(double probAlta) {
        this.probAlta = probAlta;
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
                ", probLibre=" + probLibre +
                ", probMedia=" + probMedia +
                ", probAlta=" + probAlta +
                ", tipoDia='" + tipoDia + '\'' +
                ", dia='" + dia + '\'' +
                '}';
    }
}
