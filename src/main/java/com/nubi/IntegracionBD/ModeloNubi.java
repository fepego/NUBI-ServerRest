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


}
