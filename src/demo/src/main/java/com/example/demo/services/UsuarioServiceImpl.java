package com.example.demo.services;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Correo;
import com.example.demo.domain.Telefono;
import com.example.demo.domain.Usuario;
import com.example.demo.domain.dto.UsuarioRegistroDto;
import com.example.demo.domain.exceptions.ValidacionException;
import com.example.demo.repository.CorreoRepository;
import com.example.demo.repository.TelefonoRepository;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.services.interfaces.UsuarioService;

/**
 * Implementación de la interfaz UsuarioService que proporciona servicios
 * relacionados con la gestión de usuarios, como la creación de usuarios y la
 * codificación de contraseñas.
 */
@Service
public class UsuarioServiceImpl implements UsuarioService {

    private Logger logger = LoggerFactory.getLogger(UsuarioServiceImpl.class);

    private final UsuarioRepository usuarioRepository;

    private final PasswordEncoder passwordEncoder;

    private final CorreoRepository correoRepository;

    private final TelefonoRepository telefonoRepository;

    /**
     * Constructor de UsuarioServiceImpl
     * 
     * @param usuarioRepository Repositorio de usuario
     * @param passwordEncoder   Codificador de contraseñas
     */
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder,
            CorreoRepository correoRepository,
            TelefonoRepository telefonoRepository) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.correoRepository = correoRepository;
        this.telefonoRepository = telefonoRepository;
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
    @Transactional(rollbackFor = Exception.class)
    public Usuario crearUsuario(UsuarioRegistroDto usuario) throws ValidacionException {
        try {

            validarDatosUsuario(usuario);
            
            // Creación de usuario
            Usuario creada = _crearUsuario(usuario);
            logger.info("Usuario creado con id: {}", creada.getUsuarioId());

            Telefono tel = crearTelefono(usuario, creada.getUsuarioId());
            logger.info("Telefono creado con TelefonoId: {}", tel.getTelefonoId());

            Correo correo = creaCorreo(usuario, creada.getUsuarioId());
            logger.info("Correo creado con CorreoId: {}", correo.getCorreoId());

            return creada;
        } catch (Exception e) {
            logger.error("Se produjo un error al intentar crear el usuario. Se desharán los cambios", e);
            throw e;
        }
    }

    private Usuario _crearUsuario(UsuarioRegistroDto usuario) {
        String encoded = passwordEncoder.encode(usuario.getPassword());
        usuario.setPassword(encoded);
        Usuario aCrear = new Usuario();
        aCrear.setUsername(usuario.getUsername());
        aCrear.setPassword(encoded);
        aCrear.setNombre(usuario.getNombre());
        aCrear.setApellidoPaterno(usuario.getApellidoPaterno());
        aCrear.setApellidoMaterno(usuario.getApellidoMaterno());
        aCrear.setFechaNacimiento(usuario.getFechaNacimiento());
        usuarioRepository.save(aCrear);
        return aCrear;
    }

    private Telefono crearTelefono(UsuarioRegistroDto usuario, Long usuarioId) {
        // Creación de teléfono
        Telefono tel = new Telefono();
        tel.setNumero(usuario.getTelefono());
        tel.setPaisId(usuario.getPaisId());
        tel.setUsuarioId(usuarioId);
        telefonoRepository.save(tel);
        return tel;
    }

    private Correo creaCorreo(UsuarioRegistroDto usuario, Long usuarioId) {
        Correo correo = new Correo();
        correo.setCorreoElectronico(usuario.getEmail());
        correo.setUsuarioId(usuarioId);
        correoRepository.save(correo);
        return correo;
    }

    private void validarDatosUsuario(UsuarioRegistroDto usuario) throws ValidacionException {
        Optional<Correo> correOptional = correoRepository
                .findByCorreoElectronicoAndActivoTrue(usuario.getEmail());
        if (correOptional.isPresent()) {
            throw new ValidacionException("Este correo ya está siendo usado");
        }
        Optional<Telefono> teleOptional = telefonoRepository.findByNumeroAndActivoTrue(usuario.getTelefono());
        if (teleOptional.isPresent()) {
            throw new ValidacionException("El teléfono ya está siendo usado");
        }

        Usuario existente = usuarioRepository.findByUsername(usuario.getUsername());
        if (existente instanceof Usuario) {
            throw new ValidacionException("El nombre de usuario ya existe");
        }
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
