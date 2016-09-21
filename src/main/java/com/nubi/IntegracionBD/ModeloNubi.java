package com.nubi.IntegracionBD;

import com.nubi.colecciones.Alerta;
import com.nubi.colecciones.HistorialSitios;
import com.nubi.colecciones.SitiosEstudio;
import com.nubi.controlador.Sitio;
import com.nubi.controlador.resultadoHistorico;
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
}
