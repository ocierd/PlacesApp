package com.example.demo.services;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Correo;
import com.example.demo.domain.Usuario;
import com.example.demo.domain.VerificacionCorreo;
import com.example.demo.domain.exceptions.NoEncontradoException;
import com.example.demo.domain.exceptions.ValidacionException;
import com.example.demo.repository.CorreoRepository;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.repository.VerificacionCorreoRepository;
import com.example.demo.services.interfaces.EmailService;
import com.example.demo.services.interfaces.VerificacionEmailService;
import com.example.demo.services.interfaces.TemplateService;

/**
 * Implementación de la interfaz VerificacionEmailService para manejar la lógica
 * de negocio relacionada con la verificación de correos electrónicos de los
 * usuarios. Esta clase se encarga de crear tokens de verificación, guardarlos
 * en la base de datos, enviar correos electrónicos de verificación a los
 * usuarios y verificar los tokens cuando los usuarios intentan confirmar sus
 * correos electrónicos. Además, esta clase maneja las validaciones necesarias
 * para asegurar que el proceso de verificación se realice correctamente y que
 * se respeten los límites establecidos para el envío de correos y la
 * verificación de tokens.
 */
@Service
public class VerificacionEmailServiceImpl implements VerificacionEmailService {
  private static final Logger logger = LoggerFactory.getLogger(VerificacionEmailServiceImpl.class);

  private final VerificacionCorreoRepository verificacionCorreoRepository;

  private final CorreoRepository correoRepository;

  private final UsuarioRepository usuarioRepository;

  private final EmailService emailService;

  private final TemplateService templateService;

  @Value("${places_app.email.verificacion.url}")
  private String EMAIL_VERIFICACION_URL;

  @Value("${places_app.email.verificacion.max_attempts}")
  private Integer LIMITE_INTENTOS;

  @Value("${places_app.email.verificacion.expiration}")
  private Integer EXPIRATION_TIME;

  private static final String EMAIL_TEMPLATE = "email/email-template";

  private static final String ASUNTO_VERIFICACION = "Verificación de correo electrónico";

  private static final String BIENVENIDA_TEMPLATE = "varios/bienvenida";

  public VerificacionEmailServiceImpl(VerificacionCorreoRepository verificacionCorreoRepository,
      CorreoRepository correoRepository, UsuarioRepository usuarioRepository,
      EmailService emailService, TemplateService templateService) {
    this.verificacionCorreoRepository = verificacionCorreoRepository;
    this.correoRepository = correoRepository;
    this.usuarioRepository = usuarioRepository;
    this.emailService = emailService;
    this.templateService = templateService;
  }

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
  @Override
  public void enviarCorreoVerificacionToken(Usuario usuario) throws ValidacionException {
    try {
      Optional<Correo> correoOpt = correoRepository.findByUsuarioIdAndActivoIsNull(usuario.getUsuarioId());
      Correo correo = correoOpt.isPresent() ? correoOpt.get() : null;

      validarDatosCorreo(correo);

      saveVerificacionCorreo(correo);
    } catch (Exception e) {
      logger.error("Se produjo un error al intentar crear el token.", e);
      throw e;
    }
  }

  private void validarDatosCorreo(Correo correo) throws ValidacionException {
    // Valida que haya un correo para activar
    if (correo == null) {
      throw new ValidacionException("No tiene correo para activar.");
    }

    Integer verificado = verificacionCorreoRepository
        .countByCorreoIdAndFechaConfirmacionIsNotNull(correo.getCorreoId());

    // Valida que el correo no esté verificado
    if (verificado > 0) {
      throw new ValidacionException("El correo ya fue verificado.");
    }

    Integer verificacionCorreo = verificacionCorreoRepository
        .countByCorreoIdAndFechaConfirmacionIsNull(correo.getCorreoId());

    // Valida que el número de intentos no sobrepase el límite
    if (verificacionCorreo >= LIMITE_INTENTOS) {
      throw new ValidacionException(
          "Se ha alcanzado el límite de códigos de verificación enviados. Por favor, inténtalo más tarde.");
    }

    Integer noVencidos = verificacionCorreoRepository.countByCorreoIdAndFechaExpiracionAfter(correo.getCorreoId(),
        LocalDateTime.now());

    // Valida que el número de intentos no sobrepase el límite
    if (noVencidos > 0) {
      throw new ValidacionException("Tiene un Token no vencido.");
    }
  }

