package com.example.demo.services;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Correo;
import com.example.demo.domain.Usuario;
import com.example.demo.domain.VerificacionCorreo;
import com.example.demo.domain.exceptions.ValidacionException;

import com.example.demo.repository.UsuarioRepository;
import com.example.demo.repository.VerificacionCorreoRepository;
import com.example.demo.services.interfaces.EmailService;
import com.example.demo.services.interfaces.VerificacionEmailService;
import com.example.demo.services.interfaces.TemplateService;

@Service
public class VerificacionEmailServiceImpl implements VerificacionEmailService {
  private final VerificacionCorreoRepository verificacionCorreoRepository;

  private final UsuarioRepository usuarioRepository;

  private final EmailService emailService;

  private final TemplateService templateService;

  @Value("${places_app.email.verificacion.url}")
  private String EMAIL_VERIFICACION_URL;

  private static final String EMAIL_TEMPLATE = "email/email-template";

  private static final String ASUNTO_VERIFICACION = "Verificación de correo electrónico";

  private static final String BIENVENIDA_TEMPLATE = "varios/bienvenida";

  private VerificacionEmailServiceImpl(VerificacionCorreoRepository verificacionCorreoRepository, UsuarioRepository usuarioRepository,
      EmailService emailService, TemplateService templateService) {
    this.verificacionCorreoRepository = verificacionCorreoRepository;
    this.usuarioRepository = usuarioRepository;
    this.emailService = emailService;
    this.templateService = templateService;
  }

  @Override
  public void createAndSendToken(Usuario usuario) throws ValidacionException {
    VerificacionCorreo vToken = new VerificacionCorreo();
    
    // Falta obtener el correo

    vToken.setFechaEnvio(LocalDateTime.now());
    vToken.setFechaExpiracion(LocalDateTime.now().plusMinutes(30)); // Token válido
    verificacionCorreoRepository.save(vToken);

    String token= vToken.getToken(); // Obtener el token generado para incluirlo en el correo

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
    VerificacionCorreo vToken = verificacionCorreoRepository.findByToken(token);

    if (vToken == null || vToken.getFechaExpiracion().isBefore(LocalDateTime.now())) {
      return ResponseEntity.badRequest().body("Token inválido o expirado");
    }

    Correo correo = vToken.getCorreo();
    correo.setActivo(true);
    
    // usuarioRepository.save(correo);

    // tokenRepository.delete(vToken); // Borrar token usado

    Map<String, Object> datos = new HashMap<>();
    // datos.put("nombre", correo.getNombre());
    String bienvenidatemplate = templateService.renderTemplate(BIENVENIDA_TEMPLATE, datos);

    return ResponseEntity.ok(bienvenidatemplate);
  }
}