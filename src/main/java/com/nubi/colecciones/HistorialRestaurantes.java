package com.nubi.colecciones;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Indexed;
import org.mongodb.morphia.annotations.Reference;

import java.util.Date;

/**
 * Created by felipe on 13/09/16.
 */
@Entity
public class HistorialRestaurantes {
    @Indexed(expireAfterSeconds = 691200)
    @Id
    private long horaConsulta;
    private String diaRegistro;
    private double numAlertasLibre;
    private double numAlertasMedia;
    private double numAlertasLleno;
    @Reference
    private Restaurante restaurante;


    public HistorialRestaurantes() {
    }

    public HistorialRestaurantes(long horaConsulta, String diaRegistro, double numAlertasLibre,
                                 double numAlertasMedia, double numAlertasLleno, Restaurante restaurante) {
        this.horaConsulta = horaConsulta;
        this.diaRegistro = diaRegistro;
        this.numAlertasLibre = numAlertasLibre;
        this.numAlertasMedia = numAlertasMedia;
        this.numAlertasLleno = numAlertasLleno;
        this.restaurante = restaurante;
    }

    public long getHoraConsulta() {
        return horaConsulta;
    }

    public void setHoraConsulta(long horaConsulta) {
        this.horaConsulta = horaConsulta;
    }

    public String getDiaRegistro() {
        return diaRegistro;
    }

    public void setDiaRegistro(String diaRegistro) {
        this.diaRegistro = diaRegistro;
    }

    public double getNumAlertasLibre() {
        return numAlertasLibre;
    }

    public void setNumAlertasLibre(double numAlertasLibre) {
        this.numAlertasLibre = numAlertasLibre;
    }

    public double getNumAlertasMedia() {
        return numAlertasMedia;
    }

    public void setNumAlertasMedia(double numAlertasMedia) {
        this.numAlertasMedia = numAlertasMedia;
    }

    public double getNumAlertasLleno() {
        return numAlertasLleno;
    }

    public void setNumAlertasLleno(double numAlertasLleno) {
        this.numAlertasLleno = numAlertasLleno;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }
}
