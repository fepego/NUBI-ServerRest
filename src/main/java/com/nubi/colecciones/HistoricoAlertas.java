package com.nubi.colecciones;

import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Indexed;

import java.util.Date;

/**
 * Created by Felipe on 28/10/2016.
 */
@Entity
public class HistoricoAlertas {
    @Id
    private String id;
    @Embedded
    @Indexed
    private Alerta alerta;

    public HistoricoAlertas() {
    }

    public HistoricoAlertas( Alerta alerta) {

        this.alerta = alerta;
    }

    public HistoricoAlertas(String id, Alerta alerta) {
        this.id = id;
        this.alerta = alerta;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public Alerta getAlerta() {
        return alerta;
    }

    public void setAlerta(Alerta alerta) {
        this.alerta = alerta;
    }

    @Override
    public String toString() {
        return "HistoricoAlertas{" +
                "id='" + id + '\'' +
                ", alerta=" + alerta +
                '}';
    }
}
