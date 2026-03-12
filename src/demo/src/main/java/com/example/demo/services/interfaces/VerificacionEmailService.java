package com.example.demo.services.interfaces;

import org.springframework.http.ResponseEntity;

import com.example.demo.domain.Usuario;

import jakarta.mail.MessagingException;

public interface VerificacionEmailService {
  void createAndSendToken(Usuario usuario) throws MessagingException;

  void sendEmail(String to, String url) throws MessagingException;

  ResponseEntity<String> confirmarCorreo(String token);
}