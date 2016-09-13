package com.nubi.ServicioREST;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * Created by felipe on 8/09/16.
 */
@Path("/prueba")
public class prueba {
    @GET
    @Path("/hola")
    @Produces("application/json")
    public hola holamundo()
    {
        return new hola("Soy un JSON feliz!!!");
    }
}
