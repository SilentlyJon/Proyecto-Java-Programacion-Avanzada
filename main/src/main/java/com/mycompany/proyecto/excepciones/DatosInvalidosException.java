package com.mycompany.proyecto.excepciones;

/**
 * Excepción que se lanza cuando los datos numéricos de un Departamento
 * son inválidos (metros cuadrados o precio base menores o iguales a 0).
 */
public class DatosInvalidosException extends Exception {

    public DatosInvalidosException(String mensaje) {
        super(mensaje);
    }
}