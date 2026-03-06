package com.example.demo.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Usuario;
import com.example.demo.repository.UsuarioRepository;

/**
 * Implementación de UserDetailsService para cargar los detalles del usuario
 * desde la base de datos utilizando el repositorio de usuarios.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    /**
     * Repositorio de usuarios utilizado para acceder a los datos de los usuarios en la base de datos.
     */
    private final UsuarioRepository usuarioRepository;

    public UserDetailsServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    /**
     * Carga los detalles del usuario por su nombre de usuario. Si el usuario no se encuentra, se lanza una excepción UsernameNotFoundException.
     * @param username El nombre de usuario del usuario a cargar.
     * @return Un objeto Usuario que contiene los detalles del usuario cargado.
     * @throws UsernameNotFoundException Si el usuario no se encuentra en la base de datos
     */
    @Override
    public Usuario loadUserByUsername(String username) {
        Usuario usuario = usuarioRepository.findByUsername(username);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
        return usuario;
    }
}
