package com.nubi.controlador;

import com.mongodb.MongoClient;
import com.nubi.colecciones.*;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.converters.DoubleConverter;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.QueryResults;

/**
 * Created by Felipe on 23/08/2016.
 */
public class mainPersistencia {
    public static void main(String[] args) {
        MongoClient cli= new MongoClient("localhost",27017);
        Morphia mph= new Morphia();
        Datastore ds= mph.createDatastore(cli,"NUBI_DB");
        mph.map(Usuario.class);
        // Genera usuario de prueba
        Usuario usu= new Usuario();
        usu.setIdUsuario("pipe");
        usu.setCarrera("IngSistemas");
        usu.setPassword("abc1234");
        Preferencia prf= new Preferencia();
        prf.setDisponibilidad("Libre");
        prf.setDistancia(100);
        prf.setTiempoMaximo(15);
        prf.setTiempoMinimo(2);
        prf.setNotificaciones(true);
        Restriccion res= new Restriccion(false,"ninguno",true);
        usu.setLocalizacion(new Localizacion());
        usu.setPreferencias(prf);
        usu.setRestricciones(res);
        ds.save(usu);
        //Genera Restaurante de prueba
        Restaurante rest= new Restaurante();
        rest.setNombre("kiosko ing");
        rest.setLocalizacion(new Localizacion(4.575343,-74.65653));
        rest.setEstado(new Estado(0.1,100,10));
        ds.save(rest);
        //Recuperando datos
        Query <Usuario> qry= ds.createQuery(Usuario.class);
        qry.and(qry.criteria("idUsuario").equal("pipe"));
        QueryResults<Usuario> qrList=ds.find(qry.getEntityClass());
        for (Usuario u:qrList)
        {
            System.out.println(u.getPassword());
        }
    }
}
