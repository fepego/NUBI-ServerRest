package com.nubi.controlador;

import com.nubi.colecciones.Fotocopiadora;
import com.nubi.colecciones.Restaurante;
import com.nubi.colecciones.SitiosEstudio;

/**
 * Created by Felipe on 20/09/2016.
 */
public class resultadoHistorico {
    private Fotocopiadora fotocopiadora;
    private SitiosEstudio sitiosEstudio;
    private Restaurante restaurante;
    private double totalAlertasLibre;
    private double totalAlertasMedio;
    private double totalAlertasLLeno;

    public resultadoHistorico() {
    }

    public resultadoHistorico(Fotocopiadora fotocopiadora, SitiosEstudio sitiosEstudio,
                              Restaurante restaurante, double totalAlertasLibre, double totalAlertasMedio,
                              double totalAlertasLLeno) {
        this.fotocopiadora = fotocopiadora;
        this.sitiosEstudio = sitiosEstudio;
        this.restaurante = restaurante;
        this.totalAlertasLibre = totalAlertasLibre;
        this.totalAlertasMedio = totalAlertasMedio;
        this.totalAlertasLLeno = totalAlertasLLeno;
    }

    public Fotocopiadora getFotocopiadora() {
        return fotocopiadora;
    }

    public void setFotocopiadora(Fotocopiadora fotocopiadora) {
        this.fotocopiadora = fotocopiadora;
    }

    public SitiosEstudio getSitiosEstudio() {
        return sitiosEstudio;
    }

    public void setSitiosEstudio(SitiosEstudio sitiosEstudio) {
        this.sitiosEstudio = sitiosEstudio;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }

    public double getTotalAlertasLibre() {
        return totalAlertasLibre;
    }

    public void setTotalAlertasLibre(double totalAlertasLibre) {
        this.totalAlertasLibre = totalAlertasLibre;
    }

    public double getTotalAlertasMedio() {
        return totalAlertasMedio;
    }

    public void setTotalAlertasMedio(double totalAlertasMedio) {
        this.totalAlertasMedio = totalAlertasMedio;
    }

    public double getTotalAlertasLLeno() {
        return totalAlertasLLeno;
    }

    public void setTotalAlertasLLeno(double totalAlertasLLeno) {
        this.totalAlertasLLeno = totalAlertasLLeno;
    }

    @Override
    public String toString() {
        return "resultadoHistorico{" +
                "fotocopiadora=" + fotocopiadora +
                ", sitiosEstudio=" + sitiosEstudio +
                ", restaurante=" + restaurante +
                ", totalAlertasLibre=" + totalAlertasLibre +
                ", totalAlertasMedio=" + totalAlertasMedio +
                ", totalAlertasLLeno=" + totalAlertasLLeno +
                '}';
    }
}
