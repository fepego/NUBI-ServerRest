package com.nubi.controladorServicios;



import com.nubi.IntegracionBD.ModeloNubi;
import com.nubi.IntegracionBD.ModeloNubiImp;
import com.nubi.IntegracionJaveriana.UsuarioJaveriana;
import com.nubi.IntegracionMapzen.Mapzen;
import com.nubi.ModuloAdaptacion.*;
import com.nubi.Utils.Calculador;
import com.nubi.colecciones.*;
import org.bson.Document;

import javax.ws.rs.QueryParam;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Felipe on 22/09/2016.
 */
public class ServiciosNUBIImp implements ServiciosNUBI {
    private ModeloNubi mod;

    public ServiciosNUBIImp() {
        mod= new ModeloNubiImp();
    }

    /**
     * {@inheritDoc}
     */
    public List<Candidato> filtrarSitiosEstudio(String nombreUsuario, double latitud, double longitud)
    {

        mod.actualizarLocUsuario(nombreUsuario,latitud,longitud);

        List <Candidato> candidatos= FiltradoSitiosEstudio.EjecutarReglasSitioEstudio(nombreUsuario);
        return candidatos;
    }
    public List<Candidato> filtrarRestaurantes(String nombreUsuario, double latitud, double longitud)
    {
        mod.actualizarLocUsuario(nombreUsuario,latitud,longitud);
        List <Candidato> candidatos= FiltradoRestaurante.EjecutarReglasRestaurante(nombreUsuario);
        return candidatos;
    }
    public List<Candidato> filtrarFotocopiadora(String nombreUsuario, double latitud, double longitud)
    {
        mod.actualizarLocUsuario(nombreUsuario,latitud,longitud);
        List <Candidato> candidatos= FiltradoFotocopiadora.EjecutarReglasFotocopiadora(nombreUsuario);
        return candidatos;
    }

    public Usuario verificarUsuario(String nombreUsuario) {
        Usuario u=mod.buscarUsuario(nombreUsuario);
        if(u!=null)
        {
            System.out.println("si existe el usuario");
            return u;
        }

        return null;
    }

    /**
     *{@inheritDoc}
     * @param nombreUsuario
     * @param pass
     * @return
     */
    public Document verificarUsuario(String nombreUsuario,String pass) {
        List <Usuario> u=mod.validarLogin(nombreUsuario,pass);
        Document resp;
        if(u.size()>0)
        {
            resp= new Document("login",true);
            return resp;
        }
        resp= new Document("login",false);
        return resp;
    }

    /**
     *{@inheritDoc}
     * @param nombreUsuario
     * @param pass
     * @return
     */
    public Document verificarCorreo(String nombreUsuario, String pass)
    {
        Usuario u=mod.buscarUsuario(nombreUsuario);
        Document resp;
        if(u!=null)
        {
            resp= new Document("Cuenta",false);
            return resp;
        }
        if(UsuarioJaveriana.ObtenerCuenta(nombreUsuario) && u == null)
        {
            Usuario usu= new Usuario(nombreUsuario,pass,new String("usuario"));
            mod.agregarusuario(usu);
            resp= new Document("Cuenta",true);
            return resp;
        }
        if(!UsuarioJaveriana.ObtenerCuenta(nombreUsuario))
        {
            resp= new Document("validacionCuenta",false);
            return resp;
        }
        return null;
    }

