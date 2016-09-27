package com.nubi.colecciones;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Indexed;
import org.mongodb.morphia.annotations.Reference;

/**
 * Created by felipe on 13/09/16.
 */
@Entity
public class HistorialSitios {
    @Indexed(expireAfterSeconds = 691200)
    @Id
    private long horaConsulta;
    private String dia;
    private double numAlertasLibre;
    private double numAlertasMedia;
    private double numAlertasLleno;
    @Reference
    private SitiosEstudio sitiosEstudio;


    public HistorialSitios() {
    }

    public HistorialSitios(long horaConsulta, String dia,
                           double numAlertasLibre, double numAlertasMedia, double numAlertasLleno,
                            SitiosEstudio sitiosEstudio) {
        this.horaConsulta = horaConsulta;
        this.dia = dia;
        this.numAlertasLibre = numAlertasLibre;
        this.numAlertasMedia = numAlertasMedia;
        this.numAlertasLleno = numAlertasLleno;
        this.sitiosEstudio = sitiosEstudio;
    }

    public long getHoraConsulta() {
        return horaConsulta;
    }

    public void setHoraConsulta(long horaConsulta) {
        this.horaConsulta = horaConsulta;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
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


    public SitiosEstudio getSitiosEstudio() {
        return sitiosEstudio;
    }

    public void setSitiosEstudio(SitiosEstudio sitiosEstudio) {
        this.sitiosEstudio = sitiosEstudio;
    }

    @Override
    public String toString() {
        return "HistorialSitios{" +
                "horaConsulta=" + horaConsulta +
                ", dia='" + dia + '\'' +
                ", numAlertasLibre=" + numAlertasLibre +
                ", numAlertasMedia=" + numAlertasMedia +
                ", numAlertasLleno=" + numAlertasLleno +
                ", sitiosEstudio=" + sitiosEstudio +
                '}';
    }
}
