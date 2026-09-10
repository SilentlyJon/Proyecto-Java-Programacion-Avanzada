package com.mycompany.main.Persistencia;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


import com.mycompany.proyecto.modelo.*;

public class EscritorDeDatos {
    public static void guardarProyectos(String archivo, Inmobiliaria inmobiliaria){
        
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))){
            for(Proyecto proyecto : inmobiliaria.getProyectos().values()){
                bw.write(proyecto.getCodigo()+ ";" + proyecto.getNombre()+ ";"+ proyecto.getUbicacion() + ";" + proyecto.getDemanda());
                bw.newLine();
            }
        } catch (IOException e){
            System.out.println("Error al guardar los datos.");
        }
        
    }
    
    public static void guardarDepartamento(String archivo, Inmobiliaria inmobiliaria){
        
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))){
            for(Proyecto proyecto : inmobiliaria.getProyectos().values()){
                for(Departamento departamento : proyecto.getDepartamentos()){
                    
                    if(departamento instanceof DepartamentoBasico){
                        bw.write(proyecto.getCodigo()+ ";" + "BASICO" +";"+ departamento.getId()+";"+ departamento.getNumero() + ";" + departamento.getMetrosCuadrados() + ";" + departamento.getPrecioBase() +";"+ departamento.getDemanda()+";"+ departamento.getEstado());
                        bw.newLine();
                    }else if(departamento instanceof DepartamentoPremium){
                        DepartamentoPremium premium = (DepartamentoPremium) departamento;
                        bw.write(proyecto.getCodigo()+ ";" + "PREMIUM" +";" + premium.getId()+ ";"+ premium.getNumero() + ";" + premium.getMetrosCuadrados() + ";" + premium.getPrecioBase() + ";" + premium.getDemanda()+ ";" + premium.getEstado()+ ";"+ premium.isTienePiscina()+";"+ premium.isGarage()+";"+premium.isTieneBidet());
                        bw.newLine();
                    }
                }
            }
        } catch (IOException e){
            System.out.println("Error al guardar los datos.");
        }
        
    }
    
    
}
