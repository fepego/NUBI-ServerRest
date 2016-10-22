package com.nubi.ModuloAdaptacion;

import com.nubi.IntegracionBD.ModeloNubi;
import com.nubi.IntegracionBD.ModeloNubiImp;
import com.nubi.colecciones.Usuario;

import java.util.List;

/**
 * Created by Felipe on 11/10/2016.
 */
public class Grupo {


    private Usuario grupo;
    private Usuario integrante;

    public Grupo() {
    }

    public Grupo(Usuario grupo) {
        this.grupo = grupo;
    }

    public Grupo(Usuario grupo, Usuario Integrante) {

        this.grupo = grupo;
        integrante= Integrante;
    }


    public Usuario getGrupo() {
        return grupo;
    }

    public void setGrupo(Usuario grupo) {
        this.grupo = grupo;
    }

    public Usuario getIntegrante() {
        return integrante;
    }

    public void setIntegrante(Usuario integrantes) {
        this.integrante = integrante;
    }

    @Override
    public String toString() {
        return "Grupo{" +
                ", grupo=" + grupo +
                ", integrantes=" + integrante +
                '}';
    }
}
