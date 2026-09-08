package com.mycompany.proyecto.modelo;


import com.mycompany.proyecto.modelo.Departamento;



public class DepartamentoBasico extends Departamento{
    public DepartamentoBasico(String id, int numero, double metrosCuadrados, double precioBase, NivelDemanda demanda, EstadoDepartamento estado){
        super(id, numero, metrosCuadrados, precioBase, demanda, estado);
    }
    
    @Override
    public double calcularPrecioFinal() {
        if(getDemanda() == NivelDemanda.BAJA){
            return getPrecioBase() * 1.05;
        }else if(getDemanda() == NivelDemanda.MEDIA){
            return getPrecioBase() * 1.10;
        }else if(getDemanda() == NivelDemanda.ALTA){
            return getPrecioBase() * 1.20;
        }
        return getPrecioBase();
    }
    
    @Override
    public String mostrarInformacion(){
       return super.mostrarInformacion() + " | Tipo: Basico";
    }
}