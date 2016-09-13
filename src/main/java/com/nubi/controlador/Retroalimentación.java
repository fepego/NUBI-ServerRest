package com.nubi.controlador;

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
