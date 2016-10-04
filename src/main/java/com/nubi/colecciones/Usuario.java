package com.nubi.colecciones;


import org.mongodb.morphia.annotations.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Felipe on 22/08/2016.
 */
@Entity
public class Usuario {
    @Id
    @Indexed
    private String idUsuario;
    private String carrera;
    private String password;
    @Embedded
    private Localizacion localizacion;
    @Embedded
    private Preferencia preferencias;
    @Embedded
    private Restriccion restricciones;
    @Reference
    private List<SitiosEstudio> favoritosEstudio;
    @Reference
    private List<Restaurante> favoritosRestaurantes;
    @Reference
    private List<Fotocopiadora> favoritosFotocopiadoras;
    @Reference
    private List<Usuario> listaContactos;

    public Usuario(String idUsuario, String password) {
        this.idUsuario = idUsuario;
        this.password = password;
        favoritosEstudio= new ArrayList<SitiosEstudio>();
        favoritosFotocopiadoras= new ArrayList<Fotocopiadora>();
        favoritosRestaurantes= new ArrayList<Restaurante>();
        listaContactos= new ArrayList<Usuario>();
    }

    public Usuario() {
        favoritosEstudio= new ArrayList<SitiosEstudio>();
        favoritosFotocopiadoras= new ArrayList<Fotocopiadora>();
        favoritosRestaurantes= new ArrayList<Restaurante>();
        listaContactos= new ArrayList<Usuario>();

    }

    public Usuario(String idUsuario, String carrera, String password,
                   Localizacion localizacion, Preferencia preferencias, Restriccion restricciones,
                   List<SitiosEstudio> favoritosEstudio, List<Restaurante> favoritosRestaurantes,
                   List<Fotocopiadora> favoritosFotocopiadoras, List<Usuario> listaContactos) {
        this.idUsuario = idUsuario;
        this.carrera = carrera;
        this.password = password;
        this.localizacion = localizacion;
        this.preferencias = preferencias;
        this.restricciones = restricciones;
        this.favoritosEstudio = favoritosEstudio;
        this.favoritosRestaurantes = favoritosRestaurantes;
        this.favoritosFotocopiadoras = favoritosFotocopiadoras;
        this.listaContactos = listaContactos;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Localizacion getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(Localizacion localizacion) {
        this.localizacion = localizacion;
    }

    public Preferencia getPreferencias() {
        return preferencias;
    }

    public void setPreferencias(Preferencia preferencias) {
        this.preferencias = preferencias;
    }

    public Restriccion getRestricciones() {
        return restricciones;
    }

    public void setRestricciones(Restriccion restricciones) {
        this.restricciones = restricciones;
    }

    public List<SitiosEstudio> getFavoritosEstudio() {
        return favoritosEstudio;
    }

    public void setFavoritosEstudio(List<SitiosEstudio> favoritosEstudio) {
        this.favoritosEstudio = favoritosEstudio;
    }

    public List<Restaurante> getFavoritosRestaurantes() {
        return favoritosRestaurantes;
    }

    public void setFavoritosRestaurantes(List<Restaurante> favoritosRestaurantes) {
        this.favoritosRestaurantes = favoritosRestaurantes;
    }

    public List<Fotocopiadora> getFavoritosFotocopiadoras() {
        return favoritosFotocopiadoras;
    }

    public void setFavoritosFotocopiadoras(List<Fotocopiadora> favoritosFotocopiadoras) {
        this.favoritosFotocopiadoras = favoritosFotocopiadoras;
    }

    public List<Usuario> getListaContactos() {
        return listaContactos;
    }

    public void setListaContactos(List<Usuario> listaContactos) {
        this.listaContactos = listaContactos;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario='" + idUsuario + '\'' +
                ", carrera='" + carrera + '\'' +
                ", password='" + password + '\'' +
                ", localizacion=" + localizacion +
                ", preferencias=" + preferencias +
                ", restricciones=" + restricciones +
                ", favoritosEstudio=" + favoritosEstudio +
                ", favoritosRestaurantes=" + favoritosRestaurantes +
                ", favoritosFotocopiadoras=" + favoritosFotocopiadoras +
                ", listaContactos=" + listaContactos +
                '}';
    }
}
