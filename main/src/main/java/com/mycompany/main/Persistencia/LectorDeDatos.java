package com.mycompany.main.Persistencia;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.mycompany.proyecto.modelo.Departamento;
import com.mycompany.proyecto.modelo.DepartamentoBasico;
import com.mycompany.proyecto.modelo.DepartamentoPremium;
import com.mycompany.proyecto.modelo.Inmobiliaria;
import com.mycompany.proyecto.modelo.NivelDemanda;
import com.mycompany.proyecto.modelo.EstadoDepartamento;
import com.mycompany.proyecto.modelo.Proyecto;
import com.mycompany.proyecto.excepciones.ProyectoDuplicadoException;
import com.mycompany.proyecto.excepciones.DatosInvalidosException;

public class LectorDeDatos {
    public static void cargarProyectos(String archivo, Inmobiliaria inmobilaria){
        try{
            BufferedReader br = new BufferedReader(new FileReader(archivo));
            
            String linea;
            
            while((linea = br.readLine()) != null){
                String[] datos = linea.split(";");
                
                String codigo = datos[0];
                String nombre = datos[1];
                String direccion = datos[2];
                NivelDemanda demanda = NivelDemanda.valueOf(datos[3]);
                
                
                Proyecto proyecto = new Proyecto(codigo, nombre, direccion, demanda);
                try{
                    inmobilaria.agregarProyecto(proyecto);
                }catch(ProyectoDuplicadoException e){
                    System.out.println("Aviso al cargar proyectos: " + e.getMessage());
                }
            }
            br.close();
        }catch (IOException e){
            System.out.println("Error al leer los datos.");
        }
    }

    public static void cargarDepartamento(String archivo, Inmobiliaria inmobiliaria){
        try{
            BufferedReader br = new BufferedReader(new FileReader(archivo));
            
            String linea;
            
            while((linea = br.readLine()) != null){
                String[] datos = linea.split(";");
                
                String codigoProyecto = datos[0];
                
                String tipo = datos[1];
                String id = datos[2];
                int numero = Integer.parseInt(datos[3]);
                double metrosCuadrados = Double.parseDouble(datos[4]);
                double precioBase = Double.parseDouble(datos[5]);
                NivelDemanda demanda = NivelDemanda.valueOf(datos[6]);
                EstadoDepartamento estado = EstadoDepartamento.valueOf(datos[7]);
               
                Proyecto proyecto = inmobiliaria.buscarProyecto(codigoProyecto);
                
                if(proyecto == null){
                    System.out.println("Aviso al cargar departamentos: no existe el proyecto " + codigoProyecto);
                    continue;
                }
                
                try{
                    if(tipo.equals("BASICO")){
                        DepartamentoBasico departamento = new DepartamentoBasico(id, numero, metrosCuadrados, precioBase, demanda, estado);
                        proyecto.agregarDepartamento(departamento);                
                    }else if(tipo.equals("PREMIUM")){
                        Boolean tienePiscina = Boolean.valueOf(datos[8]);
                        Boolean garage = Boolean.valueOf(datos[9]);
                        Boolean tieneBidet = Boolean.valueOf(datos[10]);

                        DepartamentoPremium departamento = new DepartamentoPremium(tienePiscina, garage, tieneBidet, id, numero, metrosCuadrados, precioBase, demanda, estado);
                        proyecto.agregarDepartamento(departamento); 
                    }
                }catch(DatosInvalidosException e){
                    System.out.println("Aviso al cargar departamentos: " + e.getMessage());
                }
            }
            br.close();
        } catch (IOException e){
            System.out.println("Error al leer los datos");
        }
        
    }

}