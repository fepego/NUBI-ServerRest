package com.nubi.ModuloAdaptacion;


import com.nubi.IntegracionBD.ModeloNubi;
import com.nubi.IntegracionBD.ModeloNubiImp;
import com.nubi.IntegracionMapzen.Mapzen;
import com.nubi.Utils.Calculador;
import com.nubi.Utils.LectorSemillaFotocopiadoras;
import com.nubi.Utils.lectorSemillaRestaurantes;
import com.nubi.colecciones.*;
import com.nubi.controladorServicios.ServiciosNUBI;
import com.nubi.controladorServicios.ServiciosNUBIImp;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.lang.Thread.sleep;

/**
 * Created by Felipe on 18/08/2016.
 */
public class test {
    private static List<Restaurante> qrList;
    private static Usuario usu;
    private static List <SitiosEstudio> sts;
    private static ModeloNubi mod= new ModeloNubiImp();

    private static List<Candidato> candidatos;
    public static void main(String[] args) {
        try {
           // load up the knowledge base
             KieServices ks = KieServices.Factory.get();
             KieContainer kContainer = ks.getKieClasspathContainer();
            // FiltradoFotocopiadora.EjecutarReglasFotocopiadora("pipe");
            LectorSemillaFotocopiadoras.crearFotocopiadoras();
//            mod.limpiarColeccionAlertas();
            //SitiosEstudio sitio=(SitiosEstudio)mod.obtenerSitio("Ing 4 piso").get("sitio");
            //mod.agregarAlerta(new Alerta(new Date(),Calculador.horaConsulta(0),"Libre",sitio,"pena-carlos"));
            //mod.generarHistoricoAlertas();
            //Retroalimentacion ret= new Retroalimentacion();
            //ret.recalcularDisponibilidad(false);
            //Mapzen.balanceador(4.6468780,-74.0639307,4.62648608014277,-74.0638060122728);
            //Grupo verif= new Grupo();
            //MatchGrupos match= new MatchGrupos();
            //match.generarMatch("Grupo de Calculo");
             //KieSession kSession = kContainer.newKieSession("ksession-rules");
//
//            buscarRestaurantes();
//            buscarUsuario();
//            candidatos= new ArrayList<Candidato>();
//            buscarSitioEstudio();
            //EjecutarReglasSitioEstudio(kContainer);
//            buscarRestaurantes();
//            EjecutarReglasRestaurante(kContainer);


           /* insertaralt();
            insertHistoricos();
            insertHistoricos();
              Retroalimentacion.recalcularDisponibilidad();*/
        } catch (Throwable t) {
            t.printStackTrace();
        }

    }

}




