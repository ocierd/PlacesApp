package com.example.demo.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Correo;
import com.example.demo.domain.Usuario;
import com.example.demo.domain.exceptions.NoEncontradoException;
import com.example.demo.domain.exceptions.ValidacionException;
import com.example.demo.services.interfaces.CorreoService;

@RestController
@RequestMapping("/correos")
public class CorreoController extends BaseController {
  private final CorreoService correoService;

  public CorreoController(CorreoService correoService) {
    this.correoService = correoService;
  }

  @PostMapping
  public Correo crearCorreo(@RequestBody Correo correo) throws NoEncontradoException, ValidacionException {
    Usuario usuario = this.getCurrentUser();
    return correoService.crearCorreo(usuario.getUsuarioId(), correo);
  }
}