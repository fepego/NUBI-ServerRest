package com.nubi.colecciones;


import org.mongodb.morphia.annotations.*;

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
    public Usuario() {
    }

    public Usuario(String idUsuario, String carrera, String password, Localizacion localizacion, Preferencia preferencias, Restriccion restricciones) {
        this.idUsuario = idUsuario;
        this.carrera = carrera;
        this.password = password;
        this.localizacion = localizacion;
        this.preferencias = preferencias;
        this.restricciones = restricciones;
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

}
