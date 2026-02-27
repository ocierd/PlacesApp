package com.example.demo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.domain.dto.ValidacionErrorResponse;
import com.example.demo.domain.exceptions.NoEncontradoException;
import com.example.demo.domain.exceptions.ValidacionException;

/**
 * GlobalExceptionHandler es un controlador de asesoramiento global que maneja
 * las excepciones no controladas en la aplicación.
 * Proporciona una forma centralizada de manejar las excepciones y devolver
 * respuestas adecuadas al cliente en caso de errores. Al
 * utilizar @RestControllerAdvice,
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Maneja las excepciones de tipo ValidacionException que se lanzan en la
     * aplicación. Devuelve una respuesta con el mensaje de error y los detalles de
     * las validaciones que fallaron.
     * 
     * @param ex La excepción de validación que se ha lanzado
     * @return Una respuesta HTTP con el mensaje de error y los detalles de las
     *         validaciones que fallaron
     */
    @ExceptionHandler(ValidacionException.class)
    public ResponseEntity<ValidacionErrorResponse> handleValidacionException(ValidacionException ex) {
        var errorResp = new ValidacionErrorResponse();
        errorResp.setError(ex.getMessage());
        errorResp.setErrores(ex.getErrores());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResp);
    }

    /**
     * Maneja las excepciones de tipo NoEncontradoException que se lanzan en la
     * aplicación. Devuelve una respuesta con el mensaje de error indicando que el
     * recurso no fue encontrado.
     * 
     * @param ex La excepción de tipo NoEncontradoException que se ha lanzado
     * @return Una respuesta HTTP con el mensaje de error indicando que el recurso
     *         no fue encontradoa
     */
    @ExceptionHandler(NoEncontradoException.class)
    public ResponseEntity<String> handleNoEncontradoException(NoEncontradoException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

}
