package com.nubi.IntegracionMapzen;


import com.nubi.IntegracionBD.ModeloNubi;
import com.nubi.IntegracionBD.ModeloNubiImp;
import com.nubi.Utils.JsonReader;
import com.nubi.colecciones.Ruta;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Felipe on 22/09/2016.
 */
public class Mapzen {

    public static Ruta ObtenerMapa(double latInicio, double longInicio, double latDestino, double longDestino)
    {
        ModeloNubi mod= new ModeloNubiImp();
        Iterator<Ruta> rutas = mod.buscarRuta(latInicio,longInicio,latDestino,longDestino);
        if(rutas != null)
        {
            System.out.println("encontre ruta");
            return rutas.next();
        }
        else
        {
            try {
                JSONObject json = JsonReader.readJsonFromUrl("http://valhalla.mapzen.com/route?json={\"locations\":[{\"lat\":"+latInicio+",\"lon\":"+longInicio+"},{\"lat\":"+latDestino+",\"lon\":"+longDestino+"}],\"costing\":\"pedestrian\",\"directions_options\":{\"units\":\"meters\"}}&id=my_work_route&api_key=valhalla-UDVJPyv");
                JSONObject trip= json.getJSONObject("trip");
                JSONObject legs= trip.getJSONArray("legs").getJSONObject(0);
                System.out.println(legs.get("shape").toString());
                System.out.println(trip.getJSONObject("summary").get("length").toString());
                double distancia= Double.parseDouble(trip.getJSONObject("summary").get("length").toString())*1000;
                Ruta ruta= new Ruta(longInicio,latInicio,latDestino,longDestino,distancia,legs.get("shape").toString());
                mod.InsertarRuta(ruta);
                return ruta;



            } catch (IOException ex) {
                Logger.getLogger(Ruta.class.getName()).log(Level.SEVERE, null, ex);
            } catch (JSONException ex) {
                Logger.getLogger(Ruta.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return null;
    }
}