    /**
     *
     * @param nombreusuario
     * @param latitud
     * @param longitud
     * @return
     */
    public List<Candidato> sitiosPrincipales(String nombreusuario, double latitud, double longitud)
    {
        List<Candidato>restaurantes=filtrarRestaurantes(nombreusuario,latitud,longitud);
        List<Candidato>fotocopiadoras=filtrarFotocopiadora(nombreusuario,latitud,longitud);
        List<Candidato>sitiosEstudio=filtrarSitiosEstudio(nombreusuario,latitud,longitud);
        List<Candidato> principales=new ArrayList<Candidato>();
        if(restaurantes.size()>0 && fotocopiadoras.size()>0 && sitiosEstudio.size()>0)
        {
            principales.add(restaurantes.get(0));
            principales.add(fotocopiadoras.get(0));
            principales.add(sitiosEstudio.get(0));
            return principales;
        }
        return null;
    }
    public Usuario agregarContacto(String nombreUsuario, String nombreContacto){
        Usuario  usuario=mod.buscarUsuario(nombreUsuario);
        Usuario contacto= mod.buscarUsuario(nombreContacto);
        if(usuario!=null && contacto!=null)
        {
            if(usuario.getListaContactos()!=null)
            {
                usuario.getListaContactos().add(nombreContacto);
                mod.actualizarListaContactos(nombreUsuario,nombreContacto);
                return usuario;
            }
            else
            {
                usuario.setListaContactos(new ArrayList<String>());
                usuario.getListaContactos().add(nombreContacto);
                mod.actualizarListaContactos(nombreUsuario,nombreContacto);
                return usuario;
            }
        }

        return null;
    }

    /**
     * {@inheritDoc}
     * @param preferencias
     */
    public Usuario agregarPreferencias(String usuario, Preferencia preferencias){
        Usuario usu= mod.buscarUsuario(usuario);
        if(usu!=null && preferencias!=null)
        {
            mod.agregarPreferencias(usuario,preferencias);
        }
        System.out.println("numero de contactos"+mod.buscarUsuario(usuario).getListaContactos().size());
        return mod.buscarUsuario(usuario);
    }

    /**
     * {@inheritDoc}
     * @param usuario
     * @param restricciones
     * @return
     */
    public Usuario agregarRestricciones(String usuario, Restriccion restricciones)
    {
        Usuario usu= mod.buscarUsuario(usuario);
        if(usu!=null && restricciones!=null)
        {
            mod.agregarRestricciones(usuario,restricciones);
        }
        System.out.println("numero de contactos"+mod.buscarUsuario(usuario).getListaContactos().size());
        return mod.buscarUsuario(usuario);
    }
    /**
     * {@inheritDoc}
     * @param nombreGrupo
     * @return
     */
    public Document crearGrupo(String nombreGrupo,String administrador)
    {
        Usuario u=mod.buscarUsuario(nombreGrupo);
        if(u==null)
        {
            mod.agregarGrupo(nombreGrupo,administrador);
            return new Document("validacionGrupo",true);
        }
        return new Document("validacionGrupo",false);
    }

    /**
     * {@inheritDoc}
     * @param nombre
     * @return
     */
    public List<Alerta> obtenerInfoSitio(String nombre)
    {
        return mod.consultarAlerta(nombre);
    }

    /**
     * {@inheritDoc}
     * @param sitio
     * @param comentario
     * @param estado
     * @return
     */
    public Document insertarAlertaSitioEstudio(String sitio,String comentario, String estado)
    {

        if(estado!=null || estado.equalsIgnoreCase("Libre") || estado.equalsIgnoreCase("Medio") || estado.equalsIgnoreCase("LLeno"))
        {

            SitiosEstudio st= new SitiosEstudio();
            st.setNombre(sitio);
            Alerta alterta= new Alerta(new Date(), Calculador.horaConsulta(0),estado,comentario,st);
            mod.agregarAlerta(alterta);
            return new Document("confirmacion",true);

        }
        return new Document("confirmacion",false);
    }

    /**
     * {@inheritDoc}
     * @param sitio
     * @param comentario
     * @param estado
     * @return
     */
    public Document insertarAlertaRestaurante(String sitio,String comentario, String estado)
    {
        if(estado!=null || estado.equalsIgnoreCase("Libre") || estado.equalsIgnoreCase("Medio") || estado.equalsIgnoreCase("LLeno"))
        {

            Restaurante restaurante= new Restaurante();
            restaurante.setNombre(sitio);
            Alerta alterta= new Alerta(new Date(), Calculador.horaConsulta(0),estado,comentario,restaurante);
            mod.agregarAlerta(alterta);
            return new Document("confirmacion",true);

        }
        return new Document("confirmacion",false);
    }

