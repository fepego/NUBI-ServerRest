package com.nubi.ServicioREST;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by felipe on 8/09/16.
 */
@XmlRootElement
public class hola {
    private String cad;

    public hola() {
    }

    public hola(String cad) {
        this.cad = cad;
    }

    public String getCad() {
        return cad;
    }

    public void setCad(String cad) {
        this.cad = cad;
    }
}
