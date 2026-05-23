package com.places.api.services.interfaces;

import com.places.api.domain.Correo;
import com.places.api.domain.exceptions.NoEncontradoException;
import com.places.api.domain.exceptions.ValidacionException;

public interface CorreoService {
  Correo crearCorreo(Long usuarioId, Correo correo) throws NoEncontradoException, ValidacionException;
} 