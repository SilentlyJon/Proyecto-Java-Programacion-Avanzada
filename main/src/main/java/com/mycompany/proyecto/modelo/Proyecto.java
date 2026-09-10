package com.mycompany.proyecto.modelo;



import com.mycompany.proyecto.modelo.NivelDemanda;
import com.mycompany.proyecto.modelo.EstadoDepartamento;
import com.mycompany.proyecto.modelo.Departamento;
import com.mycompany.proyecto.excepciones.DatosInvalidosException;
import java.util.ArrayList;

public class Proyecto {
    private String codigo;
    private String nombre;
    private String ubicacion;
    private NivelDemanda demanda;
    private ArrayList<Departamento> departamentos;

    public Proyecto(String codigo, String nombre, String ubicacion, NivelDemanda demanda){
        this.codigo = codigo;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.demanda = demanda;
        this.departamentos = new ArrayList<>();
    }
    
    public void agregarDepartamento(Departamento departamento){
        departamentos.add(departamento);
    }
    
    public void eliminarDepartamento(String id){
        Departamento departamento = buscarDepartamento(id);
        if(departamento != null){
            departamentos.remove(departamento);
        }
    }

    public void modificarDepartamento(String id, int nuevoNumero, double nuevoMetrosCuadrados, double nuevoPrecioBase, NivelDemanda nuevaDemanda, EstadoDepartamento nuevoEstado) throws DatosInvalidosException{
        Departamento departamento = buscarDepartamento(id);
        if(departamento != null){
            departamento.modificarDatos(nuevoNumero, nuevoMetrosCuadrados, nuevoPrecioBase, nuevaDemanda, nuevoEstado);
        }
    }
    
    public void mostrarDepartamento(){
        for(Departamento departamento : departamentos){
            System.out.println(departamento.mostrarInformacion());
        }
    }
    
    public Departamento buscarDepartamento(String id){
        for(Departamento departamento : departamentos){
            if(departamento.getId().equals(id)){
                return departamento;
            }
        }
        return null;
    }
    public String getCodigo(){
        return codigo;
    }

    public void setCodigo(String codigo){
        this.codigo = codigo;
    }

    public String getNombre(){
        return nombre;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public String getUbicacion(){
        return ubicacion;
    }

    public void setUbicacion(String ubicacion){
        this.ubicacion = ubicacion;
    }
    public NivelDemanda getDemanda(){
        return demanda;
    }

    public void setDemanda(NivelDemanda demanda){
        this.demanda = demanda;
    }
    
    public ArrayList<Departamento> getDepartamentos(){
        return departamentos;
    }
    
    public String toString(){
        return "Codigo: " + codigo
                + "| Nombre: " + nombre
                + "| Ubicacion: " + ubicacion
                + "| Demanda: " + demanda;
    }
}