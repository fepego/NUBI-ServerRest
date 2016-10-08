package com.nubi.ServicioREST;





import com.nubi.ModuloAdaptacion.Candidato;
import com.nubi.Utils.Calculador;
import com.nubi.colecciones.*;
import com.nubi.controladorServicios.ServiciosNUBI;
import com.nubi.controladorServicios.ServiciosNUBIImp;
import org.bson.Document;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import java.util.List;

/**
 * Created by Felipe on 22/09/2016.
 */
@Path("/nubi")
public class rsNubi {
    private ServiciosNUBI servicios;
    @GET
    @Path("/filtrado-sitiosestudio")
    @Produces("application/json")
    public List<Candidato> sitiosEstudio(@QueryParam("usuario")String nombreUsuario,@QueryParam("latitud") double latitud, @QueryParam("longitud") double longitud) {
        servicios = new ServiciosNUBIImp();
        String nombreUsu= new String(nombreUsuario);
        List<Candidato> candidatos=servicios.filtrarSitiosEstudio(nombreUsu, latitud, longitud);
        return candidatos;
    }
    @GET
    @Path("/filtrado-restaurante")
    @Produces("application/json")
    public List<Candidato> restaurantes(@QueryParam("usuario")String nombreUsuario,@QueryParam("latitud") double latitud, @QueryParam("longitud") double longitud) {
        servicios = new ServiciosNUBIImp();
        String nombreUsu= new String(nombreUsuario);
        List<Candidato> candidatos=servicios.filtrarRestaurantes(nombreUsu, latitud,longitud);
        return candidatos;
    }
    @GET
    @Path("/filtrado-fotocopiadora")
    @Produces("application/json")
    public List<Candidato> fotocopiadoras(@QueryParam("usuario")String nombreUsuario,@QueryParam("latitud") double latitud, @QueryParam("longitud") double longitud) {
        servicios = new ServiciosNUBIImp();
        String nombreUsu= new String(nombreUsuario);
        List<Candidato> candidatos=servicios.filtrarFotocopiadora(nombreUsu, latitud,longitud);
        return candidatos;
    }
    @GET
    @Path("/validar-nombreusuario")
    @Produces("application/json")
    public Usuario buscarUsuario(@QueryParam("usuario")String nombreUsuario) {
        servicios = new ServiciosNUBIImp();
        Usuario u=servicios.verificarUsuario(nombreUsuario);
        return u;
    }
    //Registro en el sistema
    @GET
    @Path("/validar-usuario")
    @Produces("application/json")
    public Document validarCuenta(@QueryParam("usuario")String nombreUsuario, @QueryParam("pass") String pass) {
        servicios = new ServiciosNUBIImp();
        System.out.println(pass);
        return servicios.verificarCorreo(nombreUsuario,pass);
    }
    //Verificar login de usuario
    @GET
    @Path("/verificar-usuario")
    @Produces("application/json")
    public Document verificarUsuario(@QueryParam("usuario")String nombreUsuario, @QueryParam("pass") String pass) {
        servicios = new ServiciosNUBIImp();

        return servicios.verificarUsuario(nombreUsuario,pass);


    }

    /**
     * Carga los principales servicios que pueden ser de interes para el usuario
     * @param nombreUsuario
     * @param latitud
     * @param longitud
     * @return
     */
    @GET
    @Path("/servicios-login")
    @Produces("application/json")
    public List<Candidato> cargarServicios(@QueryParam("usuario")String nombreUsuario, @QueryParam("latitud") double latitud,@QueryParam("longitud") double longitud) {
        servicios = new ServiciosNUBIImp();
        return servicios.sitiosPrincipales(nombreUsuario,latitud,longitud);


    }

