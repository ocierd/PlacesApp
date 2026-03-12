package com.example.demo.services;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
//import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;

import com.example.demo.domain.Usuario;
import com.example.demo.domain.VerificationToken;
import com.example.demo.repository.TokenRepository;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.services.interfaces.VerificacionEmailService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import org.thymeleaf.context.Context;

@Service
public class VerificacionEmailServiceImpl implements VerificacionEmailService {
  private final TokenRepository tokenRepository;

  private final UsuarioRepository usuarioRepository;

  private TemplateEngine templateEngine;

  private JavaMailSender mailSender;

  @Value("${spring.mail.username}")
  private String mailUsername;

  private VerificacionEmailServiceImpl(TokenRepository tokenRepository, UsuarioRepository usuarioRepository,
      TemplateEngine templateEngine, JavaMailSender mailSender) {
    this.tokenRepository = tokenRepository;
    this.usuarioRepository = usuarioRepository;
    this.mailSender = mailSender;
    this.templateEngine = templateEngine;
  }

  @Override
  public void createAndSendToken(Usuario usuario) throws MessagingException {
    String token = UUID.randomUUID().toString();
    VerificationToken vToken = new VerificationToken(token, usuario);
    tokenRepository.save(vToken);

    String url = "http://localhost:8080/api/verificacion_email?token=" + token;
    sendEmail(usuario.getEmail(), url);
  }

  @Override
  public void sendEmail(String to, String url) throws MessagingException {
    // SimpleMailMessage message = new SimpleMailMessage();
    // message.setFrom(mailUsername);
    // message.setTo(to);
    // message.setSubject("Verificación de Cuenta");
    // message.setText("Haz clic aquí para confirmar: " + url);
    // mailSender.send(message);

    MimeMessage message = mailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

    String cuerpo = "Haz clic aquí para confirmar: " + url;

    // Datos para la plantilla
    Context context = new Context();
    context.setVariable("cuerpo", cuerpo);

    // Renderizar HTML
    String htmlContent = templateEngine.process("email-template", context);

    helper.setFrom(mailUsername);
    helper.setTo(to);
    helper.setSubject("Verificación de Cuenta");
    helper.setText(htmlContent, true); // <--- 'true' indica formato HTML
    
    mailSender.send(message);
  }

  @Override
  public ResponseEntity<String> confirmarCorreo(String token) {
    VerificationToken vToken = tokenRepository.findByToken(token);

    if (vToken == null || vToken.getExpiryDate().isBefore(LocalDateTime.now())) {
      return ResponseEntity.badRequest().body("Token inválido o expirado");
    }

    Usuario usuario = vToken.getUsuario();
    usuario.setEmailVerificado(true); // Activar usuario
    usuarioRepository.save(usuario);
    tokenRepository.delete(vToken); // Borrar token usado

    return ResponseEntity.ok("Cuenta verificada exitosamente");
  }
}