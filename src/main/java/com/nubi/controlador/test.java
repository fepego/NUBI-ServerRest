package com.nubi.controlador;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.nubi.IntegracionBD.ModeloNubi;
import com.nubi.IntegracionBD.ModeloNubiImp;
import com.nubi.Utils.Calculador;
import com.nubi.colecciones.*;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.aggregation.AggregationPipeline;
import org.mongodb.morphia.aggregation.Projection;
import org.mongodb.morphia.query.Query;

import java.util.*;

import static org.mongodb.morphia.aggregation.Projection.projection;

/**
 * Created by Felipe on 18/08/2016.
 */
public class test {
    public static message msj;
    private static List<Restaurante> qrList;
    private static List <Usuario> usu;
    private static List <SitiosEstudio> sts;

    private static List<Candidato> candidatos;
    public static void main(String[] args) {
        try {
//            // load up the knowledge base
//            KieServices ks = KieServices.Factory.get();
//            KieContainer kContainer = ks.getKieClasspathContainer();
//            //KieSession kSession = kContainer.newKieSession("ksession-rules");
//
//            buscarRestaurantes();
//            buscarUsuario();
//            candidatos= new ArrayList<Candidato>();
//            buscarSitioEstudio();
//            EjecutarReglasSitioEstudio(kContainer);
//            buscarRestaurantes();
//            EjecutarReglasRestaurante(kContainer);
              probabilidadAlertas();
        } catch (Throwable t) {
            t.printStackTrace();
        }

    }
    public static void EjecutarReglasSitioEstudio(KieContainer kContainer)
    {
        StatelessKieSession kSession= kContainer.newStatelessKieSession("sitio");
        for (int j=0;j<usu.size();j++) {
            candidatos=null;
            candidatos= new ArrayList<Candidato>();
            for (int i = 0; i < sts.size(); i++) {

                candidatos.add(new Candidato());
                candidatos.get(i).setDistancia(Calculador.distance(usu.get(j).getLocalizacion(), sts.get(i).getLocalizacion()));
                candidatos.get(i).setUsuario(usu.get(j));
                candidatos.get(i).setSitio(sts.get(i));
                candidatos.get(i).setPuntaje(0);
                kSession.execute(candidatos.get(i));

            }
            for (Candidato c: candidatos)
            {
                System.out.println(c.getUsuario().getIdUsuario());
                System.out.println(c.getSitio().getNombre());
                System.out.println(c.getDistancia());
                System.out.println(c.getPuntaje());
            }
        }
    }
    public static void EjecutarReglasRestaurante(KieContainer kContainer)
    {
        StatelessKieSession kSession= kContainer.newStatelessKieSession("rRest");
        for (int j=0;j<5;j++) {
            candidatos=null;
            candidatos= new ArrayList<Candidato>();
            for (int i = 0; i < 18; i++) {

                candidatos.add(new Candidato());
                candidatos.get(i).setDistancia(Calculador.distance(usu.get(j).getLocalizacion(), qrList.get(i).getLocalizacion()));
                candidatos.get(i).setUsuario(usu.get(j));
                candidatos.get(i).setRestaurante(qrList.get(i));
                candidatos.get(i).setPuntaje(0);
                /*kSession.insert(candidatos.get(i));
                kSession.fireAllRules();*/
            }
            for (Candidato c: candidatos)
            {
                System.out.println(c.getUsuario().getIdUsuario());
                System.out.println(c.getRestaurante().getNombre());
                System.out.println(c.getDistancia());
                System.out.println(c.getPuntaje());
            }
        }
    }

    public static void probabilidadAlertas()
    {

        ModeloNubi mod= new ModeloNubiImp();
        Iterator <resultadoHistorico> res=mod.getHistoricoSitioEst("Ing 4 piso");
        while (res.hasNext())
        {
            System.out.println(res.next().toString());
        }
        /*if(!Calculador.semanaCorte())
        {
            //Calcular primero semilla del dia y la hora
            double ProbaltLib,ProbaltMed,ProbaltLlen;
            int totalAlertasLibres,totalAlertasMedia,totalalertasLleno, total;
            Iterator <Alerta> alt;
            Iterator <SitiosEstudio> sitEst= mod.semillaSitiosEst("Normal");
            insertaralt();
            while (sitEst.hasNext())
            {
                SitiosEstudio s= sitEst.next();
                alt=mod.consultarAlertas("libre",s.getNombre());
                totalAlertasLibres=mod.contadorAlertas("Libre",s.getNombre());
                totalAlertasMedia=mod.contadorAlertas("Medio",s.getNombre());
                totalalertasLleno=mod.contadorAlertas("LLeno",s.getNombre());
                total=totalAlertasLibres+totalAlertasMedia+totalalertasLleno;
                System.out.println("total: "+total);




        }*/
    }
    public static void insertaralt()
    {
        Alerta alt= new Alerta();
        SitiosEstudio s=new SitiosEstudio();
        System.out.println(new Date());
        s.setNombre("Ing 4 piso");
        alt.setComentario("hola mundo");
        alt.setEstado("Libre");
        alt.setSitioEst(s);
        ModeloNubi mod= new ModeloNubiImp();
        mod.agregarAlerta(alt);
    }
}



//AGREGAR ALERTA      BORRRRRRAAAAAARR!!
/*
Alerta alt= new Alerta();
    SitiosEstudio s=new SitiosEstudio();
        System.out.println(new Date());
                s.setNombre("Ing 4 piso");
                alt.setComentario("hola mundo");
                alt.setEstado("Libre");
                alt.setSitioEst(s);
                ModeloNubi mod= new ModeloNubiImp();
                mod.agregarAlerta(alt);

HistorialSitios hist= new HistorialSitios();
        SitiosEstudio s= new SitiosEstudio();
        s.setNombre("Ing 4 piso");
        hist.setDisponibilidad(0.7);
        hist.setNumAlertasLibre(0);
        hist.setNumAlertasMedia(5);
        hist.setNumAlertasLleno(10);
        hist.setSitiosEstudio(s);
        mod.addHistoricoSitioEst(hist);


*/
