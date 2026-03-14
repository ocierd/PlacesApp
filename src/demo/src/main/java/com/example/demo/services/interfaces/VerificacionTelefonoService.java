package com.example.demo.services.interfaces;

import com.example.demo.domain.Usuario;
import com.example.demo.domain.exceptions.NoEncontradoException;
import com.example.demo.domain.exceptions.ValidacionException;

/**
 * Interfaz que define los métodos para la verificación de teléfono.
 */
public interface VerificacionTelefonoService {

    /**
     * Envía un código de verificación al número de teléfono del usuario. Este método se encarga de validar si el usuario ha rebasado la cantidad de tokens que se le pueden enviar.
     * @param usuario El usuario al que se le enviará el código de verificación
     * @throws ValidacionException Si el usuario ha rebasado la cantidad de tokens que se le pueden enviar o si ocurre algún error al enviar el código de verificación
     */
    void enviarCodigoVerificacion(Usuario usuario) throws ValidacionException;

    /**
     * Verifica el código de verificación ingresado por el usuario. Este método se encarga de validar si el código ingresado es correcto, si no ha expirado y si el usuario no ha rebasado la cantidad de intentos permitidos.
     * @param usuario El usuario que está intentando verificar su número de teléfono
     * @param codigo El código de verificación ingresado por el usuario
     * @throws ValidacionException Si el código ingresado es incorrecto, si ha expirado o si el usuario ha rebasado la cantidad de intentos permitidos
     * @throws NoEncontradoException Si no se encuentra una verificación de teléfono para el usuario o si el usuario no existe
     */
    void verificarTelefono(Usuario usuario, String codigo) throws ValidacionException, NoEncontradoException;

}
