package com.nubi.Utils;

import com.nubi.IntegracionBD.ModeloNubi;
import com.nubi.IntegracionBD.ModeloNubiImp;
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
 * Created by Felipe on 31/10/2016.
 */
public class lectorSemillaRestaurantes {
    public static void crearRestaurantes() throws IOException {
        List<Restaurante> restaurantes= cargarSemilla("CafeteriasNormal.prn");
        List<Restaurante> restaurantes2= cargarSemilla("CafeteriasCorte.prn");
        for(int i=0; i<restaurantes2.size();i++)
        {
            for (int j=0; j<restaurantes2.get(i).getSemilla().size();j++)
            {
                restaurantes.get(i).getSemilla().add(restaurantes2.get(i).getSemilla().get(j));
            }
        }
        for(Restaurante r: restaurantes)
        {
            ModeloNubi modelo= new ModeloNubiImp();
            modelo.agregarRestaurante(r);
        }
    }
    public static List<Restaurante> cargarSemilla(String nombreArchivo)throws FileNotFoundException, IOException
    {
        String cadena,tipoSemana=new String();
        List<String> nombre= new ArrayList<String>();
        List<Restaurante> restaurantes= new ArrayList<Restaurante>();
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
                Restaurante rest= new Restaurante();
                cadena=b.readLine();
                Scanner delimitador= new Scanner(cadena).useDelimiter(" \\s*");
                while(delimitador.hasNext())
                {
                    nombre.add(new String(delimitador.next()));
                }
                if(nombre.size()==3)
                {
                    rest.setNombre(new String(nombre.get(0)+" "+nombre.get(1)));
                    tipoSemana= new String(nombre.get(2));
                }
                else
                {
                    rest.setNombre(new String(nombre.get(0)));
                    tipoSemana= new String(nombre.get(1));
                }
                restaurantes.add(rest);
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
                        semillas.add(new Semilla(establecerHora(hora,new Semilla(dias.get(0),tipoSemana))));
                        semillas.add(new Semilla(establecerHora(hora,new Semilla(dias.get(1),tipoSemana))));
                        semillas.add(new Semilla(establecerHora(hora,new Semilla(dias.get(2),tipoSemana))));
                        semillas.add(new Semilla(establecerHora(hora,new Semilla(dias.get(3),tipoSemana))));
                        semillas.add(new Semilla(establecerHora(hora,new Semilla(dias.get(4),tipoSemana))));
                        semillas.add(new Semilla(establecerHora(hora,new Semilla(dias.get(5),tipoSemana))));
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
                    restaurantes.get(restaurantes.size()-1).setSemilla(semillas);
                    semillas= new ArrayList<Semilla>();
                    cont=1;

                }
            }

        }
        b.close();

        return restaurantes;
    }
    public static Semilla establecerHora(String hora, Semilla semilla)
    {
        Semilla sem= new Semilla(semilla.getDia(),semilla.getTipoDia());
        if( hora.equals("7:00-9:00"))
        {
            sem.setHoraInicio(25200000);
            sem.setHoraFin(32400000);
        }
        if( hora.equals("9:00-11:00"))
        {
            sem.setHoraInicio(32400000);
            sem.setHoraFin(39600000);
        }
        if( hora.equals("11:00-1:00"))
        {
            sem.setHoraInicio(39600000);
            sem.setHoraFin(46800000);
        }
        if( hora.equals("1:00-2:00"))
        {
            sem.setHoraInicio(46800000);
            sem.setHoraFin(50400000);
        }
        if( hora.equals("2:00-4:00"))
        {
            sem.setHoraInicio(50400000);
            sem.setHoraFin(57600000);
        }
        if( hora.equals("4:00-6:00"))
        {
            sem.setHoraInicio(57600000);
            sem.setHoraFin(64800000);
        }
        if( hora.equals("6:00-8:00"))
        {
            sem.setHoraInicio(64800000);
            sem.setHoraFin(72000000);
        }
        return sem;
    }
}
