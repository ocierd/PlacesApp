package com.places.api.domain.exceptions;

public class NoEncontradoException extends Exception {

    public NoEncontradoException() {
        super("Recurso no encontrado");
    }

}
