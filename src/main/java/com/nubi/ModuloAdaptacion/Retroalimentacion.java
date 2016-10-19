package com.nubi.ModuloAdaptacion;

import com.mongodb.MongoClient;
import com.nubi.IntegracionBD.ModeloNubi;
import com.nubi.IntegracionBD.ModeloNubiImp;
import com.nubi.Utils.Calculador;
import com.nubi.colecciones.Alerta;
import com.nubi.colecciones.Restaurante;
import com.nubi.colecciones.SitiosEstudio;
import com.nubi.colecciones.Usuario;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by felipe on 13/09/16.
 */
public class Retroalimentacion {

    public static void recalcularDisponibilidad()
    {
        // Clase en la que está el código a ejecutar
        TimerTask timerTask = new TimerTask() {
            int i=0;
            List<Restaurante> qrList;
            List <Usuario> usu;
            List <SitiosEstudio> sts;
            MongoClient cli= new MongoClient("localhost",27017);
            Morphia mph= new Morphia();
            Datastore ds= mph.createDatastore(cli,"NUBI");
            KieServices ks = KieServices.Factory.get();
            KieContainer kContainer = ks.getKieClasspathContainer();
            public void run() {

                probabilidadAlertas(kContainer);
            }
            public void probabilidadAlertas(KieContainer kContainer)
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
                //variables auxiliares de semilla
                double semAlt, semMed, semLib;
                Iterator<Alerta> alt;
                Iterator <SitiosEstudio> sitEst;
                Iterator <resultadoHistorico> resHist;

                if(!Calculador.semanaCorte())
                {
                    //Calcular primero semilla del dia y la hora
                    sitEst= mod.semillaSitiosEst("Normal");
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
                        semLib=s.getSemilla().get(0).getProbLibre();
                        semMed=s.getSemilla().get(0).getProbMedia();
                        semAlt=s.getSemilla().get(0).getProbAlta();
                        System.out.println("semillas: "+semLib+" "+semMed+" "+semAlt);
                        //Calculo bayes dado: la probabilidad de que este en algun estado (libre, medio, lleno) dada la probabilidad
                        // de alertas y semilla
                        problibre=(probaltLib*semLib)/((probaltLib*semLib)+(probaltMed*semMed)+(probaltLlen*semAlt));
                        probMedia=(probaltMed*semMed)/((probaltLib*semLib)+(probaltMed*semMed)+(probaltLlen*semAlt));
                        probLleno=(probaltLlen*semAlt)/((probaltLib*semLib)+(probaltMed*semMed)+(probaltLlen*semAlt));
                        System.out.println("rprobabilidades semilla y alt: "+problibre+ " "+probMedia+" "+probLleno);
                        if(resHist==null)
                        {
                            probabilidades prob= new probabilidades(s.getNombre(),problibre,probMedia,probLleno);
                            StatelessKieSession kSession= kContainer.newStatelessKieSession("EstadoSitio");
                            kSession.execute(prob);
                        }
                        else
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
                            probabilidades prob= new probabilidades(s.getNombre(),probFinLibre,probFinmedia,probFinLleno);
                            StatelessKieSession kSession= kContainer.newStatelessKieSession("EstadoSitio");
                            kSession.execute(prob);
                        }
                    }
                }
            }
        };

        // Aquí se pone en marcha el timer cada segundo.
        Timer timer = new Timer();
        // Dentro de 0 milisegundos avísame cada 1000 milisegundos
        timer.schedule(timerTask,10,10000);
    }



}
