package com.example.demo.domain.dto;

import java.util.List;

/**
 * ValidacionErrorResponse es una clase que representa la estructura de la
 * respuesta que se devuelve al cliente cuando ocurre una excepción de validación en la
 * aplicación. Contiene un mensaje de error general y una lista de errores
 * específicos que detallan las validaciones que fallaron.
 */
public class ValidacionErrorResponse {

    private String error;

    private List<String> errores;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<String> getErrores() {
        return errores;
    }

    public void setErrores(List<String> errores) {
        this.errores = errores;
    }

}
