package com.nubi.controlador;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.nubi.IntegracionBD.ModeloNubi;
import com.nubi.IntegracionBD.ModeloNubiImp;
import com.nubi.Utils.Calculador;
import com.nubi.colecciones.*;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
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
           // load up the knowledge base
             KieServices ks = KieServices.Factory.get();
             KieContainer kContainer = ks.getKieClasspathContainer();
             //KieSession kSession = kContainer.newKieSession("ksession-rules");
//
//            buscarRestaurantes();
//            buscarUsuario();
//            candidatos= new ArrayList<Candidato>();
//            buscarSitioEstudio();
            //EjecutarReglasSitioEstudio(kContainer);
//            buscarRestaurantes();
//            EjecutarReglasRestaurante(kContainer);
              probabilidadAlertas(kContainer);
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

    public static void probabilidadAlertas(KieContainer kContainer)
    {

        ModeloNubi mod= new ModeloNubiImp();
        //variables de calculo de probabilidad alertas de los 3 estados
        double probaltLib,probaltMed,probaltLlen;
        //Variables de calculo probabilidad de historicos de los 3 estados
        double probHistLibre, probHistMedio, probHistLleno;
        //Variables para calculo de probabilidad de Bayes con semilla y alertas
        double problibre, probMedia, probLleno;
        //Variables para calculo de probabilidad de bayes (x/alertas) e historicos
        double probFinLibre, probFinmedia,probFinLleno;
        double totalAlertasLibres,totalAlertasMedia,totalalertasLleno, total,totalHist;
        Iterator <Alerta> alt;
        Iterator <SitiosEstudio> sitEst;
        Iterator <resultadoHistorico> resHist;

        if(!Calculador.semanaCorte())
        {
            //Calcular primero semilla del dia y la hora
            sitEst= mod.semillaSitiosEst("Normal");
            insertaralt();
            //insertHistoricos();
            while (sitEst.hasNext()) {
                SitiosEstudio s = sitEst.next();
                System.out.println(s.getNombre());
                alt = mod.consultarAlertas("Libre", s.getNombre());
                totalAlertasLibres = mod.contadorAlertas("Libre", s.getNombre());
                totalAlertasMedia = mod.contadorAlertas("Medio", s.getNombre());
                totalalertasLleno = mod.contadorAlertas("LLeno", s.getNombre());
                total = totalAlertasLibres + totalAlertasMedia + totalalertasLleno;
                System.out.println("total: " + total);
                //calculo probabilidad de disponibilidad por alertas
                probaltLib= totalAlertasLibres/total;
                System.out.println("ptob lib"+totalAlertasLibres);
                probaltMed= totalAlertasMedia/total;
                probaltLlen= totalalertasLleno/total;
                System.out.println("prob alertas: "+probaltLib+" "+probaltMed+" "+probaltLlen);
                resHist=mod.getHistoricoSitioEst("Ing 4 piso");
                //Calculo bayes dado: la probabilidad de que este en algun estado (libre, medio, lleno) dada la probabilidad
                // de alertas y semilla
                problibre=(probaltLib*0.3)/((probaltLib*0.3)+(probaltMed*0.5)+(probaltLlen*0.2));
                probMedia=(probaltMed*0.5)/((probaltLib*0.3)+(probaltMed*0.5)+(probaltLlen*0.2));
                probLleno=(probaltLlen*0.2)/((probaltLib*0.3)+(probaltMed*0.5)+(probaltLlen*0.2));
                System.out.println("rprobabilidades semilla y alt: "+problibre+ " "+probMedia+" "+probLleno);
                if(resHist.hasNext() && resHist!=null)
                {
                    resultadoHistorico aux=resHist.next();
                    totalHist= (int) (aux.getTotalAlertasLibre()+aux.getTotalAlertasMedio()+aux.getTotalAlertasLibre());
                    System.out.println("total hist"+totalHist);
                    probHistLibre=aux.getTotalAlertasLibre()/totalHist;
                    probHistMedio=aux.getTotalAlertasMedio()/totalHist;
                    probHistLleno=aux.getTotalAlertasLLeno()/totalHist;
                    System.out.println("prob historico"+ probHistLibre+" "+probHistMedio+" "+probHistLleno);
                    //calculo bayes de cualquier estado dadas la prob de (semilla/alertas) e historico
                    probFinLibre=(problibre*probHistLibre)/((problibre*probHistLibre)+(probMedia*probHistMedio)+(probLleno*probHistLleno));
                    probFinmedia=(probMedia*probHistMedio)/((problibre*probHistLibre)+(probMedia*probHistMedio)+(probLleno*probHistLleno));
                    probFinLleno=(probLleno*probHistLleno)/((problibre*probHistLibre)+(probMedia*probHistMedio)+(probLleno*probHistLleno));
                    System.out.println("probabilidades sem/alt e hist "+probFinLibre+" "+probFinmedia+" "+probFinLleno);
                    probabilidades prob= new probabilidades(probFinLibre,probFinmedia,probFinLleno);
                    StatelessKieSession kSession= kContainer.newStatelessKieSession("EstadoSitio");
                    kSession.execute(prob);
                }
            }




        }
    }
    public static void insertaralt()
    {
        Alerta alt= new Alerta();
        SitiosEstudio s=new SitiosEstudio();
        System.out.println(new Date());
        s.setNombre("Ing 4 piso");
        alt.setComentario("hola mundo");
        alt.setEstado("LLeno");
        alt.setSitioEst(s);
        ModeloNubi mod= new ModeloNubiImp();
        mod.agregarAlerta(alt);
    }
    public static void insertHistoricos()
    {
        ModeloNubi mod= new ModeloNubiImp();
        HistorialSitios hist= new HistorialSitios();
        SitiosEstudio s= new SitiosEstudio();
        s.setNombre("Ing 4 piso");
        hist.setDisponibilidad(0.7);
        hist.setNumAlertasLibre(4);
        hist.setNumAlertasMedia(10);
        hist.setNumAlertasLleno(4);
        hist.setSitiosEstudio(s);
        mod.addHistoricoSitioEst(hist);
    }
}




