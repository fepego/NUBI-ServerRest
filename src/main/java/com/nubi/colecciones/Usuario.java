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
    private List<SitiosEstudio> favoritosEst= new ArrayList<SitiosEstudio>();

    public Usuario(String idUsuario, String password) {
        this.idUsuario = idUsuario;
        this.password = password;
        favoritosEst= new ArrayList<SitiosEstudio>();
    }

    public Usuario() {
    }

    public Usuario(String idUsuario, String carrera, String password,
                   Localizacion localizacion, Preferencia preferencias,
                   Restriccion restricciones, List<SitiosEstudio> favoritosEst) {
        this.idUsuario = idUsuario;
        this.carrera = carrera;
        this.password = password;
        this.localizacion = localizacion;
        this.preferencias = preferencias;
        this.restricciones = restricciones;
        this.favoritosEst = favoritosEst;
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

    public List<SitiosEstudio> getFavoritosEst() {
        return favoritosEst;
    }

    public void setFavoritosEst(List<SitiosEstudio> favoritosEst) {
        this.favoritosEst = favoritosEst;
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
                ", favoritosEst=" + favoritosEst +
                '}';
    }
}
