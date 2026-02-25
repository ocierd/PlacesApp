package com.example.demo.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Usuario;
import com.example.demo.services.interfaces.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService)
    {
        this.usuarioService=usuarioService;
    }
    @PostMapping
    public Usuario creUsuario (@RequestBody Usuario usuario){
        return usuarioService.crearUsuario(usuario);
    }
}

