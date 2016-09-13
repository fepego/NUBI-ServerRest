package com.nubi.Utils;

import com.nubi.colecciones.Localizacion;

/**
 * Created by Felipe on 18/08/2016.
 */
public class Calculador {
    /*
 * Calculate distance between two points in latitude and longitude taking
 * into account height difference. If you are not interested in height
 * difference pass 0.0. Uses Haversine method as its base.
 *
 * lat1, lon1 Start point lat2, lon2 End point el1 Start altitude in meters
 * el2 End altitude in meters
 * @returns Distance in Meters
 * Modificado de: http://stackoverflow.com/questions/3694380/calculating-distance-between-two-points-using-latitude-longitude-what-am-i-doi
 */
    public static double distance(Localizacion loc1, Localizacion loc2) {

        final int R = 6371; // Radius of the earth

        Double latDistance = Math.toRadians(loc2.getLatitud() - loc1.getLatitud());
        Double lonDistance = Math.toRadians(loc2.getLongitud() - loc1.getLongitud());
        Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(loc1.getLatitud())) * Math.cos(Math.toRadians(loc2.getLatitud()))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters
        distance = Math.pow(distance, 2);
        return Math.sqrt(distance);
    }
}
