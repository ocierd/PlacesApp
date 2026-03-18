package com.example.demo.services.interfaces;

import com.example.demo.domain.Usuario;
import com.example.demo.domain.exceptions.NoEncontradoException;
import com.example.demo.domain.exceptions.ValidacionException;

public interface VerificacionEmailService {
  void createAndSendToken(Usuario usuario) throws ValidacionException;

  String confirmarCorreo(String token) throws NoEncontradoException, ValidacionException;
}