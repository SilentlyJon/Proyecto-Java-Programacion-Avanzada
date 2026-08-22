
package com.mycompany.main;

/**
 *
 * @author Bastian
 */
public class DepartamentoBasico extends Departamento{
    
    
    public DepartamentoBasico(String id, int numero, double metrosCuadrados, double precioBase, NivelDemanda demanda, EstadoDepartamento estado){
        super(numero,metrosCuadrados, precioBase);
        //this.descuento = descuento;
    }
    
    //@override
    public double calcularPrecioFinal(){
        return getPrecioBase();
    }
}
