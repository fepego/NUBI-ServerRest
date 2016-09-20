package com.nubi.controlador;

import com.mongodb.MongoClient;
import com.nubi.Utils.Calculador;
import com.nubi.colecciones.Restaurante;
import com.nubi.colecciones.SitiosEstudio;
import com.nubi.colecciones.Usuario;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;

import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by felipe on 13/09/16.
 */
public class Retroalimentación {

    public static void recalcularDisponibilidad()
    {
        // Clase en la que está el código a ejecutar
        TimerTask timerTask = new TimerTask() {
            int i=0;
            List<Restaurante> qrList;
            List <Usuario> usu;
            List <SitiosEstudio> sts;
            MongoClient cli= new MongoClient("localhost",27017);
            Morphia mph= new Morphia();
            Datastore ds= mph.createDatastore(cli,"NUBI");
            public void run() {

                // Aquí el código que queremos ejecutar.
                System.out.println("Hola mundo "+i);
                i++;
            }

        };

        // Aquí se pone en marcha el timer cada segundo.
        Timer timer = new Timer();
        // Dentro de 0 milisegundos avísame cada 1000 milisegundos
        timer.schedule(timerTask,10,10000);
    }



}
