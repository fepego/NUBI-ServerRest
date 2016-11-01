package com.nubi.Utils;

import com.nubi.IntegracionBD.ModeloNubi;
import com.nubi.IntegracionBD.ModeloNubiImp;
import com.nubi.colecciones.Fotocopiadora;
import com.nubi.colecciones.Restaurante;
import com.nubi.colecciones.Semilla;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Felipe on 1/11/2016.
 */
public class LectorSemillaFotocopiadoras {
    public static void crearFotocopiadoras() throws IOException {
        List<Fotocopiadora> fotocopiadoras= cargarSemilla("FotocopiadorasNormal.prn");
        List<Fotocopiadora> fotocopiadoras1= cargarSemilla("FotocopiadorasCorte.prn");
       for(int i=0; i<fotocopiadoras1.size();i++)
        {
            for (int j=0; j<fotocopiadoras1.get(i).getSemilla().size();j++)
            {
                fotocopiadoras.get(i).getSemilla().add(fotocopiadoras1.get(i).getSemilla().get(j));
            }
        }
        for(Fotocopiadora fotocopiadora: fotocopiadoras)
        {
            ModeloNubi modelo= new ModeloNubiImp();
            modelo.agregarFotocopiadora(fotocopiadora);
        }
    }
    public static List<Fotocopiadora> cargarSemilla(String nombreArchivo)throws FileNotFoundException, IOException
    {
        String cadena,tipoSemana=new String();
        List<String> nombre= new ArrayList<String>();
        List<Fotocopiadora> fotocopiadoras= new ArrayList<Fotocopiadora>();
        List<Semilla> semillas= new ArrayList<Semilla>();
        List<String> dias= new ArrayList<String>();
        FileReader f = new FileReader(nombreArchivo);
        BufferedReader b = new BufferedReader(f);
        int cont=1;
        int cont2=1;
        int contHoras=1;
        while((cadena = b.readLine())!=null) {
            System.out.println(cadena);
            if(cadena.equals("#"))
                break;
            if(cont==1)
            {
                Fotocopiadora fotocopiadora= new Fotocopiadora();
                cadena=b.readLine();
                Scanner delimitador= new Scanner(cadena).useDelimiter(" \\s*");
                while(delimitador.hasNext())
                {
                    nombre.add(new String(delimitador.next()));
                }
                if(nombre.size()==3)
                {
                    fotocopiadora.setNombre(new String(nombre.get(0)+" "+nombre.get(1)));
                    tipoSemana= new String(nombre.get(2));
                }
                else
                {
                    fotocopiadora.setNombre(new String(nombre.get(0)));
                    tipoSemana= new String(nombre.get(1));
                }
                fotocopiadoras.add(fotocopiadora);
                cont ++;
                nombre= new ArrayList<String>();
            }
            else
            {
                Scanner delimitador= new Scanner(cadena).useDelimiter(" \\s*");
                if(cont==2)
                {
                    dias.add(new String(delimitador.next()));
                    dias.add(new String(delimitador.next()));
                    dias.add(new String(delimitador.next()));
                    dias.add(new String(delimitador.next()));
                    dias.add(new String(delimitador.next()));
                    dias.add(new String(delimitador.next()));

                }
                if(cont>=3 && cont <=23)
                {

                    delimitador= new Scanner(cadena).useDelimiter(" \\s*");
                    if(cont2==1)
                    {
                        String hora= delimitador.next();
                        semillas.add(new Semilla(lectorSemillaRestaurantes.establecerHora(hora,new Semilla(dias.get(0),tipoSemana))));
                        semillas.add(new Semilla(lectorSemillaRestaurantes.establecerHora(hora,new Semilla(dias.get(1),tipoSemana))));
                        semillas.add(new Semilla(lectorSemillaRestaurantes.establecerHora(hora,new Semilla(dias.get(2),tipoSemana))));
                        semillas.add(new Semilla(lectorSemillaRestaurantes.establecerHora(hora,new Semilla(dias.get(3),tipoSemana))));
                        semillas.add(new Semilla(lectorSemillaRestaurantes.establecerHora(hora,new Semilla(dias.get(4),tipoSemana))));
                        semillas.add(new Semilla(lectorSemillaRestaurantes.establecerHora(hora,new Semilla(dias.get(5),tipoSemana))));
                        semillas.get(0).setProbLibre(Double.parseDouble(delimitador.next()));
                        semillas.get(1).setProbLibre(Double.parseDouble(delimitador.next()));
                        semillas.get(2).setProbLibre(Double.parseDouble(delimitador.next()));
                        semillas.get(3).setProbLibre(Double.parseDouble(delimitador.next()));
                        semillas.get(4).setProbLibre(Double.parseDouble(delimitador.next()));
                        semillas.get(5).setProbLibre(Double.parseDouble(delimitador.next()));
                        cont2++;
                        cont++;
                        continue;
                    }
                    if(cont2==2)
                    {
                        semillas.get(0).setProbMedia(Double.parseDouble(delimitador.next()));
                        semillas.get(1).setProbMedia(Double.parseDouble(delimitador.next()));
                        semillas.get(2).setProbMedia(Double.parseDouble(delimitador.next()));
                        semillas.get(3).setProbMedia(Double.parseDouble(delimitador.next()));
                        semillas.get(4).setProbMedia(Double.parseDouble(delimitador.next()));
                        semillas.get(5).setProbMedia(Double.parseDouble(delimitador.next()));
                        cont2++;
                        cont++;
                        continue;
                    }
                    if(cont2==3)
                    {
                        semillas.get(0).setProbAlta(Double.parseDouble(delimitador.next()));
                        semillas.get(1).setProbAlta(Double.parseDouble(delimitador.next()));
                        semillas.get(2).setProbAlta(Double.parseDouble(delimitador.next()));
                        semillas.get(3).setProbAlta(Double.parseDouble(delimitador.next()));
                        semillas.get(4).setProbAlta(Double.parseDouble(delimitador.next()));
                        semillas.get(5).setProbAlta(Double.parseDouble(delimitador.next()));
                        cont2=1;
                    }

                }
                cont++;
                if(cont==24)
                {
                    fotocopiadoras.get(fotocopiadoras.size()-1).setSemilla(semillas);
                    semillas= new ArrayList<Semilla>();
                    cont=1;

                }
            }

        }
        b.close();

        return fotocopiadoras;
    }

}
