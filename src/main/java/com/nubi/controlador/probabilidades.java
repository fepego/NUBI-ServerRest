package com.nubi.controlador;

/**
 * Created by Felipe on 21/09/2016.
 */
public class probabilidades {
    private double probLibre;
    private double probMedia;
    private double probLLeno;

    public probabilidades() {
    }

    public probabilidades(double probLibre, double probMedia, double probLLeno) {
        this.probLibre = probLibre;
        this.probMedia = probMedia;
        this.probLLeno = probLLeno;
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
