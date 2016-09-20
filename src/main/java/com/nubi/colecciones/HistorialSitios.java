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
public class HistorialSitios {
    @Indexed(expireAfterSeconds = 691200)
    @Id
    private Date horaConsulta;
    private String diaRegistro;
    private double numAlertasLibre;
    private double numAlertasMedia;
    private double numAlertasLleno;
    private double disponibilidad;
    @Reference
    private SitiosEstudio sitiosEstudio;


    public HistorialSitios() {
    }

    public HistorialSitios(Date horaConsulta, String diaRegistro, double numAlertasLibre,
                           double numAlertasMedia, double numAlertasLleno, double disponibilidad,
                           SitiosEstudio sitiosEstudio) {
        this.horaConsulta = horaConsulta;
        this.diaRegistro = diaRegistro;
        this.numAlertasLibre = numAlertasLibre;
        this.numAlertasMedia = numAlertasMedia;
        this.numAlertasLleno = numAlertasLleno;
        this.disponibilidad = disponibilidad;
        this.sitiosEstudio = sitiosEstudio;
    }

    public Date getHoraConsulta() {
        return horaConsulta;
    }

    public void setHoraConsulta(Date horaConsulta) {
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

    public double getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(double disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public SitiosEstudio getSitiosEstudio() {
        return sitiosEstudio;
    }

    public void setSitiosEstudio(SitiosEstudio sitiosEstudio) {
        this.sitiosEstudio = sitiosEstudio;
    }
}
