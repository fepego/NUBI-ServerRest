package com.nubi.IntegracionJaveriana;


import com.nubi.Utils.JsonReader;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Felipe on 22/09/2016.
 */
public class UsuarioJaveriana {
    public static boolean ObtenerCuenta(String nombreUsuario)
    {
        try {


            JSONObject json = JsonReader.readJsonFromUrl("http://www.javeriana.edu.co/JaveMovil/rs/movilservicehornot/gethorariosemanauid?uid="+nombreUsuario);
            String getHorario = json.get("content")+"";
            System.out.println("usr"+nombreUsuario);
            if(getHorario.length()==2){
                System.out.println("El usuario NO es estudiante de la PUJ");
                return false;
            }
            else{
                System.out.println("El usuario es estudiante de la PUJ");
                return true;
            }


        } catch (IOException ex) {
            Logger.getLogger(UsuarioJaveriana.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
            Logger.getLogger(UsuarioJaveriana.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
