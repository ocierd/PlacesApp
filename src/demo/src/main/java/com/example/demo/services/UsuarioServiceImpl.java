package com.example.demo.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Usuario;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.services.interfaces.UsuarioService;


/**
 * Implementación de la interfaz UsuarioService que proporciona servicios
 * relacionados con la gestión de usuarios, como la creación de usuarios y la
 * codificación de contraseñas.
 */
@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    private final PasswordEncoder passwordEncoder;

    /**
     * Constructor de UsuarioServiceImpl
     * 
     * @param usuarioRepository Repositorio de usuario
     * @param passwordEncoder   Codificador de contraseñas
     */
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Crea un nuevo usuario en la aplicación. Este método toma un objeto Usuario,
     * codifica su contraseña y lo guarda en el repositorio de usuarios. Devuelve el
     * usuario creado con su ID asignado.
     * 
     * @param usuario El Usuario que contiene la información del usuario a crear,
     *                incluyendo su nombre de usuario y contraseña sin codificar.
     * @return El Usuario creado, con su contraseña codificada y su ID asignado por
     *         la base de datos. Si el proceso de creación falla, devuelve null.
     */
    @Override
    public Usuario crearUsuario(Usuario usuario) {
        String encoded = passwordEncoder.encode(usuario.getPassword());
        usuario.setPassword(encoded);
        Usuario creada = usuarioRepository.save(usuario);
        Long usuarioId = creada.getUsuarioid();
        if (usuarioId != null) {
        }
        return creada;
    }

    /**
     * Codifica una cadena utilizando el codificador de contraseñas configurado en
     * la aplicación. Este método se puede utilizar para codificar contraseñas u
     * otras cadenas que requieran codificación antes de ser almacenadas o
     * comparadas.
     * 
     * @param str La cadena que se desea codificar.
     * @return La cadena codificada resultante después de aplicar el codificador de
     *         contraseñas. La salida será diferente cada vez que se codifique la
     *         misma cadena debido a la naturaleza del proceso de codificación.
     */
    @Override
    public String encodeString(String str) {
        return passwordEncoder.encode(str);
    }
}
