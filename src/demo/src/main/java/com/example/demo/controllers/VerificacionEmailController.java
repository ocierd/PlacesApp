package com.example.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Usuario;
import com.example.demo.domain.exceptions.NoEncontradoException;
import com.example.demo.domain.exceptions.ValidacionException;
import com.example.demo.services.interfaces.VerificacionEmailService;

@RestController
@RequestMapping("/verificacion_email")
public class VerificacionEmailController extends BaseController {
  private final VerificacionEmailService verificacionEmailService;

  public VerificacionEmailController(VerificacionEmailService verificacionEmailService) {
    this.verificacionEmailService = verificacionEmailService;
  }

  /**
   * Endpoint para enviar el correo de verificación al usuario. Este método
   * obtiene el usuario actual autenticado y utiliza el servicio de verificación
   * de email para crear un token y enviar el correo de verificación.
   * 
   * @throws ValidacionException Si ocurre un error de validación durante la
   *                             creación del token o el envío del correo
   *                             electrónico.
   */
  @GetMapping("/confirmation-email")
  public void sendEmail() throws ValidacionException {
    Usuario usuario = this.getCurrentUser();
    verificacionEmailService.enviarCorreoVerificacionToken(usuario);
  }

  /**
   * Endpoint para confirmar la verificación del correo electrónico. Este método
   * recibe un token como parámetro, lo valida utilizando el servicio de
   * verificación de email y devuelve una respuesta indicando si la verificación
   * fue exitosa o no.
   * 
   * @param token El token de verificación recibido en el correo electrónico. Este
   *              token se utiliza para identificar al usuario y confirmar que su
   *              correo electrónico ha sido verificado correctamente.
   * @return Una ResponseEntity con un mensaje indicando el resultado de la
   *         verificación. Si la verificación es exitosa, se devuelve un mensaje
   *         de bienvenida al usuario. Si el token es inválido o ha expirado, se
   *         devuelve un mensaje de error correspondiente.
   */
  @GetMapping("/confirmar")
  public String confirmRegistration(@RequestParam String token) throws NoEncontradoException, ValidacionException {
    return verificacionEmailService.verificarCorreo(token);
  }
}