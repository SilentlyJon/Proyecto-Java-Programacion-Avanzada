/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.main;

/**
 *
 * @author Bastian
 */
public class Inmobiliaria {
    
    private String nombre;
    private String direccion;
    private Map<String, Proyecto> proyectos;
    private String contacto;
    
    public Inmobiliaria(String nombre,String direccion, String contacto){
        this.nombre = nombre;
        this.direccion = direccion;
        this.contacto = contacto;
        this.proyecto = new Hasmap<>();
    }
    
    
    public String getNombre(){
    return nombre;
    }
    
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public void setDireccion(String direccion){
        this.direccion = direccion;
    }
    public void setContacto(String contacto){
        this.contacto = contacto:
    }
   
    
}
