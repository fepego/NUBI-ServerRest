package com.nubi.ModuloAdaptacion;

import com.nubi.IntegracionBD.ModeloNubi;
import com.nubi.IntegracionBD.ModeloNubiImp;
import com.nubi.IntegracionMapzen.Mapzen;
import com.nubi.colecciones.Fotocopiadora;
import com.nubi.colecciones.Restaurante;
import com.nubi.colecciones.Ruta;
import com.nubi.colecciones.Usuario;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Felipe on 26/09/2016.
 */
public class FiltradoFotocopiadora {
    private static KieServices ks ;
    private static KieContainer kContainer ;
    private static Usuario usu;
    private static List<Fotocopiadora> fotocopiadoras;
    private static ModeloNubi modelo;
    private static List<Candidato> candidatos;
    private static Ruta ruta;

    public static List<Candidato> EjecutarReglasFotocopiadora(String usuario) {
        ks = KieServices.Factory.get();
        kContainer = ks.getKieClasspathContainer();
        StatelessKieSession kSession = kContainer.newStatelessKieSession("RFotocopiadora");
        modelo = new ModeloNubiImp();
        usu= modelo.buscarUsuario(usuario);
        fotocopiadoras=modelo.buscarFotocopiadoras();
        candidatos= new ArrayList<Candidato>();
        for (int i = 0; i < fotocopiadoras.size(); i++) {
            ruta= Mapzen.balanceador(usu.getLocalizacion().getLatitud(),usu.getLocalizacion().getLongitud(),fotocopiadoras.get(i).getLocalizacion().getLatitud(),fotocopiadoras.get(i).getLocalizacion().getLongitud());
            candidatos.add(new Candidato());
            candidatos.get(i).setDistancia(ruta.getDistancia());
            candidatos.get(i).setUsuario(usu);
            candidatos.get(i).setFotocopiadora(fotocopiadoras.get(i));
            candidatos.get(i).setPuntaje(0);
            if(modelo.verificarFavorito(usu.getIdUsuario(),fotocopiadoras.get(i).getNombre()))
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
        for (Candidato c : candidatos) {
            System.out.println(c.getUsuario().getIdUsuario());
            System.out.println(c.getFotocopiadora().getNombre());
            System.out.println(c.getDistancia());
            System.out.println(c.getPuntaje());
        }
        return candidatos;
    }
}
