package com.example.demo.services;

import org.springframework.stereotype.Service;

import com.example.demo.domain.Usuario;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.services.interfaces.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
/**
 * Constructor de usuarioService    
 * @param usuarioRepository Repositorio de usuario
 */
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Usuario crearUsuario(Usuario usuario) {
        Usuario creada = usuarioRepository.save(usuario);
        Long usuarioId = creada.getUsuarioid();
        if (usuarioId != null) {
            return usuarioRepository.findById(usuarioId)
                    .orElse(null);
        }
        return creada;
    }
}
