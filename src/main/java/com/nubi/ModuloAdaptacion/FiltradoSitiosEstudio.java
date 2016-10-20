package com.nubi.ModuloAdaptacion;


import com.nubi.IntegracionBD.ModeloNubi;
import com.nubi.IntegracionBD.ModeloNubiImp;
import com.nubi.IntegracionMapzen.Mapzen;
import com.nubi.colecciones.Ruta;
import com.nubi.colecciones.SitiosEstudio;
import com.nubi.colecciones.Usuario;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by Felipe on 22/09/2016.
 */
public class FiltradoSitiosEstudio {
    private static KieServices ks ;
    private static KieContainer kContainer ;
    private static Usuario usu;
    private static List<SitiosEstudio> sitiosEstudio;
    private static ModeloNubi modelo;
    private static List<Candidato> candidatos;

    public static List<Candidato> EjecutarReglasSitioEstudio(String usuario)
    {
        ks = KieServices.Factory.get();
        kContainer = ks.getKieClasspathContainer();
        StatelessKieSession kSession= kContainer.newStatelessKieSession("sitio");
        modelo= new ModeloNubiImp();
        Ruta ruta;
        usu=modelo.buscarUsuario(usuario);
        sitiosEstudio=modelo.buscarSitioEstudio();

        candidatos= new ArrayList<Candidato>();
        for (int i = 0; i < sitiosEstudio.size(); i++) {
            ruta= Mapzen.balanceador(usu.getLocalizacion().getLatitud(),usu.getLocalizacion().getLongitud(),sitiosEstudio.get(i).getLocalizacion().getLatitud(),sitiosEstudio.get(i).getLocalizacion().getLongitud());
            candidatos.add(new Candidato());
            candidatos.get(i).setDistancia(ruta.getDistancia());
            candidatos.get(i).setUsuario(usu);
            candidatos.get(i).setSitio(sitiosEstudio.get(i));
            candidatos.get(i).setPuntaje(0);
            if(modelo.verificarFavorito(usu.getIdUsuario(),sitiosEstudio.get(i).getNombre()))
            {
                candidatos.get(i).setFavorito(true);
            }
            else
            {
                candidatos.get(i).setFavorito(false);
            }

            kSession.execute(candidatos.get(i));

        }
        Collections.sort(candidatos);
        for (Candidato c: candidatos)
        {
            System.out.println(c.getUsuario().getIdUsuario());
            System.out.println(c.getSitio().getNombre());
            System.out.println(c.getDistancia());
            System.out.println(c.getPuntaje());
        }
        return candidatos;
    }

}
