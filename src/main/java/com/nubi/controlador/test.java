package com.nubi.controlador;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.nubi.Utils.Calculador;
import com.nubi.colecciones.Restaurante;
import com.nubi.colecciones.Semilla;
import com.nubi.colecciones.SitiosEstudio;
import com.nubi.colecciones.Usuario;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.aggregation.AggregationPipeline;
import org.mongodb.morphia.aggregation.Projection;
import org.mongodb.morphia.query.Query;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Felipe on 18/08/2016.
 */
public class test {
    public static message msj;
    private static List<Restaurante> qrList;
    private static List <Usuario> usu;
    private static List <SitiosEstudio> sts;
    private static MongoClient cli= new MongoClient("localhost",27017);
    private static Morphia mph= new Morphia();
    private static Datastore ds= mph.createDatastore(cli,"NUBI");
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
    public static void buscarRestaurantes()
    {
        Query<Restaurante> qry= ds.createQuery(Restaurante.class);

        qrList=ds.find(qry.getEntityClass()).asList();

    }
    public static void buscarUsuario()
    {
        Query<Usuario> qry= ds.createQuery(Usuario.class);

        usu=ds.find(qry.getEntityClass()).asList();

    }
    public static void buscarSitioEstudio()
    {
        Query<SitiosEstudio> qry= ds.createQuery(SitiosEstudio.class);
        sts= ds.find(qry.getEntityClass()).asList();
    }
    public static void actualizar()
    {
        Date fecha= new Date();
        Query<SitiosEstudio> qry= ds.createQuery(SitiosEstudio.class);
        sts= ds.find(qry.getEntityClass()).asList();
        sts.get(0).setSemilla(new ArrayList<Semilla>());
        sts.get(0).getSemilla().add(new Semilla());
        sts.get(0).getSemilla().get(0).setHoraInicio(fecha);
        ds.save(sts);

    }
    public static void probabilidadAlertas()
    {
        if(!Calculador.semanaCorte())
        {
           /* AggregationPipeline qry= ds.createAggregation(SitiosEstudio.class);
            qry.unwind("semilla");
            qry.match(ds.createQuery(SitiosEstudio.class).filter("semilla.tipoDia","Normal"));
            Iterator<SitiosEstudio> st= qry.aggregate(SitiosEstudio.class);
            while(st.hasNext())
            {
                System.out.println(st.next());
            }*/
            Query<SitiosEstudio> matchQuery = ds.createQuery(SitiosEstudio.class).field("semilla.tipoDia").equal("Normal");
            AggregationPipeline agg= ds.createAggregation(SitiosEstudio.class)
                    .match(matchQuery)
                    .project(Projection.projection("id","_id"));
            Iterator <SitiosEstudio> st=agg.aggregate(SitiosEstudio.class);
            if(st.hasNext())
            {
                System.out.println(st.next());
            }

            }
    }
}