    /**
     * Agregar las preferencias de usuario generado por el menú de configuración.
     * @param nombreUsuario
     * @param distancia
     * @param tolerancia
     * @param disponibilidad
     * @param tiempoMin
     * @param tiempoMax
     * @param ruido
     * @param espacio
     * @param tipoFtdr
     * @return
     */
    @GET
    @Path("/agregar-preferencias")
    @Produces("application/json")
    public Usuario agregarPreferencias(@QueryParam("usuario")String nombreUsuario,
                                               @QueryParam("distancia") String distancia,@QueryParam("DistTolerancia") double tolerancia,
                                               @QueryParam("disponibilidad") String disponibilidad, @QueryParam("tiempoMin") double tiempoMin,
                                               @QueryParam("tiempoMax") double tiempoMax, @QueryParam("tolerancia") String ruido,
                                               @QueryParam("tipoEspacio") String espacio, @QueryParam("fotocopiadora") String tipoFtdr) {
        servicios = new ServiciosNUBIImp();
        double dist= Calculador.distancia(distancia );
        Preferencia preferencias= new Preferencia(tiempoMin,tiempoMax,disponibilidad,dist,tolerancia,ruido,espacio,tipoFtdr);

        return servicios.agregarPreferencias(nombreUsuario,preferencias);

    }
    @GET
    @Path("/agregar-restricciones")
    @Produces("application/json")
    public Usuario agregarRestricciones(@QueryParam("usuario")String nombreUsuario,
                                       @QueryParam("privacidad") boolean privacidad,@QueryParam("movilidad") String movilidad) {
        servicios = new ServiciosNUBIImp();
        Restriccion rest;
        if(movilidad.equalsIgnoreCase("No"))
        {
            rest= new Restriccion(new Boolean(false),privacidad);
        }
        else
        {
            rest= new Restriccion(new Boolean(true),movilidad,privacidad);
        }
        return servicios.agregarRestricciones(nombreUsuario,rest);

    }
    /**
     * Agregar un contacto a la lista de amigos del usuario.
     * @param nombreUsuario
     * @param contacto
     * @return
     */
    @GET
    @Path("/agregar-contacto")
    @Produces("application/json")
    public Usuario agregarContacto(@QueryParam("usuario")String nombreUsuario, @QueryParam("contacto") String contacto) {
        servicios = new ServiciosNUBIImp();
        return servicios.agregarContacto(nombreUsuario,contacto);


    }

    /**
     * Metodo para agrgar un grupo a BD
     * @param nombreGrupo
     * @return
     */
    @GET
    @Path("/crear-grupo")
    @Produces("application/json")
    public Document crearGrupo(@QueryParam("usuario")String nombreGrupo,@QueryParam("admin") String admin) {
        servicios = new ServiciosNUBIImp();
        return servicios.crearGrupo(nombreGrupo,admin);


    }

    /**
     * Método para consultar alertas realizadas a un sitio en un intervalo de 15 minutos
     * @param sitio
     * @return
     */
    @GET
    @Path("/comentarios")
    @Produces("application/json")
    public List<Alerta> consultarComentarios(@QueryParam("sitio")String sitio) {
        servicios = new ServiciosNUBIImp();
        return servicios.obtenerInfoSitio(sitio);


    }

    /**
     * Método para agregar una alerta para sitios de estudio
     * @param sitio
     * @return
     */
    @GET
    @Path("/alerta-sitioest")
    @Produces("application/json")
    public Document alertaSitiosEst(@QueryParam("sitio")String sitio,@QueryParam("comentario")String comentario,@QueryParam("estado")String estado ) {
        servicios = new ServiciosNUBIImp();
        return servicios.insertarAlertaSitioEstudio(sitio,comentario,estado);
    }
    /**
     * Método para agregar una alerta para restaurante
     * @param sitio
     * @return
     */
    @GET
    @Path("/alerta-restaurante")
    @Produces("application/json")
    public Document alertaRestaurante(@QueryParam("sitio")String sitio,@QueryParam("comentario")String comentario,@QueryParam("estado")String estado ) {
        servicios = new ServiciosNUBIImp();
        return servicios.insertarAlertaRestaurante(sitio,comentario,estado);
    }
    /**
     * Método para agregar una alerta para fotocopiadora
     * @param sitio
     * @return
     */
    @GET
    @Path("/alerta-fotocopiadora")
    @Produces("application/json")
    public Document alertaFotocopiadora(@QueryParam("sitio")String sitio,@QueryParam("comentario")String comentario,@QueryParam("estado")String estado ) {
        servicios = new ServiciosNUBIImp();
        return servicios.insertarAlertaFotocopiadora(sitio,comentario,estado);
    }
    @GET
    @Path("/disponibilidad")
    @Produces("application/json")
    public Document disponibilidadSitio(@QueryParam("sitio")String sitio) {
        servicios = new ServiciosNUBIImp();
        return servicios.consultarDisponibilidad(sitio);
    }

