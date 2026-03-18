package com.example.demo.services.interfaces;

import com.example.demo.domain.Correo;
import com.example.demo.domain.exceptions.NoEncontradoException;
import com.example.demo.domain.exceptions.ValidacionException;

public interface CorreoService {
  Correo crearCorreo(Long usuarioId, Correo correo) throws NoEncontradoException, ValidacionException;
} 