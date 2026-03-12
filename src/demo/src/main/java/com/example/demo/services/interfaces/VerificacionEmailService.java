package com.example.demo.services.interfaces;

import org.springframework.http.ResponseEntity;

import com.example.demo.domain.Usuario;
import com.example.demo.domain.exceptions.ValidacionException;

public interface VerificacionEmailService {
  void createAndSendToken(Usuario usuario) throws ValidacionException;

  ResponseEntity<String> confirmarCorreo(String token);
}