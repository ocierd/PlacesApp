package com.example.demo.domain.exceptions;

/**
 * Excepción personalizada que se lanza cuando un usuario no está autorizado
 * para realizar una acción o acceder a un recurso específico.
 * Esta excepción se utiliza para indicar que la autenticación ha fallado o que
 * el usuario no tiene los permisos necesarios para acceder al recurso
 * solicitado.
 */
public class UnauthorizedException extends Exception {
    public UnauthorizedException(String message) {
        super(message);
    }

}