    /**
     * Método para retroalimentación de las alertas (like y erronea)
     * @param sitio
     * @param id
     * @param tipo
     * @return
     */
    @GET
    @Path("/retroalimentacion-alerta")
    @Produces("application/json")
    public List<Alerta> retroalimentacionAlerta(@QueryParam("sitio")String sitio,@QueryParam("id")String id,@QueryParam("tipo")boolean tipo) {
        servicios = new ServiciosNUBIImp();
        servicios.retroalimentacionAlerta(id,tipo);
        return servicios.obtenerInfoSitio(sitio);
    }

    /**
     * Metodo para verificar si un sitio es favorito de la lista de favoritos del usuario.
     * @param usuario
     * @param sitio
     */
    @GET
    @Path("/verificar-favorito")
    @Produces("application/json")
    public Document verificarFavorito(@QueryParam("usuario")String usuario,@QueryParam("sitio")String sitio) {
        servicios = new ServiciosNUBIImp();
        return servicios.verificarSitioFavorito(usuario,sitio);

    }

    /**
     * Metodo para agregar/eliminar un sitio favorito de la lista de favoritos del usuario.
     * @param usuario
     * @param sitio
     * @return
     */
    @GET
    @Path("/agregar-favorito")
    @Produces("application/json")
    public Document agregarFavorito(@QueryParam("usuario")String usuario,@QueryParam("sitio")String sitio) {
        servicios = new ServiciosNUBIImp();
        servicios.sitioFavorito(usuario,sitio);
        return servicios.verificarSitioFavorito(usuario,sitio);

    }
    /**
     * Metodo para agregar comentario
     * @param remitente
     * @param destinatario
     * @param tipo
     * @param comentario
     * @return
     */
    @GET
    @Path("/agregar-notificacion")
    @Produces("application/json")
    public Document agregarNotificacion(@QueryParam("remitente")String remitente,@QueryParam("destinatario")String destinatario,
                                        @QueryParam("tipo") String tipo, @QueryParam("comentario") String comentario) {
        servicios = new ServiciosNUBIImp();
        return servicios.agregarNotificacion(remitente,destinatario,comentario,tipo);

    }

    /**
     * Metodo para consultar las notificaciones de usuario-destinatario
     * @param usuario
     * @return
     */
    @GET
    @Path("/consultar-notifdestinatario")
    @Produces("application/json")
    public List<Notificacion> obtenerNotificacionDestinatario(@QueryParam("usuario")String usuario) {
        servicios = new ServiciosNUBIImp();
        return servicios.obtenerNotificacionesDestinatario(usuario);

    }
    /**
     * Metodo para consultar las notificaciones de usuario-remitente
     * @param usuario
     * @return
     */
    @GET
    @Path("/consultar-notifremitente")
    @Produces("application/json")
    public List<Notificacion> obtenerNotificacionRemitente(@QueryParam("usuario")String usuario) {
        servicios = new ServiciosNUBIImp();
        return servicios.obtenerNotificacionesRemitente(usuario);

    }
    @GET
    @Path("/consultar-ruta")
    @Produces("application/json")
    public Ruta obtenerNotificacionRemitente(@QueryParam("latitud") double latitud, @QueryParam("longitud") double longitud, @QueryParam("sitio") String sitio) {
        servicios = new ServiciosNUBIImp();
        return servicios.obtenerRuta(latitud,longitud,sitio);

    }
}
