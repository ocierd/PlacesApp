package com.example.demo.services;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Usuario;
import com.example.demo.domain.VerificationToken;
import com.example.demo.domain.exceptions.ValidacionException;
import com.example.demo.repository.TokenRepository;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.services.interfaces.EmailService;
import com.example.demo.services.interfaces.VerificacionEmailService;
import com.example.demo.services.interfaces.TemplateService;

@Service
public class VerificacionEmailServiceImpl implements VerificacionEmailService {
  private final TokenRepository tokenRepository;

  private final UsuarioRepository usuarioRepository;

  private final EmailService emailService;

  private final TemplateService templateService;

  @Value("${places_app.email.verificacion.url}")
  private String EMAIL_VERIFICACION_URL;

  private static final String EMAIL_TEMPLATE = "email/email-template";

  private static final String ASUNTO_VERIFICACION = "Verificación de correo electrónico";

  private static final String BIENVENIDA_TEMPLATE = "varios/bienvenida";

  private VerificacionEmailServiceImpl(TokenRepository tokenRepository, UsuarioRepository usuarioRepository,
      EmailService emailService, TemplateService templateService) {
    this.tokenRepository = tokenRepository;
    this.usuarioRepository = usuarioRepository;
    this.emailService = emailService;
    this.templateService = templateService;
  }

  @Override
  public void createAndSendToken(Usuario usuario) throws ValidacionException {
    String token = UUID.randomUUID().toString();
    VerificationToken vToken = new VerificationToken(token, usuario);
    tokenRepository.save(vToken);

    // String email=usuario.getEmail();
    String email = "<correo_electronico_del_usuario>"; // Reemplazar con el correo electrónico del usuario
    sendEmail(email, token);

  }

  private void sendEmail(String to, String token) throws ValidacionException {
    String url = EMAIL_VERIFICACION_URL + token;
    Map<String, Object> datos = new HashMap<>();
    datos.put("confirmacion_url", url);
    emailService.sendEmailFromTemplate(to, ASUNTO_VERIFICACION, EMAIL_TEMPLATE, datos);

  }

  @Override
  public ResponseEntity<String> confirmarCorreo(String token) {
    VerificationToken vToken = tokenRepository.findByToken(token);

    if (vToken == null || vToken.getExpiryDate().isBefore(LocalDateTime.now())) {
      return ResponseEntity.badRequest().body("Token inválido o expirado");
    }

    Usuario usuario = vToken.getUsuario();
    // usuario.setEmailVerificado(true); // Adaptar al nuevo método para activar el email verificado
    usuarioRepository.save(usuario);
    // tokenRepository.delete(vToken); // Borrar token usado

    Map<String, Object> datos = new HashMap<>();
    datos.put("nombre", usuario.getNombre());
    String bienvenidatemplate = templateService.renderTemplate(BIENVENIDA_TEMPLATE, datos);

    return ResponseEntity.ok(bienvenidatemplate);
  }
}