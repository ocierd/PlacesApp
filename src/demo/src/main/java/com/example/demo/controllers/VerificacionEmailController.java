package com.example.demo.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Usuario;
import com.example.demo.services.interfaces.VerificacionEmailService;

import jakarta.mail.MessagingException;

@RestController
@RequestMapping("/verificacion_email")
public class VerificacionEmailController {
  private final VerificacionEmailService verificacionEmailService;

  public VerificacionEmailController(VerificacionEmailService verificacionEmailService) {
    this.verificacionEmailService = verificacionEmailService;
  }

  @PostMapping
  public void createAndSendToken(@RequestBody Usuario usuario) throws MessagingException {
    verificacionEmailService.createAndSendToken(usuario);
  }

  @GetMapping
  public ResponseEntity<String> confirmRegistration(@RequestParam String token) {
    return verificacionEmailService.confirmarCorreo(token);
  }
}