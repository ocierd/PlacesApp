package com.example.demo.services.interfaces;

import com.example.demo.domain.Usuario;
import com.example.demo.domain.exceptions.NoEncontradoException;
import com.example.demo.domain.exceptions.ValidacionException;

public interface VerificacionEmailService {

  /**
   * Envía un correo de verificación al usuario con un token único. Este método se
   * encarga de crear el token de verificación, guardarlo en la base de datos y
   * enviar el correo al usuario.
   * 
   * @param usuario El usuario al que se le enviará el correo de verificación
   * @throws ValidacionException Si ocurre un error de validación durante la
   *                             creación del token o el envío del correo
   *                             electrónico
   */
  void enviarCorreoVerificacionToken(Usuario usuario) throws ValidacionException;

  /**
   * Verifica el correo electrónico del usuario utilizando un token único.
   * 
   * @param token El token de verificación enviado al correo del usuario
   * @return Un mensaje de confirmación o bienvenida
   * @throws NoEncontradoException Si el token no se encuentra en la base de datos
   * @throws ValidacionException   Si ocurre un error de validación durante la
   *                               verificación del token
   */
  String verificarCorreo(String token) throws NoEncontradoException, ValidacionException;
}