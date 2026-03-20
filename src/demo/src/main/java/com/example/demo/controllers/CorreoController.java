package com.example.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Correo;
import com.example.demo.domain.Usuario;
import com.example.demo.domain.exceptions.NoEncontradoException;
import com.example.demo.domain.exceptions.ValidacionException;
import com.example.demo.services.interfaces.CorreoService;
import com.example.demo.services.interfaces.VerificacionEmailService;

/**
 * Controlador para manejar las operaciones relacionadas con los correos
 * electrónicos de los usuarios. Este controlador se encarga de recibir las
 * solicitudes HTTP
 * relacionadas con los correos electrónicos, como la creación de nuevos correos
 * y la verificación de correos electrónicos, y delegar la lógica de negocio a
 * los servicios correspondientes. Además, este controlador se encarga de
 * obtener el usuario actual autenticado para asociar los correos electrónicos a
 * dicho usuario.
 */
@RestController
@RequestMapping("/correos")
public class CorreoController extends BaseController {

  /**
   * Servicio para manejar las operaciones relacionadas con los correos
   * electrónicos de los usuarios.
   */
  private final CorreoService correoService;

  /**
   * Servicio para manejar la verificación de correos electrónicos.
   */
  private final VerificacionEmailService verificacionEmailService;

  /**
   * Constructor para inyectar las dependencias del servicio de correos y el
   * servicio de verificación de correos electrónicos.
   * 
   * @param verificacionEmailService Servicio para manejar la verificación de
   *                                 correos electrónicos.
   * @param correoService            Servicio para manejar las operaciones
   *                                 relacionadas con los correos electrónicos de
   *                                 los usuarios.
   */
  public CorreoController(VerificacionEmailService verificacionEmailService,
      CorreoService correoService) {
    this.verificacionEmailService = verificacionEmailService;
    this.correoService = correoService;
  }

  /**
   * Endpoint para crear un nuevo correo electrónico para el usuario actual.
   * Este método recibe un objeto Correo en el cuerpo de la solicitud y utiliza
   * el servicio de correos para crear el nuevo correo asociado al usuario.
   * 
   * @param correo El objeto Correo que contiene la información del nuevo correo
   *               a crear.
   * @return El objeto Correo creado.
   * @throws NoEncontradoException Si el usuario no se encuentra.
   * @throws ValidacionException   Si ocurre un error de validación durante la
   *                               creación del correo.
   */
  @PostMapping
  public Correo crearCorreo(@RequestBody Correo correo) throws NoEncontradoException, ValidacionException {
    Usuario usuario = this.getCurrentUser();
    return correoService.crearCorreo(usuario.getUsuarioId(), correo);
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