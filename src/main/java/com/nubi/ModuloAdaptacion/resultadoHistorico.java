package com.nubi.ModuloAdaptacion;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

/**
 * Created by Felipe on 20/09/2016.
 */
@Entity
public class resultadoHistorico {
    @Id
    private String nombreSitio;
    private double totalAlertasLibre;
    private double totalAlertasMedio;
    private double totalAlertasLLeno;

    public resultadoHistorico() {
    }

    public resultadoHistorico(String nombreSitio, double totalAlertasLibre, double totalAlertasMedio, double totalAlertasLLeno) {
        this.nombreSitio = nombreSitio;
        this.totalAlertasLibre = totalAlertasLibre;
        this.totalAlertasMedio = totalAlertasMedio;
        this.totalAlertasLLeno = totalAlertasLLeno;
    }

    public String getNombreSitio() {
        return nombreSitio;
    }

    public void setNombreSitio(String nombreSitio) {
        this.nombreSitio = nombreSitio;
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
                "nombreSitio='" + nombreSitio + '\'' +
                ", totalAlertasLibre=" + totalAlertasLibre +
                ", totalAlertasMedio=" + totalAlertasMedio +
                ", totalAlertasLLeno=" + totalAlertasLLeno +
                '}';
    }
}
