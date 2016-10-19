package com.nubi.ModuloAdaptacion;

/**
 * Created by Felipe on 21/09/2016.
 */
public class probabilidades {
    private String nombreSitio;
    private double probLibre;
    private double probMedia;
    private double probLLeno;

    public probabilidades() {
    }

    public probabilidades(String nombreSitio, double probLibre, double probMedia, double probLLeno) {
        this.nombreSitio = nombreSitio;
        this.probLibre = probLibre;
        this.probMedia = probMedia;
        this.probLLeno = probLLeno;
    }

    public String getNombreSitio() {
        return nombreSitio;
    }

    public void setNombreSitio(String nombreSitio) {
        this.nombreSitio = nombreSitio;
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

    public double getProbLLeno() {
        return probLLeno;
    }

    public void setProbLLeno(double probLLeno) {
        this.probLLeno = probLLeno;
    }
}
