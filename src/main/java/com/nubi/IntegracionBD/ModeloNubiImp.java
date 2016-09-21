package com.nubi.IntegracionBD;

import com.mongodb.MongoClient;
import com.nubi.Utils.Calculador;
import com.nubi.colecciones.*;
import com.nubi.controlador.Sitio;
import com.nubi.controlador.resultadoHistorico;
import org.bson.Document;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.aggregation.Accumulator;
import org.mongodb.morphia.aggregation.AggregationPipeline;
import org.mongodb.morphia.query.Query;

import java.util.*;

import static org.mongodb.morphia.aggregation.Group.grouping;
import static org.mongodb.morphia.aggregation.Group.sum;
import static org.mongodb.morphia.aggregation.Projection.projection;

/**
 * Created by Felipe on 20/09/2016.
 */
public class ModeloNubiImp implements ModeloNubi {
    private static MongoClient cli= new MongoClient("localhost",27017);
    private static Morphia mph= new Morphia();
    private static Datastore ds= mph.createDatastore(cli,"NUBI");
    final GregorianCalendar fechaCte=new GregorianCalendar(1971,0,01,0,0);

    public ModeloNubiImp() {
    }


    public Iterator<SitiosEstudio> semillaSitiosEst(String tipoDia)
    {
        Date d= new Date();
        String dia= Calculador.diaString(d.getDay());
        d.setYear(71);
        d.setMonth(0);
        d.setDate(1);
        long hora=d.getTime()-fechaCte.getTimeInMillis();
        AggregationPipeline agg= ds.createAggregation(SitiosEstudio.class)
                .unwind("semilla")
                .match(ds.createQuery(SitiosEstudio.class).field("semilla.tipoDia").equal(tipoDia))
                .match(ds.createQuery(SitiosEstudio.class).field("semilla.dia").equal(dia))
                .match(ds.createQuery(SitiosEstudio.class).field("semilla.horaInicio").lessThan(hora))
                .match(ds.createQuery(SitiosEstudio.class).field("semilla.horaFin").greaterThan(hora))
                .project(projection("semilla.tipoDia"),
                        projection("semilla.disponibilidad"),
                        projection("semilla.dia"));
        Iterator<SitiosEstudio> st=agg.aggregate(SitiosEstudio.class);
        if(st.hasNext())
        {
            return st;
        }
        return null;
    }
    public boolean agregarAlerta(Alerta Alt)
    {
        if(Alt!=null)
        {
            Date fecha= new Date();
            fecha.setHours(0);
            fecha.setMinutes(0);
            long hora=(new Date().getTime())-fecha.getTime();
            Alt.setHoraPublicacion(hora);
            ds.save(Alt);
            return true;
        }
        return false;
    }
    public int contadorAlertas(String estado, String nombreSitio)
    {
        Date fecha= new Date();
        fecha.setHours(0);
        fecha.setMinutes(0);
        Date fechaIntervalo= new Date();
        fechaIntervalo.setMinutes(fechaIntervalo.getMinutes()-10);
        long horamin=fechaIntervalo.getTime()-fecha.getTime();
        fechaIntervalo.setMinutes(fechaIntervalo.getMinutes()+20);
        long horamax=fechaIntervalo.getTime()-fecha.getTime();
        SitiosEstudio s= new SitiosEstudio();
        s.setNombre(nombreSitio);
        int numAlertas= (int)ds.createQuery(Alerta.class).field("estado").equal(estado)
                .field("sitioEst").equal(s)
                .field("horaPublicacion").greaterThan(horamin)
                .field("horaPublicacion").lessThan(horamax)
                .countAll();
        return numAlertas;
    }
    public Iterator<Alerta> consultarAlertas(String estado, String nombreSitio)
    {
        Date fecha= new Date();
        fecha.setHours(0);
        fecha.setMinutes(0);
        Date fechaIntervalo= new Date();
        fechaIntervalo.setMinutes(fechaIntervalo.getMinutes()-10);
        long horamin=fechaIntervalo.getTime()-fecha.getTime();
        fechaIntervalo.setMinutes(fechaIntervalo.getMinutes()+20);
        long horamax=fechaIntervalo.getTime()-fecha.getTime();
        SitiosEstudio s= new SitiosEstudio();
        s.setNombre(nombreSitio);
        Iterator <Alerta> alertas= ds.createQuery(Alerta.class).field("estado").equal(estado)
                .field("sitioEst").equal(s)
                .field("horaPublicacion").greaterThan(horamin)
                .field("horaPublicacion").lessThan(horamax).iterator();
        if(alertas.hasNext())
        {
            return alertas;
        }
        return null;
    }
    public boolean addHistoricoSitioEst(HistorialSitios historico)
    {
        if(historico!=null)
        {
            Date fecha= new Date();
            fecha.setHours(0);
            fecha.setMinutes(0);
            long hora=new Date().getTime()-fecha.getTime();
            String dia= Calculador.diaString(new Date().getDay());
            historico.setHoraConsulta(hora);
            historico.setDia(dia);
            ds.save(historico);
            return true;
        }
        return false;
    }
    public Iterator<HistorialSitios> consultarHistoricoSitioEst(String id)
    {
        Date fecha= new Date();
        fecha.setHours(0);
        fecha.setMinutes(0);
        Date fechaInt=  new Date();
        fechaInt.setHours(fechaInt.getHours()-1);
        long horamin=fechaInt.getTime()-fecha.getTime();
        fechaInt.setHours(fechaInt.getHours()+2);
        long horamax=fechaInt.getTime()-fecha.getTime();
        SitiosEstudio s= new SitiosEstudio();
        s.setNombre(id);
        Iterator <HistorialSitios> hist= ds.createQuery(HistorialSitios.class).field("sitiosEstudio").equal(s)
                .field("_id").greaterThanOrEq(horamin)
                .field("_id").lessThanOrEq(horamax).iterator();
        if(hist.hasNext()) {
            return hist;
        }
        return null;
    }
    public Iterator <resultadoHistorico> getHistoricoSitioEst(String id)
    {
        Date fecha= new Date();
        fecha.setHours(0);
        fecha.setMinutes(0);
        Date fechaInt=  new Date();
        fechaInt.setHours(fechaInt.getHours()-1);
        long horamin=fechaInt.getTime()-fecha.getTime();
        fechaInt.setHours(fechaInt.getHours()+2);
        long horamax=fechaInt.getTime()-fecha.getTime();
        SitiosEstudio s= new SitiosEstudio();
        s.setNombre(id);
        Iterator <resultadoHistorico> resHistorico=ds.createAggregation(HistorialSitios.class)
                .match(ds.createQuery(HistorialSitios.class).field("sitiosEstudio").equal(s))
                .match(ds.createQuery(HistorialSitios.class).field("_id").greaterThanOrEq(horamin))
                .match(ds.createQuery(HistorialSitios.class).field("_id").lessThanOrEq(horamax))
                .group("sitiosEstudio",
                    grouping("totalAlertasLibre",new Accumulator("$sum","numAlertasLibre")), grouping("totalAlertasMedio",new Accumulator("$sum","numAlertasMedia")),
                        grouping("totalAlertasLLeno",new Accumulator("$sum","numAlertasLleno"))).aggregate(resultadoHistorico.class);
        if(resHistorico.hasNext())
        {
            return resHistorico;
        }
        return null;
    }


   /* public static void buscarRestaurantes()
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

        GregorianCalendar cini= new GregorianCalendar(1971,0,1,0,0);
        GregorianCalendar c1= new GregorianCalendar(1971,0,1,18,0);
        GregorianCalendar c2= new GregorianCalendar(1971,0,1,20,0);
        long ini= c1.getTimeInMillis()-cini.getTimeInMillis();
        long fin= c2.getTimeInMillis()-cini.getTimeInMillis();
        Semilla sm= new Semilla(ini,fin,0.2,"Normal","lunes");
        sts.get(0).getSemilla().add(sm);
        ds.save(sts);

    }
    */
}
