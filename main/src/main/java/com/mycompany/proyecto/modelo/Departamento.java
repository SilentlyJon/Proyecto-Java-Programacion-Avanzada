package com.mycompany.proyecto.modelo;

import com.mycompany.proyecto.excepciones.DatosInvalidosException;

/*
 * Clase abstracta que es basicamten un departamento dentro de un proyecto
 * inmobiliario
 *
 * todo departamento es de un tipo concreto (estandar, premium),
 * y cada tipo calcula su precio final de forma distinta, por eso
 * calcularPrecioFinal() no tiene cuerpo aquí y cada subclase está obligada
 * a implementarlo (así como una función declarada pero no definida en un
 * .h en C, con la diferencia de que aquí el compilador obliga a
 * implementarla).
 */

public abstract class Departamento{
    private String id;
    private int numero;
    private double metrosCuadrados;
    private double precioBase;
    private NivelDemanda demanda;
    private EstadoDepartamento estado;

    public Departamento(String id, int numero, double metrosCuadrados, double precioBase, NivelDemanda demanda, EstadoDepartamento estado) throws DatosInvalidosException {
        if(metrosCuadrados <= 0 || precioBase <= 0){
            throw new DatosInvalidosException("Los metros cuadrados y el precio base deben ser mayores a cero.");
        }
        this.id = id;
        this.numero = numero;
        this.metrosCuadrados = metrosCuadrados;
        this.precioBase = precioBase;
        this.demanda = demanda;
        this.estado = estado;
    }

    /**
     * Método abstracto: cada subclase decide cómo calcula su precio final
     * a partir del precio base y la demanda.
     */

    public abstract double calcularPrecioFinal();

    /**
     * Método concreto con una implementación por defecto, pero que las
     * subclases SOBREESCRIBEN (@Override) para agregar su propia
     * información específica.
     */
    public String mostrarInformacion(){
        return String.format("ID: %s | Nro: %d | %.1f m2 | Precio base: $%.0f | Demanda: %s | Estado: %s", id, numero, metrosCuadrados, precioBase, demanda, estado);
    }
    
    public void  modificarDatos(int numero, double metrosCuadrados, double precioBase, NivelDemanda demanda, EstadoDepartamento estado) throws DatosInvalidosException{
        if(metrosCuadrados <= 0 || precioBase <= 0){
            throw new DatosInvalidosException("Los metros cuadrados y el precio base deben ser mayores a cero.");
        }
        this.numero = numero;
        this.metrosCuadrados = metrosCuadrados;
        this.precioBase = precioBase;
        this.demanda = demanda;
        this.estado = estado;
        
    }
    

    // Getters y Setters
    public String getId(){
        return id; 
    }
    public void setId(String id){ 
        this.id = id;
    }

    public int getNumero(){ 
        return numero;
    }
    public void setNumero(int numero){ 
        this.numero = numero; 
    }

    public double getMetrosCuadrados(){ 
        return metrosCuadrados; 
    }
    public void setMetrosCuadrados(double metrosCuadrados){ 
        this.metrosCuadrados = metrosCuadrados; 
    }

    public double getPrecioBase(){ 
        return precioBase;
    }
    public void setPrecioBase(double precioBase){ 
        this.precioBase = precioBase; 
    }

    public NivelDemanda getDemanda(){ 
        return demanda; 
    }
    public void setDemanda(NivelDemanda demanda){ 
        this.demanda = demanda;
    }

    public EstadoDepartamento getEstado(){ 
        return estado; 
    }
    public void setEstado(EstadoDepartamento estado){ 
        this.estado = estado; 
    }
}