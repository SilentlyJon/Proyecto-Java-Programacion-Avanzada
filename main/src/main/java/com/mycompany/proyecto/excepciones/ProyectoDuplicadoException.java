package com.mycompany.proyecto.excepciones;

/**
 * Excepción que se lanza cuando se intenta agregar un Proyecto
 * con un código que ya existe en la Inmobiliaria.
 */
public class ProyectoDuplicadoException extends Exception {

    public ProyectoDuplicadoException(String mensaje) {
        super(mensaje);
    }
}