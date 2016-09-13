package com.nubi.controlador;

import com.nubi.colecciones.Fotocopiadora;
import com.nubi.colecciones.Restaurante;
import com.nubi.colecciones.SitiosEstudio;
import com.nubi.colecciones.Usuario;

import java.util.List;

/**
 * Created by Felipe on 23/08/2016.
 */
public class Candidato {
    private Restaurante restaurante;
    private SitiosEstudio sitio;
    private Fotocopiadora fotocopiadora;
    private Usuario usuario;
    private double distancia;
    private double puntaje;
    private boolean favorito;

    public Candidato() {
    }

    public Candidato(Restaurante restaurante, SitiosEstudio sitio, Fotocopiadora fotocopiadora,
                     Usuario usuario, double distancia, double puntaje, boolean favorito) {
        this.restaurante = restaurante;
        this.sitio = sitio;
        this.fotocopiadora = fotocopiadora;
        this.usuario = usuario;
        this.distancia = distancia;
        this.puntaje = puntaje;
        this.favorito = favorito;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(Restaurante restaurantes) {
        this.restaurante = restaurantes;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }

    public double getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(double puntaje) {
        this.puntaje = puntaje;
    }

    public SitiosEstudio getSitio() {
        return sitio;
    }

    public void setSitio(SitiosEstudio sitio) {
        this.sitio = sitio;
    }

    public boolean isFavorito() {
        return favorito;
    }

    public void setFavorito(boolean favorito) {
        this.favorito = favorito;
    }

    public Fotocopiadora getFotocopiadora() {
        return fotocopiadora;
    }

    public void setFotocopiadora(Fotocopiadora fotocopiadora) {
        this.fotocopiadora = fotocopiadora;
    }
}