    /**
     * {@inheritDoc}
     * @param sitio
     * @param comentario
     * @param estado
     * @return
     */
    public Document insertarAlertaFotocopiadora(String sitio,String comentario, String estado)
    {
        if(estado!=null || estado.equalsIgnoreCase("Libre") || estado.equalsIgnoreCase("Medio") || estado.equalsIgnoreCase("LLeno"))
        {

            Fotocopiadora fotocopiadora=  new Fotocopiadora();
            fotocopiadora.setNombre(sitio);
            Alerta alterta= new Alerta(new Date(), Calculador.horaConsulta(0),estado,comentario,fotocopiadora);
            mod.agregarAlerta(alterta);
            return new Document("confirmacion",true);

        }
        return new Document("confirmacion",false);
    }

    /**
     * {@inheritDoc}
     * @param sitio
     * @return
     */
    public Document consultarDisponibilidad(String sitio)
    {
        if(sitio!=null)
        {
            return mod.disponibilidadSitio(sitio);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     * @param id
     * @param tipo
     */
    public void retroalimentacionAlerta(String id, boolean tipo)
    {
        if(id!=null)
        {
            mod.retroalimentacionAlerta(id,tipo);
        }
    }
    public void sitioFavorito(String usuario, String sitio)
    {
        if(usuario!=null && sitio!=null)
        {
            mod.agregarFavorito(usuario,sitio);
        }
    }

    /**
     * {@inheritDoc}
     * @param usuario
     * @param sitio
     * @return
     */
    public Document verificarSitioFavorito(String usuario, String sitio)
    {
        if(usuario!=null && sitio!=null)
        {
            if(mod.verificarFavorito(usuario,sitio))
            {
                return new Document("favorito",true);
            }
            return new Document("favorito",false);
        }
        return new Document("favorito",false);
    }

    /**
     * {@inheritDoc}
     * @param remitente
     * @param destinatario
     * @param comentario
     * @param tipo
     * @return
     */
    public Document agregarNotificacion(String remitente, String destinatario, String comentario, String tipo)
    {
        if(remitente!=null && destinatario!=null && comentario!=null && tipo !=null)
        {
            Notificacion notificacion= new Notificacion(tipo,remitente,destinatario,comentario,false,new Date());
            mod.agregarNotificacion(notificacion);
            return new Document("notificacion",true);
        }
        return new Document("notificacion",false);
    }

    /**
     * {@inheritDoc}
     * @param usuario
     * @return
     */
    public List<Notificacion> obtenerNotificacionesDestinatario(String usuario)
    {
        if(usuario!=null)
        {
            mod.marcarnotificaciones(usuario);
            return mod.obtenerNotificacion(usuario);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     * @param usuario
     * @return
     */
    public List<Notificacion> obtenerNotificacionesRemitente(String usuario)
    {
        if(usuario!=null)
        {
            return mod.obtenerNotificacionRemitente(usuario);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     * @param latitudIni
     * @param longitudIni
     * @param sitio
     * @return
     */
    public Ruta obtenerRuta(double latitudIni, double longitudIni,String sitio)
    {
        if(latitudIni!=0 && longitudIni!=0 &&sitio!=null)
        {
            Document sit=mod.obtenerSitio(sitio);
            Ruta ruta= Mapzen.balanceador(latitudIni,longitudIni,new Double(sit.get("latitud").toString()),new Double(sit.get("longitud").toString()));
            return ruta;
        }
        return null;
    }

    /**
     * {@inheritDoc}
     * @param sitio
     * @param comentario
     * @param estado
     * @return
     */
    public Document agregarAlerta(String sitio,String comentario, String estado)
    {
        Document tipoLugar= mod.obtenerSitio(sitio);
        if(tipoLugar.get("tipoServicio").toString().equalsIgnoreCase("sitioEstudio"))
            return insertarAlertaSitioEstudio(sitio,comentario,estado);
        if(tipoLugar.get("tipoServicio").toString().equalsIgnoreCase("restaurantes"))
            return insertarAlertaRestaurante(sitio,comentario,estado);
        if(tipoLugar.get("tipoServicio").toString().equalsIgnoreCase("fotocopiadoras"))
            return insertarAlertaFotocopiadora(sitio,comentario,estado);
        return new Document("confirmacion",false);
    }

    /**
     * {@inheritDoc}
     * @param usuario
     * @return
     */
    public List<String> verContactos(String usuario)
    {
        if(usuario!=null)
        {
            Usuario usr=mod.buscarUsuario(usuario);
            if(usr!=null)
            {
                return mod.obtenerListaContactos(usuario);
            }
        }
        return null;
    }

    /**
     * {@inheritDoc}
     * @param usuario
     * @param contacto
     * @return
     */
    public Document eliminarContacto(String usuario, String contacto)
    {
        if(usuario!=null && contacto!=null)
        {
            Document verif=mod.eliminarContacto(usuario,contacto);
            if(verif.getBoolean("eliminacion"))
            {
                return mod.eliminarContacto(contacto,usuario);
            }
        }
        return new Document("eliminacion",false);
    }

    /**
     * {@inheritDoc}
     * @param nombreGrupo
     * @param administrador
     * @return
     */
    public Document eliminarGrupo(String nombreGrupo,String administrador)
    {
        if(nombreGrupo!=null && administrador!=null)
        {
            return mod.eliminarGrupo(nombreGrupo,administrador);
        }
        return new Document("eliminacion",false);
    }

    /**
     * {@inheritDoc}
     * @param usuario
     * @return
     */
    public Document consultarNumNotificaciones(String usuario)
    {
        if(usuario!=null)
        {
            return new Document("notificaciones",mod.consultarNotificaciones(usuario));
        }
        return new Document("notificaciones",0);
    }

    /**
     * {@inheritDoc}
     * @param nombre
     * @return
     */
    public List<Usuario> obtenerContactos(String nombre)
    {
        if(nombre!=null)
        {
            return mod.obtenerContactos(nombre);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     * @param nombre
     * @return
     */
    public List<String> obtenerGrupos(String nombre)
    {
        if(nombre!=null)
        {
            return mod.obtenerGrupos(nombre);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     * @param grupo
     * @param usuario
     */
    public void agregarContactoGrupo(String grupo, String usuario)
    {
        if(grupo!=null && usuario!=null)
        {
            Notificacion notif= new Notificacion("Grupo",grupo,usuario,"Ahora perteneces al grupo: "+grupo);
            mod.agregarNotificacion(notif);
            mod.actualizarListaContactosGrupo(grupo,usuario);
            //MatchGrupos match= new MatchGrupos();
            //match.generarMatch(grupo);
        }
    }

    /**
     * {@inheritDoc}
     * @param grupo
     */
    public Document agregarNotificiacionBroadCast(String grupo, String comentario, String sitio)
    {
        if(grupo!=null & comentario!=null && sitio!=null)
        {
            List <String> integrantes=mod.obtenerListaContactos(grupo);
            for(int i=0;i<integrantes.size();i++)
            {
                Notificacion notificacion= new Notificacion(new String("Notificacion grupo"),grupo,integrantes.get(i),comentario,false,new Date(),sitio);
                mod.agregarNotificacion(notificacion);

            }
            return new Document("notificacion",true);
        }
        return new Document("notificacion", false);
    }

    /**
     * {@inheritDoc}
     */
    public void activarRetroalimentacion()
    {
        Retroalimentacion.recalcularDisponibilidad();
    }
}