  /***
   * Guarda el registro de Verificación Correo
   * 
   * @param correo Entidad que contiene los datos del correo a activar
   * @throws ValidacionException Excepción a la hora de enviar el correo
   */
  private void saveVerificacionCorreo(Correo correo) throws ValidacionException {
    try {
      VerificacionCorreo vToken = new VerificacionCorreo();
      LocalDateTime now = LocalDateTime.now();
      vToken.setFechaEnvio(now);
      LocalDateTime expiracion = now.plusSeconds(EXPIRATION_TIME);
      vToken.setFechaExpiracion(expiracion); // Token válido
      vToken.setCorreoId(correo.getCorreoId());

      verificacionCorreoRepository.save(vToken);

      UUID token = vToken.getToken(); // Obtener el token generado para incluirlo en el correo

      sendEmail(correo.getCorreoElectronico(), token.toString());
    } catch (Exception e) {
      logger.error("Error al enviar el correo de verificación de token.", e);
      throw e;
    }
  }

  /**
   * Envío de correo para verificar el correo del usuario después de registrarse
   * 
   * @param to    correo electrónico del usuario
   * @param token token para verificar el correo electrónico
   * @throws ValidacionException Excepción que se puede arrojar en caso de envío
   *                             de correo
   */
  private void sendEmail(String to, String token) throws ValidacionException {
    String url = EMAIL_VERIFICACION_URL + token;
    Map<String, Object> datos = new HashMap<>();
    datos.put("confirmacion_url", url);
    emailService.sendEmailFromTemplate(to, ASUNTO_VERIFICACION, EMAIL_TEMPLATE, datos);
  }

  /**
   * Verifica el correo electrónico del usuario utilizando un token único.
   * 
   * @param token El token de verificación enviado al correo del usuario
   * @return Un mensaje de confirmación o bienvenida
   * @throws NoEncontradoException Si el token no se encuentra en la base de datos
   * @throws ValidacionException   Si ocurre un error de validación durante la
   *                               verificación del token
   */
  @Override
  @Transactional(rollbackFor = Exception.class)
  public String verificarCorreo(String token) throws NoEncontradoException, ValidacionException {
    try {
      UUID uuid = UUID.fromString(token);
      VerificacionCorreo vToken = verificacionCorreoRepository.findByToken(uuid);

      if (vToken != null) {
        validacionVerificacionCorreo(vToken);

        Correo correo = vToken.getCorreo();
        Usuario usuario = usuarioRepository.findById(correo.getUsuarioId()).orElse(null);

        if (usuario == null) {
          logger.error("Usuario inválido.");
          throw new ValidacionException("Usuario inválido.");
        }

        List<Correo> correosActivos = correoRepository.findByUsuarioIdAndActivoTrue(usuario.getUsuarioId());

        if (!correosActivos.isEmpty()) {
          for (Correo correoActivo : correosActivos) {
            correoActivo.setActivo(false);
          }

          correoRepository.saveAll(correosActivos);
        }

        vToken.setFechaConfirmacion(LocalDateTime.now());
        correo.setActivo(true);

        correoRepository.save(correo);
        verificacionCorreoRepository.save(vToken);

        return sendTemplate(usuario.getNombre());
      } else {
        throw new ValidacionException("Token inválido.");
      }
    } catch (Exception e) {
      logger.error("Error al guardar confirmación el correo de verificación del token.", e);
      throw e;
    }
  }

  private void validacionVerificacionCorreo(VerificacionCorreo vToken) throws ValidacionException {
    if (vToken.getFechaExpiracion().isBefore(LocalDateTime.now())) {
      logger.error("Token inválido o expirado.");
      throw new ValidacionException("Token inválido o expirado.");
    }

    if (vToken.getFechaConfirmacion() != null) {
      logger.error("El correo ya fue confirmado.");
      throw new ValidacionException("El correo ya fue confirmado.");
    }
  }

  /***
   * En este método se arma y devuelve el template a mostrar al usuario que
   * significa que su correo se verificó con éxito
   * 
   * @param nombre Nombre del usuario
   * @return Template que se muestra al usuario
   */
  private String sendTemplate(String nombre) {
    Map<String, Object> datos = new HashMap<>();
    datos.put("nombre", nombre);
    String bienvenidatemplate = templateService.renderTemplate(BIENVENIDA_TEMPLATE, datos);

    return bienvenidatemplate;
  }
}