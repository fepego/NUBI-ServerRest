package com.nubi.ModuloAdaptacion;

import com.mongodb.MongoClient;
import com.nubi.IntegracionBD.ModeloNubi;
import com.nubi.IntegracionBD.ModeloNubiImp;
import com.nubi.Utils.Calculador;
import com.nubi.colecciones.*;
import org.bson.Document;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import java.util.*;

/**
 * Created by felipe on 13/09/16.
 */
public class Retroalimentacion {
    private  boolean actividadTimer;
    private Timer timer = new Timer();

    public Retroalimentacion() {
        actividadTimer=false;
    }

    public Retroalimentacion(boolean actividadTimer) {
        this.actividadTimer = actividadTimer;
    }

    public   Document recalcularDisponibilidad( boolean estado)
    {
        actividadTimer=estado;
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

                try {
                    System.out.println(Calculador.horaConsulta(0));
                    if((Calculador.horaConsulta(0)>=28840000 && Calculador.horaConsulta(0)<=70230000) && new Date().getDay()!=0)
                    {
                        System.out.println("Inicio Retroalimentación");
                        System.out.println(new Date());
                        System.out.println(Calculador.horaConsulta(0));
                        probabilidadAlertasSitiosEst(kContainer);
                        probabilidadRestaurantes(kContainer);
                        probabilidadFotocopiadoras( kContainer);
                    }
                    else
                    {
                        ModeloNubi mod= new ModeloNubiImp();
                        System.out.println("hora consulta"+(double)Calculador.horaConsulta(0));
                        System.out.println("Hora de no retroalimentación");
                        if(Calculador.horaConsulta(0)>73876000 && Calculador.horaConsulta(0)<81020000 )
                            mod.generarHistoricoAlertas();
                    }


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            public void resume()
            {

            }
            public void probabilidadAlertasSitiosEst(KieContainer kContainer) throws InterruptedException {

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
                        resHist=mod.getHistoricoSitioEst(s.getNombre());
                        semLib=s.getSemilla().get(0).getProbLibre();
                        semMed=s.getSemilla().get(0).getProbMedia();
                        semAlt=s.getSemilla().get(0).getProbAlta();
                        System.out.println("semillas: "+semLib+" "+semMed+" "+semAlt);
                        if(totalAlertasLibres==0 && totalAlertasMedia==0 && totalalertasLleno==0)
                        {
                            problibre=semLib;
                            probMedia=semMed;
                            probLleno=semAlt;
                        }
                        else
                        {
                            //Calculo bayes dado: la probabilidad de que este en algun estado (libre, medio, lleno) dada la probabilidad
                            // de alertas y semilla
                            problibre=(probaltLib*semLib)/((probaltLib*semLib)+(probaltMed*semMed)+(probaltLlen*semAlt));
                            probMedia=(probaltMed*semMed)/((probaltLib*semLib)+(probaltMed*semMed)+(probaltLlen*semAlt));
                            probLleno=(probaltLlen*semAlt)/((probaltLib*semLib)+(probaltMed*semMed)+(probaltLlen*semAlt));
                        }

                        System.out.println("rprobabilidades semilla y alt: "+problibre+ " "+probMedia+" "+probLleno);
                        if(resHist==null)
                        {
                            probabilidades prob= new probabilidades(s.getNombre(),problibre,probMedia,probLleno);
                            StatelessKieSession kSession= kContainer.newStatelessKieSession("EstadoSitio");
                            kSession.execute(prob);
                            mod.actualizarSitioEstudio(s.getNombre(),prob.getProbFinal());
                        }
                        else
                        {
                            // Mongo retorna un aggregation con lo que encuentra de todos los historicos evita un loop y consumo de máquina
                            resultadoHistorico aux=resHist.next();
                            totalHist= (int) (aux.getTotalAlertasLibre()+aux.getTotalAlertasMedio()+aux.getTotalAlertasLibre());
                            System.out.println("total hist"+totalHist);
                            if(aux.getTotalAlertasLibre()==0 && aux.getTotalAlertasMedio()==0 && aux.getTotalAlertasLLeno()==0)
                            {
                                probabilidades prob= new probabilidades(s.getNombre(),problibre,probMedia,probLleno);
                                StatelessKieSession kSession= kContainer.newStatelessKieSession("EstadoSitio");
                                kSession.execute(prob);
                                mod.actualizarSitioEstudio(s.getNombre(),prob.getProbFinal());
                            }
                            else
                            {
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
                                mod.actualizarSitioEstudio(s.getNombre(),prob.getProbFinal());
                            }
                        }
                        HistorialSitios historico= new HistorialSitios(Calculador.horaConsulta(0),
                                Calculador.diaString(new Date().getDay()),totalAlertasLibres,totalAlertasMedia,totalalertasLleno,s);
                        mod.agregarHistorialSitiosEstudio(historico);
                        Thread.sleep(1000);
                    }
                }
            }
            public void probabilidadRestaurantes(KieContainer kContainer) throws InterruptedException {

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
                Iterator <Restaurante> restaurates;
                Iterator <resultadoHistorico> resHist;

                if(!Calculador.semanaCorte())
                {
                    //Calcular primero semilla del dia y la hora
                    restaurates= mod.semillaRestaurante("Normal");
                    while (restaurates.hasNext()) {
                        Restaurante restaurante = restaurates.next();
                        System.out.println(restaurante.getNombre());
                        alt = mod.consultarAlertas("Libre", restaurante.getNombre());
                        totalAlertasLibres = mod.contadorAlertasRestaurantes("Libre", restaurante.getNombre());
                        totalAlertasMedia = mod.contadorAlertasRestaurantes("Medio", restaurante.getNombre());
                        totalalertasLleno = mod.contadorAlertasRestaurantes("LLeno", restaurante.getNombre());
                        total = totalAlertasLibres + totalAlertasMedia + totalalertasLleno;
                        System.out.println("total: " + total);
                        //calculo probabilidad de disponibilidad por alertas
                        probaltLib= totalAlertasLibres/total;
                        System.out.println("ptob lib"+totalAlertasLibres);
                        probaltMed= totalAlertasMedia/total;
                        probaltLlen= totalalertasLleno/total;
                        System.out.println("prob alertas: "+probaltLib+" "+probaltMed+" "+probaltLlen);
                        resHist=mod.getHistoricoRestaurantes(restaurante.getNombre());
                        semLib=restaurante.getSemilla().get(0).getProbLibre();
                        semMed=restaurante.getSemilla().get(0).getProbMedia();
                        semAlt=restaurante.getSemilla().get(0).getProbAlta();
                        System.out.println("semillas: "+semLib+" "+semMed+" "+semAlt);
                        if(totalAlertasLibres==0 && totalAlertasMedia==0 && totalalertasLleno==0)
                        {
                            problibre=semLib;
                            probMedia=semMed;
                            probLleno=semAlt;
                        }
                        else
                        {
                            //Calculo bayes dado: la probabilidad de que este en algun estado (libre, medio, lleno) dada la probabilidad
                            // de alertas y semilla
                            problibre=(probaltLib*semLib)/((probaltLib*semLib)+(probaltMed*semMed)+(probaltLlen*semAlt));
                            probMedia=(probaltMed*semMed)/((probaltLib*semLib)+(probaltMed*semMed)+(probaltLlen*semAlt));
                            probLleno=(probaltLlen*semAlt)/((probaltLib*semLib)+(probaltMed*semMed)+(probaltLlen*semAlt));
                        }
                        System.out.println("rprobabilidades semilla y alt: "+problibre+ " "+probMedia+" "+probLleno);
                        if(resHist==null)
                        {
                            probabilidades prob= new probabilidades(restaurante.getNombre(),problibre,probMedia,probLleno);
                            StatelessKieSession kSession= kContainer.newStatelessKieSession("EstadoSitio");
                            kSession.execute(prob);
                            mod.actualizarRestaurante(restaurante.getNombre(),prob.getProbFinal());
                        }
                        else
                        {
                            // Mongo retorna un aggregation con lo que encuentra de todos los historicos evita un loop y consumo de máquina

                            resultadoHistorico aux=resHist.next();

                            totalHist= (int) (aux.getTotalAlertasLibre()+aux.getTotalAlertasMedio()+aux.getTotalAlertasLibre());
                            System.out.println("total hist"+totalHist);
                            if(totalHist==0)
                            {
                                probabilidades prob= new probabilidades(restaurante.getNombre(),problibre,probMedia,probLleno);
                                StatelessKieSession kSession= kContainer.newStatelessKieSession("EstadoSitio");
                                kSession.execute(prob);
                                mod.actualizarRestaurante(restaurante.getNombre(),prob.getProbFinal());
                            }
                            else
                            {
                                probHistLibre=aux.getTotalAlertasLibre()/totalHist;
                                probHistMedio=aux.getTotalAlertasMedio()/totalHist;
                                probHistLleno=aux.getTotalAlertasLLeno()/totalHist;
                                System.out.println("prob historico"+ probHistLibre+" "+probHistMedio+" "+probHistLleno);
                                //calculo bayes de cualquier estado dadas la prob de (semilla/alertas) e historico
                                probFinLibre=(problibre*probHistLibre)/((problibre*probHistLibre)+(probMedia*probHistMedio)+(probLleno*probHistLleno));
                                probFinmedia=(probMedia*probHistMedio)/((problibre*probHistLibre)+(probMedia*probHistMedio)+(probLleno*probHistLleno));
                                probFinLleno=(probLleno*probHistLleno)/((problibre*probHistLibre)+(probMedia*probHistMedio)+(probLleno*probHistLleno));
                                System.out.println("probabilidades sem/alt e hist "+probFinLibre+" "+probFinmedia+" "+probFinLleno);
                                probabilidades prob= new probabilidades(restaurante.getNombre(),probFinLibre,probFinmedia,probFinLleno);
                                StatelessKieSession kSession= kContainer.newStatelessKieSession("EstadoSitio");
                                kSession.execute(prob);
                                mod.actualizarRestaurante(restaurante.getNombre(),prob.getProbFinal());
                            }
                        }
                        HistorialRestaurantes historico= new HistorialRestaurantes(Calculador.horaConsulta(0),
                                Calculador.diaString(new Date().getDay()),totalAlertasLibres,totalAlertasMedia,totalalertasLleno,restaurante);
                        mod.agregarHistorialRestaurante(historico);
                        Thread.sleep(1000);
                    }
                }
            }
            public void probabilidadFotocopiadoras(KieContainer kContainer) throws InterruptedException {

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
                Iterator <Fotocopiadora> fotocopiadoras;

                Iterator <resultadoHistorico> resHist;

                if(!Calculador.semanaCorte())
                {
                    //Calcular primero semilla del dia y la hora
                    fotocopiadoras= mod.semillaFotocopiadora("Normal");
                    while (fotocopiadoras.hasNext()) {
                        Fotocopiadora fotocopiadora = fotocopiadoras.next();
                        System.out.println(fotocopiadora.getNombre());
                        alt = mod.consultarAlertas("Libre", fotocopiadora.getNombre());
                        totalAlertasLibres = mod.contadorAlertasFotocopiadoras("Libre", fotocopiadora.getNombre());
                        totalAlertasMedia = mod.contadorAlertasFotocopiadoras("Medio", fotocopiadora.getNombre());
                        totalalertasLleno = mod.contadorAlertasFotocopiadoras("LLeno", fotocopiadora.getNombre());
                        total = totalAlertasLibres + totalAlertasMedia + totalalertasLleno;
                        System.out.println("total: " + total);
                        //calculo probabilidad de disponibilidad por alertas
                        probaltLib= totalAlertasLibres/total;
                        System.out.println("ptob lib"+totalAlertasLibres);
                        probaltMed= totalAlertasMedia/total;
                        probaltLlen= totalalertasLleno/total;
                        System.out.println("prob alertas: "+probaltLib+" "+probaltMed+" "+probaltLlen);
                        resHist=mod.getHistoricoFotocopiadora(fotocopiadora.getNombre());
                        semLib=fotocopiadora.getSemilla().get(0).getProbLibre();
                        semMed=fotocopiadora.getSemilla().get(0).getProbMedia();
                        semAlt=fotocopiadora.getSemilla().get(0).getProbAlta();
                        System.out.println("semillas: "+semLib+" "+semMed+" "+semAlt);
                        if(totalAlertasLibres==0 && totalAlertasMedia==0 && totalalertasLleno==0)
                        {
                            problibre=semLib;
                            probMedia=semMed;
                            probLleno=semAlt;
                        }
                        else
                        {
                            //Calculo bayes dado: la probabilidad de que este en algun estado (libre, medio, lleno) dada la probabilidad
                            // de alertas y semilla
                            problibre=(probaltLib*semLib)/((probaltLib*semLib)+(probaltMed*semMed)+(probaltLlen*semAlt));
                            probMedia=(probaltMed*semMed)/((probaltLib*semLib)+(probaltMed*semMed)+(probaltLlen*semAlt));
                            probLleno=(probaltLlen*semAlt)/((probaltLib*semLib)+(probaltMed*semMed)+(probaltLlen*semAlt));
                        }
                        System.out.println("rprobabilidades semilla y alt: "+problibre+ " "+probMedia+" "+probLleno);
                        if(resHist==null)
                        {
                            probabilidades prob= new probabilidades(fotocopiadora.getNombre(),problibre,probMedia,probLleno);
                            StatelessKieSession kSession= kContainer.newStatelessKieSession("EstadoSitio");
                            kSession.execute(prob);
                            mod.actualizarFotocopiadoras(fotocopiadora.getNombre(),prob.getProbFinal());
                        }
                        else
                        {
                            // Mongo retorna un aggregation con lo que encuentra de todos los historicos evita un loop y consumo de máquina

                            resultadoHistorico aux=resHist.next();

                            totalHist= (int) (aux.getTotalAlertasLibre()+aux.getTotalAlertasMedio()+aux.getTotalAlertasLibre());
                            System.out.println("total hist"+totalHist);
                            if(totalHist==0)
                            {
                                probabilidades prob= new probabilidades(fotocopiadora.getNombre(),problibre,probMedia,probLleno);
                                StatelessKieSession kSession= kContainer.newStatelessKieSession("EstadoSitio");
                                kSession.execute(prob);
                                mod.actualizarFotocopiadoras(fotocopiadora.getNombre(),prob.getProbFinal());
                            }
                            else
                            {
                                probHistLibre=aux.getTotalAlertasLibre()/totalHist;
                                probHistMedio=aux.getTotalAlertasMedio()/totalHist;
                                probHistLleno=aux.getTotalAlertasLLeno()/totalHist;
                                System.out.println("prob historico"+ probHistLibre+" "+probHistMedio+" "+probHistLleno);
                                //calculo bayes de cualquier estado dadas la prob de (semilla/alertas) e historico
                                probFinLibre=(problibre*probHistLibre)/((problibre*probHistLibre)+(probMedia*probHistMedio)+(probLleno*probHistLleno));
                                probFinmedia=(probMedia*probHistMedio)/((problibre*probHistLibre)+(probMedia*probHistMedio)+(probLleno*probHistLleno));
                                probFinLleno=(probLleno*probHistLleno)/((problibre*probHistLibre)+(probMedia*probHistMedio)+(probLleno*probHistLleno));
                                System.out.println("probabilidades sem/alt e hist "+probFinLibre+" "+probFinmedia+" "+probFinLleno);
                                probabilidades prob= new probabilidades(fotocopiadora.getNombre(),probFinLibre,probFinmedia,probFinLleno);
                                StatelessKieSession kSession= kContainer.newStatelessKieSession("EstadoSitio");
                                kSession.execute(prob);
                                mod.actualizarFotocopiadoras(fotocopiadora.getNombre(),prob.getProbFinal());
                            }

                        }
                        HistorialFotocopiadoras historico= new HistorialFotocopiadoras(Calculador.horaConsulta(0),
                                Calculador.diaString(new Date().getDay()),totalAlertasLibres,totalAlertasMedia,totalalertasLleno,fotocopiadora);
                        mod.agregarHistorialFotocopiadora(historico);
                        Thread.sleep(1000);
                    }
                }
            }
        };

        if(actividadTimer)
        {
            timer.cancel();
            timer.purge();
            actividadTimer=false;
            return new Document("retroalimentacion",false);
        }
        else
        {
            timer.schedule(timerTask,10,1800000);
            actividadTimer=true;
            return new Document("retroalimentacion",true);
        }
    }
}
