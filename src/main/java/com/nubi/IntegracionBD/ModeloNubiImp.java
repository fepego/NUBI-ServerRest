package com.nubi.IntegracionBD;

import com.mongodb.MongoClient;
import com.nubi.ModuloAdaptacion.Sitio;
import com.nubi.ModuloAdaptacion.resultadoHistorico;
import com.nubi.Utils.Calculador;
import com.nubi.colecciones.*;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.aggregation.Accumulator;
import org.mongodb.morphia.aggregation.AggregationPipeline;
import org.mongodb.morphia.query.UpdateOperations;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import java.util.*;

import static org.mongodb.morphia.aggregation.Group.grouping;
import static org.mongodb.morphia.aggregation.Projection.projection;

/**
 * Created by Felipe on 20/09/2016.
 */
@XmlRootElement
public class ModeloNubiImp implements ModeloNubi {
    private static MongoClient cli;
    private static Morphia mph;
    private static Datastore ds;
    final GregorianCalendar fechaCte=new GregorianCalendar(1971,0,01,0,0);

    public ModeloNubiImp() {
         cli= new MongoClient("localhost",27017);
         mph= new Morphia();
        ds= mph.createDatastore(cli,"NUBI");
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
                        projection("semilla.probLibre"),
                        projection("semilla.probMedia"),
                        projection("semilla.probAlta"),
                        projection("semilla.dia"));
        Iterator<SitiosEstudio> st=agg.aggregate(SitiosEstudio.class);
        if(st.hasNext())
        {
            return st;
        }
        return null;
    }
    public Iterator<Restaurante> semillaRestaurante(String tipoDia)
    {
        Date d= new Date();
        String dia= Calculador.diaString(d.getDay());
        d.setYear(71);
        d.setMonth(0);
        d.setDate(1);
        long hora=d.getTime()-fechaCte.getTimeInMillis();
        AggregationPipeline agg= ds.createAggregation(Restaurante.class)
                .unwind("semilla")
                .match(ds.createQuery(Restaurante.class).field("semilla.tipoDia").equal(tipoDia))
                .match(ds.createQuery(Restaurante.class).field("semilla.dia").equal(dia))
                .match(ds.createQuery(Restaurante.class).field("semilla.horaInicio").lessThan(hora))
                .match(ds.createQuery(Restaurante.class).field("semilla.horaFin").greaterThan(hora))
                .project(projection("semilla.tipoDia"),
                        projection("semilla.probLibre"),
                        projection("semilla.probMedia"),
                        projection("semilla.probAlta"),
                        projection("semilla.dia"));
        Iterator<Restaurante> restaurante=agg.aggregate(Restaurante.class);
        if(restaurante.hasNext())
        {
            return restaurante;
        }
        return null;
    }
    public Iterator<Fotocopiadora> semillaFotocopiadora(String tipoDia)
    {
        Date d= new Date();
        String dia= Calculador.diaString(d.getDay());
        d.setYear(71);
        d.setMonth(0);
        d.setDate(1);
        long hora=d.getTime()-fechaCte.getTimeInMillis();
        AggregationPipeline agg= ds.createAggregation(Fotocopiadora.class)
                .unwind("semilla")
                .match(ds.createQuery(Fotocopiadora.class).field("semilla.tipoDia").equal(tipoDia))
                .match(ds.createQuery(Fotocopiadora.class).field("semilla.dia").equal(dia))
                .match(ds.createQuery(Fotocopiadora.class).field("semilla.horaInicio").lessThan(hora))
                .match(ds.createQuery(Fotocopiadora.class).field("semilla.horaFin").greaterThan(hora))
                .project(projection("semilla.tipoDia"),
                        projection("semilla.probLibre"),
                        projection("semilla.probMedia"),
                        projection("semilla.probAlta"),
                        projection("semilla.dia"));
        Iterator<Fotocopiadora> fotocopiadoras=agg.aggregate(Fotocopiadora.class);
        if(fotocopiadoras.hasNext())
        {
            return fotocopiadoras;
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
    public int contadorAlertasFotocopiadoras(String estado, String nombreSitio)
    {
        Date fecha= new Date();
        fecha.setHours(0);
        fecha.setMinutes(0);
        Date fechaIntervalo= new Date();
        fechaIntervalo.setMinutes(fechaIntervalo.getMinutes()-10);
        long horamin=fechaIntervalo.getTime()-fecha.getTime();
        fechaIntervalo.setMinutes(fechaIntervalo.getMinutes()+20);
        long horamax=fechaIntervalo.getTime()-fecha.getTime();
        Fotocopiadora s= new Fotocopiadora();
        s.setNombre(nombreSitio);
        int numAlertas= (int)ds.createQuery(Alerta.class).field("estado").equal(estado)
                .field("fotocopiadora").equal(s)
                .field("horaPublicacion").greaterThan(horamin)
                .field("horaPublicacion").lessThan(horamax)
                .countAll();
        return numAlertas;
    }
    public int contadorAlertasRestaurantes(String estado, String nombreSitio)
    {
        Date fecha= new Date();
        fecha.setHours(0);
        fecha.setMinutes(0);
        Date fechaIntervalo= new Date();
        fechaIntervalo.setMinutes(fechaIntervalo.getMinutes()-10);
        long horamin=fechaIntervalo.getTime()-fecha.getTime();
        fechaIntervalo.setMinutes(fechaIntervalo.getMinutes()+20);
        long horamax=fechaIntervalo.getTime()-fecha.getTime();
        Restaurante s= new Restaurante();
        s.setNombre(nombreSitio);
        int numAlertas= (int)ds.createQuery(Alerta.class).field("estado").equal(estado)
                .field("restaurante").equal(s)
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
    public Iterator <resultadoHistorico> getHistoricoRestaurantes(String id)
    {
        Date fecha= new Date();
        fecha.setHours(0);
        fecha.setMinutes(0);
        Date fechaInt=  new Date();
        fechaInt.setHours(fechaInt.getHours()-1);
        long horamin=fechaInt.getTime()-fecha.getTime();
        fechaInt.setHours(fechaInt.getHours()+2);
        long horamax=fechaInt.getTime()-fecha.getTime();
        Restaurante restaurante= new Restaurante();
        restaurante.setNombre(id);
        Iterator <resultadoHistorico> resHistorico=ds.createAggregation(HistorialRestaurantes.class)
                .match(ds.createQuery(HistorialRestaurantes.class).field("restaurante").equal(restaurante))
                .match(ds.createQuery(HistorialRestaurantes.class).field("_id").greaterThanOrEq(horamin))
                .match(ds.createQuery(HistorialRestaurantes.class).field("_id").lessThanOrEq(horamax))
                .group("restaurante",
                        grouping("totalAlertasLibre",new Accumulator("$sum","numAlertasLibre")), grouping("totalAlertasMedio",new Accumulator("$sum","numAlertasMedia")),
                        grouping("totalAlertasLLeno",new Accumulator("$sum","numAlertasLleno"))).aggregate(resultadoHistorico.class);
        if(resHistorico.hasNext())
        {
            return resHistorico;
        }
        return null;
    }
    public Iterator <resultadoHistorico> getHistoricoFotocopiadora(String id)
    {
        Date fecha= new Date();
        fecha.setHours(0);
        fecha.setMinutes(0);
        Date fechaInt=  new Date();
        fechaInt.setHours(fechaInt.getHours()-1);
        long horamin=fechaInt.getTime()-fecha.getTime();
        fechaInt.setHours(fechaInt.getHours()+2);
        long horamax=fechaInt.getTime()-fecha.getTime();
        Fotocopiadora fotocopiadoras= new Fotocopiadora();
        fotocopiadoras.setNombre(id);
        Iterator <resultadoHistorico> resHistorico=ds.createAggregation(HistorialFotocopiadoras.class)
                .match(ds.createQuery(HistorialFotocopiadoras.class).field("fotocopiadora").equal(fotocopiadoras))
                .match(ds.createQuery(HistorialFotocopiadoras.class).field("_id").greaterThanOrEq(horamin))
                .match(ds.createQuery(HistorialFotocopiadoras.class).field("_id").lessThanOrEq(horamax))
                .group("fotocopiadora",
                        grouping("totalAlertasLibre",new Accumulator("$sum","numAlertasLibre")), grouping("totalAlertasMedio",new Accumulator("$sum","numAlertasMedia")),
                        grouping("totalAlertasLLeno",new Accumulator("$sum","numAlertasLleno"))).aggregate(resultadoHistorico.class);
        if(resHistorico.hasNext())
        {
            return resHistorico;
        }
        return null;
    }
    public void actualizarSitioEstudio(String nombreSitio, double disponibilidad)
    {
        List <SitiosEstudio> sitiosEstudio= ds.createQuery(SitiosEstudio.class).field("_id").contains(nombreSitio).asList();
        if(sitiosEstudio != null)
        {
            sitiosEstudio.get(0).getEstado().setDisponibilidad(disponibilidad);
            ds.save(sitiosEstudio.get(0));
        }
    }
    public void InsertarRuta(Ruta ruta)
    {
        if(ruta!=null)
        {
            ds.save(ruta);
        }
    }
    public Iterator <Ruta> buscarRuta(double latInicio, double longInicio, double latDestino, double longDestino)
    {
        Iterator<Ruta> rutas=  ds.createQuery(Ruta.class).field("longDestino").equal(longDestino)
                .field("latDestino").equal(latDestino)
                .field("longInicio").greaterThanOrEq(longInicio-0.00005)
                .field("longInicio").lessThanOrEq(longInicio+0.00005)
                .field("latInicio").greaterThanOrEq(latInicio-0.00005)
                .field("latInicio").lessThanOrEq(latInicio+0.00005).iterator();
        if(rutas.hasNext())
        {
            return rutas;
        }
        return null;
    }
    public  List<SitiosEstudio> buscarSitioEstudio()
    {
        return  ds.createQuery(SitiosEstudio.class).asList();

    }
    public Usuario buscarUsuario(String usuario)
    {
        List <Usuario> u = ds.createQuery(Usuario.class).field("_id").equal(usuario).asList();

        if(u.size()>0)
        {
            return u.get(0);
        }
        return null;
    }
    public void actualizarLocUsuario(String nombre, double latitud, double longitud)
    {
        Usuario u= ds.createQuery(Usuario.class).field("_id").equal(nombre).asList().get(0);
        if(u!=null)
        {
            u.getLocalizacion().setLatitud(latitud);
            u.getLocalizacion().setLongitud(longitud);
            ds.save(u);
        }
    }
    public  List<Restaurante> buscarRestaurantes()
    {

        return ds.createQuery(Restaurante.class).asList();

    }
    public  List<Fotocopiadora> buscarFotocopiadoras()
    {

        return ds.createQuery(Fotocopiadora.class).asList();

    }
    public void agregarFotocopiadora(Fotocopiadora ft)
    {
        if(ft!=null)
        {
            ds.save(ft);
        }
    }
    public void agregarusuario (Usuario u)
    {
        if(u!=null)
        {
            ds.save(u);
        }
    }
    public List<Usuario> validarLogin(String usuario, String pass)
    {
        return ds.createQuery(Usuario.class).field("_id").equal(usuario)
                .field("password").equal(pass).asList();
    }
    public void actualizarListaContactos(String usuario, String contacto)
    {
        Usuario us=buscarUsuario(usuario);

        UpdateOperations <Usuario> ops=ds.createUpdateOperations(Usuario.class).add("listaContactos",contacto);
        ds.update(us,ops);

    }

    /**
     * {@inheritDoc}
     * @param nombreUsuario
     * @param preferencias
     */
    public void agregarPreferencias(String nombreUsuario, Preferencia preferencias)
    {
        Usuario us=buscarUsuario(nombreUsuario);
        UpdateOperations <Usuario> ops=ds.createUpdateOperations(Usuario.class).set("preferencias",preferencias);
        ds.update(us,ops);
    }

    /**
     * {@inheritDoc}
     * @param nombreUsuario
     * @param restricciones
     */
    public void agregarRestricciones(String nombreUsuario, Restriccion restricciones)
    {
        Usuario us=buscarUsuario(nombreUsuario);
        UpdateOperations <Usuario> ops=ds.createUpdateOperations(Usuario.class).set("restricciones",restricciones);
        ds.update(us,ops);
    }
    public void agregarGrupo(String nombre,String administrador)
    {
        Usuario grupo= new Usuario();
        grupo.setIdUsuario(nombre);
        grupo.setTipoUsuario("grupo");
        grupo.setAdministrador(administrador);
        ds.save(grupo);
    }
    public  List<Alerta> consultarAlerta(String nombreSitio)
    {
        long horaini=Calculador.horaConsulta(-15);
        long horafin=Calculador.horaConsulta(15);
        SitiosEstudio est=new SitiosEstudio();
        est.setNombre(nombreSitio);
        List<Alerta> alertas= ds.createQuery(Alerta.class)
            .field("sitioEst").equal(est)
            .field("horaPublicacion").greaterThan(horaini)
            .field("horaPublicacion").lessThan(horafin).asList();
        if(alertas.size()==0)
        {
            Restaurante res= new Restaurante();
            res.setNombre(nombreSitio);
            alertas= ds.createQuery(Alerta.class)
                    .field("restaurante").equal(res)
                    .field("horaPublicacion").greaterThan(horaini)
                    .field("horaPublicacion").lessThan(horafin).asList();
            if(alertas.size()==0)
            {
                Fotocopiadora fot= new Fotocopiadora();
                fot.setNombre(nombreSitio);
                alertas= ds.createQuery(Alerta.class)
                        .field("fotocopiadora").equal(fot)
                        .field("horaPublicacion").greaterThan(horaini)
                        .field("horaPublicacion").lessThan(horafin).asList();
                if(alertas.size()==0)
                {
                    return null;
                }
            }
            return alertas;
        }
        return alertas;
    }
    public Document disponibilidadSitio(String sitio)
    {
        SitiosEstudio st= new SitiosEstudio();
        st.setNombre(sitio);
        List <SitiosEstudio> sitiosEst=ds.createQuery(SitiosEstudio.class).field("_id").equal(sitio).asList();
        List <Restaurante> restaurantes= ds.createQuery(Restaurante.class).field("_id").equal(sitio).asList();
        List <Fotocopiadora> fotocopiadora= ds.createQuery(Fotocopiadora.class).field("_id").equal(sitio).asList();
        if(sitiosEst.size()>0)
        {
            return new Document("disponibilidad",sitiosEst.get(0).getEstado().getDisponibilidad());
        }
        if(restaurantes.size()>0)
        {
            return new Document("disponibilidad",restaurantes.get(0).getEstado().getDisponibilidad());
        }
        if(fotocopiadora.size()>0)
        {
            return new Document("disponibilidad",fotocopiadora.get(0).getEstado().getDisponibilidad());
        }
        return null;
    }
    public void retroalimentacionAlerta(String id, boolean tipo)
    {
        ObjectId oid=new ObjectId(id);
        Alerta alertas=ds.find(Alerta.class).field("_id").equal(oid).get();
        System.out.println("entro"+alertas.toString());
        if(tipo && alertas!=null)
        {
            System.out.println("entro");
            if(alertas.getSitioEst()!=null)
            {
                Alerta alt=new Alerta(new Date(),Calculador.horaConsulta(0),alertas.getEstado(),alertas.getSitioEst());
                agregarAlerta(alt);
            }
            if(alertas.getFotocopiadora()!=null)
            {
                Alerta alt=new Alerta(new Date(),Calculador.horaConsulta(0),alertas.getEstado(),alertas.getFotocopiadora());
                agregarAlerta(alt);
            }
            if(alertas.getRestaurante()!=null)
            {
                Alerta alt=new Alerta(new Date(),Calculador.horaConsulta(0),alertas.getEstado(),alertas.getRestaurante());
                agregarAlerta(alt);
            }
        }
        if(tipo==false  && alertas!=null)
        {
            ds.delete(ds.createQuery(Alerta.class).filter("_id",oid));
        }
    }

    /**
     * {@inheritDoc}
     * @param usuario
     * @param sitio
     */
    public void agregarFavorito(String usuario, String sitio)
    {
        List <SitiosEstudio> sitiosEst=ds.createQuery(SitiosEstudio.class).field("_id").equal(sitio).asList();
        List <Restaurante> restaurantes= ds.createQuery(Restaurante.class).field("_id").equal(sitio).asList();
        List <Fotocopiadora> fotocopiadora= ds.createQuery(Fotocopiadora.class).field("_id").equal(sitio).asList();
        Usuario usr;
        if(sitiosEst.size()>0)
        {
            List <Usuario> lUsr= ds.createQuery(Usuario.class).field("_id").equal(usuario)
                    .field("favoritosEstudio").equal(sitiosEst.get(0)).asList();
            if(lUsr.size()>0)
            {
                usr=buscarUsuario(usuario);
                UpdateOperations <Usuario> ops=ds.createUpdateOperations(Usuario.class).removeAll("favoritosEstudio",sitiosEst.get(0));
                ds.update(usr,ops);
            }
            else
            {
                usr=buscarUsuario(usuario);
                UpdateOperations <Usuario> ops=ds.createUpdateOperations(Usuario.class).add("favoritosEstudio",sitiosEst.get(0));
                ds.update(usr,ops);
            }
        }
        if(restaurantes.size()>0)
        {
            List <Usuario> LUsr= ds.createQuery(Usuario.class).field("_id").equal(usuario)
                    .field("favoritosRestaurantes").equal(restaurantes.get(0)).asList();
            if(LUsr.size()>0)
            {
                usr=buscarUsuario(usuario);
                UpdateOperations <Usuario> ops=ds.createUpdateOperations(Usuario.class).removeAll("favoritosRestaurantes",restaurantes.get(0));
                ds.update(usr,ops);
            }
            else
            {
                usr=buscarUsuario(usuario);
                UpdateOperations <Usuario> ops=ds.createUpdateOperations(Usuario.class).add("favoritosRestaurantes",restaurantes.get(0));
                ds.update(usr,ops);
            }
        }
        if(fotocopiadora.size()>0)
        {
            List <Usuario> LUsr= ds.createQuery(Usuario.class).field("_id").equal(usuario)
                    .field("favoritosFotocopiadoras").equal(fotocopiadora.get(0)).asList();
            if(LUsr.size()>0)
            {
                usr=buscarUsuario(usuario);
                UpdateOperations <Usuario> ops=ds.createUpdateOperations(Usuario.class).removeAll("favoritosFotocopiadoras",fotocopiadora.get(0));
                ds.update(usr,ops);
            }
            else
            {
                usr=buscarUsuario(usuario);
                UpdateOperations <Usuario> ops=ds.createUpdateOperations(Usuario.class).add("favoritosFotocopiadoras",fotocopiadora.get(0));
                ds.update(usr,ops);
            }
        }

    }

    public boolean verificarFavorito(String usuario,String sitio)
    {
        List <SitiosEstudio> sitiosEst=ds.createQuery(SitiosEstudio.class).field("_id").equal(sitio).asList();
        List <Restaurante> restaurantes= ds.createQuery(Restaurante.class).field("_id").equal(sitio).asList();
        List <Fotocopiadora> fotocopiadora= ds.createQuery(Fotocopiadora.class).field("_id").equal(sitio).asList();
        Usuario usr;
        if(sitiosEst.size()>0)
        {
            List <Usuario> lUsr= ds.createQuery(Usuario.class).field("_id").equal(usuario)
                    .field("favoritosEstudio").equal(sitiosEst.get(0)).asList();
            if(lUsr.size()>0)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        if(restaurantes.size()>0)
        {
            List <Usuario> LUsr= ds.createQuery(Usuario.class).field("_id").equal(usuario)
                    .field("favoritosRestaurantes").equal(restaurantes.get(0)).asList();
            if(LUsr.size()>0)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        if(fotocopiadora.size()>0)
        {
            List <Usuario> LUsr= ds.createQuery(Usuario.class).field("_id").equal(usuario)
                    .field("favoritosFotocopiadoras").equal(fotocopiadora.get(0)).asList();
            if(LUsr.size()>0)
            {
                return true;
            }
            else
            {
               return false;
            }
        }
        return false;
    }

    /**
     * {@inheritDoc}
     * @param notificacion
     */
    public void agregarNotificacion(Notificacion notificacion)
    {
        if(notificacion!=null)
        {
            ds.save(notificacion);
        }
    }

    /**
     *{@inheritDoc}
     * @param usuario
     */
    public List<Notificacion> obtenerNotificacion(String usuario)
    {
        List<Notificacion> notificaciones=ds.createQuery(Notificacion.class).filter("destinatario",usuario).asList();
        if(notificaciones.size()>0)
            return notificaciones;
        return null;
    }

    /**
     * {@inheritDoc}
     * @param remitente
     * @return
     */
    public List<Notificacion> obtenerNotificacionRemitente(String remitente)
    {
        List<Notificacion> notificaciones=ds.createQuery(Notificacion.class).filter("remitente",remitente).asList();
        if(notificaciones.size()>0)
            return notificaciones;
        return null;
    }

    /**
     * {@inheritDoc}
     * @param sitio
     * @return
     */
    public Document obtenerSitio(String sitio)
    {
        List <SitiosEstudio> sitiosEst=ds.createQuery(SitiosEstudio.class).field("_id").equal(sitio).asList();
        List <Restaurante> restaurantes= ds.createQuery(Restaurante.class).field("_id").equal(sitio).asList();
        List <Fotocopiadora> fotocopiadora= ds.createQuery(Fotocopiadora.class).field("_id").equal(sitio).asList();
        if(sitiosEst.size()>0)
            return new Document("sitio",sitiosEst.get(0))
                    .append("latitud",sitiosEst.get(0).getLocalizacion().getLatitud())
                    .append("longitud",sitiosEst.get(0).getLocalizacion().getLongitud())
                    .append("tipoServicio","sitioEstudio");
        if(restaurantes.size()>0)
            return new Document("sitio",restaurantes.get(0))
                    .append("latitud",restaurantes.get(0).getLocalizacion().getLatitud())
                    .append("longitud",restaurantes.get(0).getLocalizacion().getLongitud())
                    .append("tipoServicio","restaurantes");
        if(fotocopiadora.size()>0)
            return new Document("sitio",fotocopiadora.get(0))
                    .append("latitud",fotocopiadora.get(0).getLocalizacion().getLatitud())
                    .append("longitud",fotocopiadora.get(0).getLocalizacion().getLongitud())
                    .append("tipoServicio","fotocopiadoras") ;
        return null;
    }

    /**
     * {@inheritDoc}
     * @param nombre
     * @param contacto
     * @return
     */
    public Document eliminarContacto(String nombre,String contacto)
    {
        Usuario usr=buscarUsuario(nombre);
        Usuario usrCont=buscarUsuario(contacto);
        if(usr!=null && usrCont!=null)
        {
            UpdateOperations <Usuario> ops=ds.createUpdateOperations(Usuario.class).removeAll("listaContactos",contacto);
            ds.update(usr,ops);
            return new Document("eliminacion",true);
        }
        return new Document("eliminacion",false);
    }

    /**
     * {@inheritDoc}
     * @param nombre
     * @param admin
     * @return
     */
    public Document eliminarGrupo(String nombre, String admin)
    {
        Usuario usr=buscarUsuario(nombre);
        if(usr.getAdministrador().equalsIgnoreCase(admin))
        {
            ds.findAndDelete(ds.createQuery(Usuario.class).filter("_id",nombre));
            return new Document("eliminacion",true);
        }
        return new Document("eliminacion",false);
    }
    /**
     * {@inheritDoc}
     * @param usuario
     */
    public void marcarnotificaciones(String usuario)
    {
        List<Notificacion> notificaciones= ds.createQuery(Notificacion.class).field("destinatario").equal(usuario).asList();
        if(notificaciones.size()>0)
        {
            UpdateOperations <Notificacion> ops=ds.createUpdateOperations(Notificacion.class).set("estadoLectura",true);
            ds.update(ds.createQuery(Notificacion.class).field("destinatario").equal(usuario),ops);
        }
    }
    /**
     * {@inheritDoc}
     * @param usuario
     */
    public long consultarNotificaciones(String usuario)
    {
        List<Notificacion> notificaciones= ds.createQuery(Notificacion.class).field("destinatario").equal(usuario)
                .field("estadoLectura").equal(false).asList();
        if(notificaciones.size()>0)
        {
            return notificaciones.size();
        }
        return 0;
    }
    /**
     * {@inheritDoc}
     * @param nombre
     * @return
     */
    public List<Usuario> obtenerContactos(String nombre)
    {
        List<Usuario> contactos= new ArrayList<Usuario>();
        Usuario usuario= buscarUsuario(nombre);
        if(usuario!=null && (usuario.getListaContactos().size()>0 || usuario.getListaContactos()!=null)) {
            for (String s: usuario.getListaContactos())
            {
                List<Usuario> contacto=ds.createQuery(Usuario.class).field("_id").equal(s)
                        .filter("restricciones.privacidad",true)
                        .field("tipoUsuario").equal("usuario").asList();
                if(contacto.size()>0)
                {
                    contactos.add(contacto.get(0));
                }
            }
            return contactos;

        }
        return null;
    }

    /**
     * {@inheritDoc}
     * @param nombre
     * @return
     */
    public List<String> obtenerListaContactos(String nombre)
    {
        List<String> contactos= new ArrayList<String>();
        Usuario usuario= buscarUsuario(nombre);
        if(usuario!=null && (usuario.getListaContactos().size()>0 || usuario.getListaContactos()!=null)) {
            for (String s: usuario.getListaContactos())
            {
                List<Usuario> contacto=ds.createQuery(Usuario.class).field("_id").equal(s)
                        .field("tipoUsuario").equal("usuario").asList();
                if(contacto.size()>0)
                {
                    contactos.add(contacto.get(0).getIdUsuario());
                }
            }
            return contactos;

        }
        return null;
    }
    /**
     *{@inheritDoc}
     * @param nombre
     * @return
     */
    public List<String> obtenerGrupos(String nombre)
    {
        List<String> grupos= new ArrayList<String>();
        Usuario usuario= buscarUsuario(nombre);
        if(usuario!=null && (usuario.getListaContactos().size()>0 || usuario.getListaContactos()!=null)) {
            for (String s: usuario.getListaContactos())
            {
                List<Usuario> contacto=ds.createQuery(Usuario.class).field("_id").equal(s)
                        .field("tipoUsuario").equal("grupo").asList();
                if(contacto.size()>0)
                {
                    System.out.println("entro");
                    grupos.add(contacto.get(0).getIdUsuario());
                }
            }
            return grupos;

        }
        return null;
    }

    /**
     * {@inheritDoc}
     * @param usuario
     * @param contacto
     */
    public void actualizarListaContactosGrupo(String usuario, String contacto)
    {
        Usuario us=buscarUsuario(usuario);
        Usuario contc=buscarUsuario(contacto);
        UpdateOperations <Usuario> ops=ds.createUpdateOperations(Usuario.class).add("listaContactos",contacto);
        UpdateOperations <Usuario> ops2=ds.createUpdateOperations(Usuario.class).add("listaContactos",usuario);
        ds.update(us,ops);
        if(contacto!=null)
            ds.update(contc,ops2);

    }
    public List<Usuario> obtenerIntegrantes(String grupo)
    {
        List<Usuario> contactos= new ArrayList<Usuario>();
        Usuario usuario= buscarUsuario(grupo);
        if(usuario!=null && (usuario.getListaContactos().size()>0 || usuario.getListaContactos()!=null)) {
            for (String s: usuario.getListaContactos())
            {
                List<Usuario> contacto=ds.createQuery(Usuario.class).field("_id").equal(s)
                        .field("tipoUsuario").equal("usuario").asList();
                if(contacto.size()>0)
                {
                    contactos.add(contacto.get(0));
                }
            }
            return contactos;

        }
        return null;
    }

    /**
     * {@inheritDoc}
     * @param grupo
     */
    public void actualizarGrupo(Usuario grupo)
    {
        if(grupo!=null)
        {
            ds.save(grupo);
        }
    }

    /**
     * {@inheritDoc}
     * @param grupo
     * @return
     */
    public Usuario obtenerInfoGrupo(String grupo)
    {
        if(grupo!=null)
        {
            return ds.createQuery(Usuario.class).field("_id").equal(grupo).asList().get(0);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     * @param historial
     */
    public void agregarHistorialSitiosEstudio(HistorialSitios historial)
    {
        if(historial!=null)
        {
            ds.save(historial);
        }
    }

    /**
     * {@inheritDoc}
     * @param historial
     */
    public void agregarHistorialRestaurante(HistorialRestaurantes historial)
    {
        if(historial!=null)
        {
            ds.save(historial);
        }
    }

    /**
     * {@inheritDoc}
     * @param historial
     */
    public void agregarHistorialFotocopiadora(HistorialFotocopiadoras historial)
    {
        if(historial!=null)
        {
            ds.save(historial);
        }
    }
}
