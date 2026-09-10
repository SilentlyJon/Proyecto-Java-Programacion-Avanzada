package com.mycompany.proyecto.modelo;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.mycompany.proyecto.excepciones.ProyectoDuplicadoException;


public class Inmobiliaria {
    private String nombre;
    private String direccion;
    private Map<String, Proyecto> proyectos;
    private String contacto;
    
    public Inmobiliaria(String nombre, String direccion, String contacto){
        this.nombre = nombre;
        this.direccion = direccion;
        this.contacto = contacto;
        this.proyectos = new HashMap<>();
    }
    
    public void agregarProyecto(Proyecto proyecto) throws ProyectoDuplicadoException{
        if(proyectos.containsKey(proyecto.getCodigo())){
            throw new ProyectoDuplicadoException("Ya existe un proyecto con el codigo: " + proyecto.getCodigo());
        }
        proyectos.put(proyecto.getCodigo(),proyecto);
    }

    public Proyecto buscarProyecto(String codigo){
        return proyectos.get(codigo);
    }

    public void modificarProyecto(String codigo, String nuevoNombre, String nuevaUbicacion, NivelDemanda nuevaDemanda){
        Proyecto proyecto = proyectos.get(codigo);
        
        if(proyecto != null){
            proyecto.setNombre(nuevoNombre);
            proyecto.setUbicacion(nuevaUbicacion);
            proyecto.setDemanda(nuevaDemanda);
        }
    }

    public void eliminarProyecto(String codigo){
        proyectos.remove(codigo);
    }

    public void mostrarProyecto(String codigo){
        Proyecto proyecto = proyectos.get(codigo);
        System.out.println(proyecto);
    }

    /**
     * SIA-9: Funcionalidad propia de utilidad para el negocio.
     * Recorre todos los proyectos y arma, por cada uno, el subconjunto de
     * departamentos filtrado por criterio: estado DISPONIBLE y precio final
     * dentro del presupuesto del cliente. Sirve para que un vendedor
     * encuentre rapidamente que ofrecerle a un cliente segun su presupuesto.
     * Dentro de cada proyecto, las coincidencias quedan ordenadas de mayor
     * a menor precio final (para aprovechar mejor el presupuesto disponible).
     */
    public Map<String, List<Departamento>> buscarDisponiblesPorPresupuesto(double presupuesto){
        Map<String, List<Departamento>> resultado = new LinkedHashMap<>();

        for(Proyecto proyecto : proyectos.values()){
            List<Departamento> coincidencias = new ArrayList<>();

            for(Departamento departamento : proyecto.getDepartamentos()){
                if(departamento.getEstado() == EstadoDepartamento.DISPONIBLE
                        && departamento.calcularPrecioFinal() <= presupuesto){
                    coincidencias.add(departamento);
                }
            }

            if(!coincidencias.isEmpty()){
                coincidencias.sort(Comparator.comparingDouble(Departamento::calcularPrecioFinal).reversed());
                resultado.put(proyecto.getCodigo(), coincidencias);
            }
        }

        return resultado;
    }
   
    public String getNombre(){
        return nombre;
    }
   
    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public String getDireccion(){
        return direccion;
    }

    public void setDireccion(String direccion){
        this.direccion = direccion;
    }

    public String getContacto(){
        return contacto;
    }

    public void setContacto(String contacto){
        this.contacto = contacto;
    }

    public Map<String, Proyecto> getProyectos(){
        return proyectos;
    }

    public void setProyectos(Map<String, Proyecto> proyectos){
        this.proyectos = proyectos;
    }
}