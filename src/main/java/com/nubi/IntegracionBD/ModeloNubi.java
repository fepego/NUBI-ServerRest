package com.nubi.IntegracionBD;


import com.nubi.ModuloAdaptacion.resultadoHistorico;
import com.nubi.colecciones.*;
import org.bson.Document;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Felipe on 20/09/2016.
 */
public interface ModeloNubi {
    public Iterator<SitiosEstudio> semillaSitiosEst(String tipoDia);
    public boolean agregarAlerta(Alerta Alt);
    public boolean addHistoricoSitioEst(HistorialSitios historico);
    public Iterator<Alerta> consultarAlertas(String estado, String nombreSitio);
    public Iterator<HistorialSitios> consultarHistoricoSitioEst(String id);
    public int contadorAlertas(String estado, String nombreSitio);
    public Iterator <resultadoHistorico>  getHistoricoSitioEst(String id);
    public void actualizarSitioEstudio(String nombreSitio, double disponibilidad);
    public void InsertarRuta(Ruta ruta);
    public Iterator <Ruta> buscarRuta(double latInicio, double longInicio, double latDestino, double longDestino);
    public  List<SitiosEstudio> buscarSitioEstudio();
    public Usuario buscarUsuario(String usuario);
    public void actualizarLocUsuario(String nombre, double latitud, double longitud);
    public List<Restaurante> buscarRestaurantes();
    public  List<Fotocopiadora> buscarFotocopiadoras();
    public void agregarFotocopiadora(Fotocopiadora ft);
    public void agregarusuario (Usuario u);
    public List<Usuario> validarLogin(String usuario, String pass);

    /**
     * Metodo de modelo para persistir nueva lista de contactos en la base de datos.
     * @param usuario
     * @param contacto
     */
    public void actualizarListaContactos(String usuario, String contacto);

    /**
     * Método para agregar o modificar las preferencias dado el nombre de usuario.
     * @param nombreUsuario
     * @param preferencias
     */
    public void agregarPreferencias(String nombreUsuario, Preferencia preferencias);
    /**
     * Método para agregar o modificar las preferencias dado el nombre de usuario.
     * @param nombreUsuario
     * @param restricciones
     */
    public void agregarRestricciones(String nombreUsuario, Restriccion restricciones);
    /**
     * Método para agregar un grupo a BD.
     * @param nombre
     */
    public void agregarGrupo(String nombre,String adminstrador);

    /**
     * Método que retorna los comentarios generados asociados a un sitio
     * @param nombreSitio
     * @return
     */
    public  List<Alerta> consultarAlerta(String nombreSitio);

    /**
     * Metodo para retornar la disponibilidad de un sitio dado su nombre
     * @param sitio
     * @return
     */
    public Document disponibilidadSitio(String sitio);

    /**
     * Metodo para duplicar / eliminar comentario por retroalimentación
     * @param id
     * @param tipo
     */
    public void retroalimentacionAlerta(String id, boolean tipo);

    /**
     * Método para agregar o eliminar favorito de la lista de favoritos del usuario
     * @param usuario
     * @param sitio
     */
    public void agregarFavorito(String usuario, String sitio);

    /**
     * Método para verificar si el sitio esta en lista de favoritos.
     * @param usuario
     * @param sitio
     * @return
     */
    public boolean verificarFavorito(String usuario,String sitio);

    /**
     * Método para agregar una nueva notificación en BD
     * @param notificacion
     */
    public void agregarNotificacion(Notificacion notificacion);

    /**
     * Método para obtener las notificaciones de un usuario(recupera notificaciones de destinatario)
     * @param usuario
     */
    public List<Notificacion> obtenerNotificacion(String usuario);
    /**
     * Método para obtener las notificaciones de un usuario-remitente(recupera notificaciones de Remitente)
     * @param remitente
     */
    public List<Notificacion> obtenerNotificacionRemitente(String remitente);

    /**
     * Método que ayuda a obtener el sitio dado el nombre
     * @param sitio
     * @return
     */
    public Document obtenerSitio(String sitio);

    /**
     * Método para eliminar un contacto
     * @param nombre
     * @return
     */
    public Document eliminarContacto(String nombre,String contacto);

    /**
     * Metodo
     * @param nombre
     * @param admin
     * @return
     */
    public Document eliminarGrupo(String nombre, String admin);


}
