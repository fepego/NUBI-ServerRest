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
    public void crearPreferencias()
    {
        double mediaDist=0,totalAten=0, totalToleranciaDist=0;
        List<Usuario> integrantes= modelo.obtenerIntegrantes(grupo.getGrupo().getIdUsuario());
        if(integrantes!=null && integrantes.size()>0)
        {
            for (int i=0;i<integrantes.size();i++)
            {
                mediaDist+=integrantes.get(i).getPreferencias().getDistancia();
                totalAten+=integrantes.get(i).getPreferencias().getTiempoMaximo();
                totalToleranciaDist+=integrantes.get(i).getPreferencias().getToleranciaDist();
            }
            mediaDist=mediaDist/integrantes.size();
            totalAten=totalAten/integrantes.size();
            totalToleranciaDist=totalToleranciaDist/integrantes.size();
            Preferencia pref= new Preferencia(0,totalAten,new String("Medio"),mediaDist,0,true,
                    totalToleranciaDist,new String("Alto"),new String("Cualquiera"),new String("Punto de Atención"));
            modelo.agregarPreferencias(grupo.getGrupo().getIdUsuario(),pref);
            modelo.agregarRestricciones(grupo.getGrupo().getIdUsuario(),new Restriccion());
        }
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
