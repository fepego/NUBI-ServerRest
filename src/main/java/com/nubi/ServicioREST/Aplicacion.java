package com.nubi.ServicioREST;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by felipe on 8/09/16.
 */
@ApplicationPath("/")
public class Aplicacion extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        HashSet h=new HashSet<Class<?>>();
        h.add(rsNubi.class);
        h.add(prueba.class);
        return h;
    }

}
