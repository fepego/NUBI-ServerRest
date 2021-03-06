package com.nubi.colecciones;



import com.fasterxml.jackson.annotation.JsonBackReference;
import org.mongodb.morphia.annotations.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
    private String tipoUsuario;
    private String administrador;
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
    @Reference(idOnly = true)
    private List<String> listaContactos;
    private List<String> grupos;
    private int puntaje;

    public Usuario(String idUsuario, String password) {
        this.idUsuario = idUsuario;
        this.password = password;
        this.tipoUsuario="usuario";
        favoritosEstudio= new ArrayList<SitiosEstudio>();
        favoritosFotocopiadoras= new ArrayList<Fotocopiadora>();
        favoritosRestaurantes= new ArrayList<Restaurante>();
        listaContactos= new ArrayList<String>();
        localizacion=new Localizacion();
        grupos= new ArrayList<String>();
        puntaje=0;

    }

    public Usuario() {
        localizacion= new Localizacion();
        favoritosEstudio= new ArrayList<SitiosEstudio>();
        favoritosFotocopiadoras= new ArrayList<Fotocopiadora>();
        favoritosRestaurantes= new ArrayList<Restaurante>();
        listaContactos= new ArrayList<String>();
        grupos= new ArrayList<String>();
        puntaje=0;
    }


    public Usuario(String idUsuario, String password, String tipoUsuario) {
        this.idUsuario = idUsuario;
        this.password = password;
        this.tipoUsuario = tipoUsuario;
        localizacion= new Localizacion();
        favoritosEstudio= new ArrayList<SitiosEstudio>();
        favoritosFotocopiadoras= new ArrayList<Fotocopiadora>();
        favoritosRestaurantes= new ArrayList<Restaurante>();
        listaContactos= new ArrayList<String>();
        puntaje=0;
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

    public List<String> getListaContactos() {
        return listaContactos;
    }

    public void setListaContactos(List<String> listaContactos) {
        this.listaContactos = listaContactos;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getAdministrador() {
        return administrador;
    }

    public void setAdministrador(String administrador) {
        this.administrador = administrador;
    }

    public List<String> getGrupos() {
        return grupos;
    }

    public void setGrupos(List<String> grupos) {
        this.grupos = grupos;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario='" + idUsuario + '\'' +
                ", carrera='" + carrera + '\'' +
                ", password='" + password + '\'' +
                ", tipoUsuario='" + tipoUsuario + '\'' +
                ", administrador='" + administrador + '\'' +
                ", localizacion=" + localizacion +
                ", preferencias=" + preferencias +
                ", restricciones=" + restricciones +
                ", favoritosEstudio=" + favoritosEstudio +
                ", favoritosRestaurantes=" + favoritosRestaurantes +
                ", favoritosFotocopiadoras=" + favoritosFotocopiadoras +
                ", listaContactos=" + listaContactos +
                ", grupos=" + grupos +
                ", puntaje=" + puntaje +
                '}';
    }
}
