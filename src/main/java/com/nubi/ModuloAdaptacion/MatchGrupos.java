package com.nubi.ModuloAdaptacion;

import com.nubi.IntegracionBD.ModeloNubi;
import com.nubi.IntegracionBD.ModeloNubiImp;
import com.nubi.colecciones.Preferencia;
import com.nubi.colecciones.Restriccion;
import com.nubi.colecciones.Usuario;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;

import java.util.List;

/**
 * Created by Felipe on 18/10/2016.
 */
public class MatchGrupos {
    private Grupo grupo;
    private ModeloNubi modelo;
    private static KieServices ks ;
    private static KieContainer kContainer ;

    public MatchGrupos() {
        modelo= new ModeloNubiImp();
    }

    public MatchGrupos(Grupo grupo) {
        this.grupo = grupo;
        modelo= new ModeloNubiImp();
    }
    public void generarMatch(String nombre)
    {
        ks = KieServices.Factory.get();
        kContainer = ks.getKieClasspathContainer();
        StatelessKieSession kSession = kContainer.newStatelessKieSession("Grupo");
        List<Usuario> integrantes= modelo.obtenerIntegrantes(nombre);
        Usuario grp= modelo.obtenerInfoGrupo(nombre);
        for(int i=0;i<integrantes.size();i++)
        {
            grupo= new Grupo(grp,integrantes.get(i));
            grupo.setIntegrante(integrantes.get(i));
            System.out.println(grupo.getIntegrante());
            kSession.execute(grupo);
        }
        System.out.println(grupo.getGrupo().getRestricciones().isMovilidad());
        modelo.actualizarGrupo(grupo.getGrupo());
    }
}
