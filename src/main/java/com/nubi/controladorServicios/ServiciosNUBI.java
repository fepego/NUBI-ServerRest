package com.nubi.controladorServicios;



import com.nubi.ModuloAdaptacion.Candidato;
import com.nubi.colecciones.*;
import com.sun.research.ws.wadl.Doc;
import org.bson.Document;

import java.util.Date;
import java.util.List;
import java.util.prefs.NodeChangeEvent;

/**
 * Created by Felipe on 22/09/2016.
 */
public interface ServiciosNUBI {

    /**
     * Método para filtrar sitios de estudio de acuerdo a preferencias de usuario y contexto.
     * @param nombreUsuario
     * @param latitud
     * @param longitud
     * @return
     */
    public List<Candidato> filtrarSitiosEstudio(String nombreUsuario, double latitud, double longitud);
    public List<Candidato> filtrarRestaurantes(String nombreUsuario, double latitud, double longitud);
    public List<Candidato> filtrarFotocopiadora(String nombreUsuario, double latitud, double longitud);

    /**
     * Verificar cuenta javeriana desde modulo, solo retorna JSON boolean
     * @param nombreUsuario
     * @return
     */
    public Usuario verificarUsuario(String nombreUsuario);

    /**
     * Método para validar cuenta javeriana y creación de usuario en BD de NUBI
     * @param nombreUsuario
     * @param pass
     * @return
     */
    public Document verificarUsuario(String nombreUsuario, String pass);

    /**
     * Login de usuario
     * @param nombreUsuario
     * @param pass
     * @return
     */
    public Document verificarCorreo(String nombreUsuario, String pass);
    public List<Candidato> sitiosPrincipales(String nombreusuario, double latitud, double longitud);

    /**
     * Método para agregar un contacto a la lista de amigos del usuario
     * @param nombreUsuario
     * @param nombreContacto
     * @return
     */
    public Usuario agregarContacto(String nombreUsuario, String nombreContacto);

    /**
     * Método para actualizar el nodo de usuario dadas las preferencias.
     * @param preferencias
     */
    public Usuario agregarPreferencias(String usuario, Preferencia preferencias);
    /**
     * Método para actualizar el nodo de usuario dadas las restricciones.
     * @param restricciones
     */
    public Usuario agregarRestricciones(String usuario, Restriccion restricciones);
    /**
     * Método para crear grupos en la BD.
     * @param nombreGrupo
     */
    public Document crearGrupo(String nombreGrupo,String administrador);

    /**
     * Método que retorna
     * @param nombre
     * @return
     */
    public List<Alerta> obtenerInfoSitio(String nombre);

    /**
     * Método para agregar un comentario de Sitio Estudio
     * @param comentario
     * @param estado
     * @return
     */
    public Document insertarAlertaSitioEstudio(String sitio,String comentario, String estado);
    /**
     * Método para agregar un comentario de restaurante
     * @param comentario
     * @param estado
     * @return
     */
    public Document insertarAlertaRestaurante(String sitio,String comentario, String estado);
    /**
     * Método para agregar un comentario de Fotocopiadora
     * @param comentario
     * @param estado
     * @return
     */
    public Document insertarAlertaFotocopiadora(String sitio,String comentario, String estado);

    /**
     * Metodo que retorna la disponibilidad del sitio dado el nombre
     * @param sitio
     * @return
     */
    public Document consultarDisponibilidad(String sitio);

    /**
     * Método para duplicar/modificar una alerta existente
     * @param id
     * @param tipo
     */
    public void retroalimentacionAlerta(String id, boolean tipo);

    /**
     * Método para agregar/eliminar favorito de la lista de favoritos del usuario
     * @param usuario
     * @param sitio
     */
    public void sitioFavorito(String usuario, String sitio);

    /**
     * Método para verificar si el sitio pertenece a la lista de favoritos del usuario
     * @param usuario
     * @param sitio
     * @return
     */
    public Document verificarSitioFavorito(String usuario, String sitio);

    /**
     * Metodo para agregar una notificación en la BD.
     * @param remitente
     * @param destinatario
     * @param comentario
     * @param tipo
     * @return
     */
    public Document agregarNotificacion(String remitente, String destinatario, String comentario, String tipo);

    /**
     * Método para recuperar notificaciones en BD de un usuario.
     * @param usuario
     * @return
     */
    public List<Notificacion> obtenerNotificacionesDestinatario(String usuario);
    /**
     * Método para recuperar notificaciones realizadas por un usuario.
     * @param usuario
     * @return
     */
    public List<Notificacion> obtenerNotificacionesRemitente(String usuario);

    /**
     *
     * @param latitudIni
     * @param longitudIni
     * @param sitio
     * @return
     */
    public Ruta obtenerRuta(double latitudIni, double longitudIni,String sitio);

    /**
     * Metodo para agregar una alerta
     * @param sitio
     * @param comentario
     * @param estado
     * @return
     */
    public Document agregarAlerta(String sitio,String comentario, String estado);

    /**
     * Metodo para retornar lista de contactos de usuario o grupo
     * @param usuario
     * @return
     */
    public List<String> verContactos(String usuario);

    /**
     * Método para eliminar un contacto
     * @param usuario
     * @param contacto
     * @return
     */
    public Document eliminarContacto(String usuario, String contacto);

    /**
     * Metodo para eliminar el grupo
     * @param nombreGrupo
     * @param administrador
     * @return
     */
    public Document eliminarGrupo(String nombreGrupo, String administrador);

    /**
     * Método para contar el notificaciones no leidas.
     * @param usuario
     * @return
     */
    public Document consultarNumNotificaciones(String usuario);

    /**
     * Método para obtener los contactos y su información.
     * @param nombre
     * @return
     */
    public List<Usuario> obtenerContactos(String nombre);

    /**
     * Metodo para obtener grupos del usuario
     * @param nombre
     * @return
     */
    public List<String> obtenerGrupos(String nombre);

    /**
     * agrega usuario a grupo y el grupo a lista contactos de usuario
     * @param grupo
     * @param usuario
     */
    public void agregarContactoGrupo(String grupo, String usuario);

    /**
     * Método para agregar una notificación a todos los del grupo.
     * @param grupo
     */
    public Document agregarNotificiacionBroadCast(String grupo, String comentario, String sitio );

    /**
     * Metodo para activar el mecanismo de retroalimentaciòn en NUBI desde REST
     */
    public Document activarRetroalimentacion(boolean estado);

}
