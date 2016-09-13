package com.nubi.controlador;

/**
 * Created by Felipe on 18/08/2016.
 */
public class Sitio {
    private float Disponibilidad;
    private int numUsuarios;
    private String ToleranciaRuido;
    private String tipoEspacio;

    public Sitio() {
    }

    public Sitio(float disponibilidad, int numUsuarios, String toleranciaRuido, String tipoEspacio) {
        Disponibilidad = disponibilidad;
        this.numUsuarios = numUsuarios;
        ToleranciaRuido = toleranciaRuido;
        this.tipoEspacio = tipoEspacio;
    }

    public float getDisponibilidad() {
        return Disponibilidad;
    }

    public void setDisponibilidad(float disponibilidad) {
        Disponibilidad = disponibilidad;
    }

    public int getNumUsuarios() {
        return numUsuarios;
    }

    public void setNumUsuarios(int numUsuarios) {
        this.numUsuarios = numUsuarios;
    }

    public String getToleranciaRuido() {
        return ToleranciaRuido;
    }

    public void setToleranciaRuido(String toleranciaRuido) {
        ToleranciaRuido = toleranciaRuido;
    }

    public String getTipoEspacio() {
        return tipoEspacio;
    }

    public void setTipoEspacio(String tipoEspacio) {
        this.tipoEspacio = tipoEspacio;
    }
}
