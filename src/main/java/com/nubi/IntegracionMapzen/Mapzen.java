package com.nubi.IntegracionMapzen;


import com.nubi.IntegracionBD.ModeloNubi;
import com.nubi.IntegracionBD.ModeloNubiImp;
import com.nubi.Utils.JsonReader;
import com.nubi.colecciones.Ruta;
import com.sun.org.apache.xml.internal.resolver.helpers.Debug;
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

    public static Ruta balanceador(double latInicio, double longInicio, double latDestino, double longDestino)
    {
        ModeloNubi mod= new ModeloNubiImp();
        Iterator<Ruta> rutas = mod.buscarRuta(latInicio,longInicio,latDestino,longDestino);
        if(rutas!=null && rutas.hasNext())
        {
            System.out.println("entro lista");
            Ruta rut=rutas.next();
            System.out.println(rut);
            return rut;
        }
        JSONObject json=null;
        String [] claves=  new String [4];
        claves[0]=new String("valhalla-UDVJPyv");
        claves[1]=new String("mapzen-r1iKHjZ");
        claves[2]=new String("mapzen-iSBxybs");
        claves[3]= new String("mapzen-4TS2xeS");
        int i=0;
        while(json==null)
        {
            json=obtenerMapa(claves[i%4],latInicio,longInicio,latDestino, longDestino);
            i++;
        }
        JSONObject trip= json.getJSONObject("trip");
        JSONObject legs= trip.getJSONArray("legs").getJSONObject(0);
        System.out.println(legs.get("shape").toString());
        System.out.println(trip.getJSONObject("summary").get("length").toString());
        double distancia= Double.parseDouble(trip.getJSONObject("summary").get("length").toString())*1000;
        Ruta ruta= new Ruta(longInicio,latInicio,latDestino,longDestino,distancia,legs.get("shape").toString());
        mod.InsertarRuta(ruta);
        return ruta;
    }

    public static JSONObject obtenerMapa(String idMapzen,double latInicio, double longInicio, double latDestino, double longDestino)
    {

            try {
                JSONObject json = JsonReader.readJsonFromUrl("http://valhalla.mapzen.com/route?json={\"locations\":[{\"lat\":"+latInicio+",\"lon\":"+longInicio+"},{\"lat\":"+latDestino+",\"lon\":"+longDestino+"}],\"costing\":\"pedestrian\",\"directions_options\":{\"units\":\"meters\"}}&id=my_work_route&api_key="+idMapzen);

                return json;
            } catch (IOException ex) {
                System.out.println("error de consulta con"+idMapzen +" usando otra cuenta");
                return null;
            } catch (JSONException ex) {
                System.out.println("error de consulta con"+idMapzen +" usando otra cuenta");
                return null;
            }

    }
}
