package com.example.demo.domain.exceptions;

public class NoEncontradoException extends Exception {

    public NoEncontradoException() {
        super("Recurso no encontrado");
    }

}
