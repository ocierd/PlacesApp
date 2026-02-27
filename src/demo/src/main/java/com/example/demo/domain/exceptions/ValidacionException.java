package com.example.demo.domain.exceptions;

import java.util.List;

/**
 * ValidacionException es una clase que representa una excepción personalizada
 * que se lanza cuando ocurre un error de validación en la aplicación. Contiene
 * un mensaje de error general y una lista de errores específicos que detallan
 * las validaciones que fallaron.
 */
public class ValidacionException extends Exception {

    private List<String> errores;

    public ValidacionException(String mensajeError) {
        super(mensajeError);
        errores = List.of();
    }

    public ValidacionException(String mensaje, List<String> errores) {
        super(mensaje);
        this.errores = errores;
    }

    public ValidacionException(List<String> errores) {
        super("Error de validación: ");
        this.errores = errores;
    }

    public List<String> getErrores() {
        return errores;
    }

    public void setErrores(List<String> errores) {
        this.errores = errores;
    }

}
