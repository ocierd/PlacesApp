package com.example.demo.services;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Telefono;
import com.example.demo.domain.dto.TelefonoCreacionCurrentUserDto;
import com.example.demo.repository.TelefonoRepository;
import com.example.demo.services.interfaces.TelefonoService;

/**
 * Implementación de la lógica de negocio para la gestión de teléfonos.
 * Se encarga de manejar las operaciones relacionadas con los teléfonos de los
 * usuarios,
 * incluyendo la validación y el almacenamiento de los datos.
 */
@Service
public class TelefonoServiceImpl implements TelefonoService {

    /** Logger para la clase TelefonoServiceImpl */
    private static final Logger logger = LoggerFactory.getLogger(TelefonoServiceImpl.class);

    /**
     * Repositorio para manejar las operaciones de persistencia relacionadas con los
     * teléfonos.
     */
    private final TelefonoRepository telefonoRepository;

    /**
     * Constructor para inyectar las dependencias del repositorio de teléfonos.
     * 
     * @param telefonoRepository Repositorio para manejar las operaciones de
     *                           persistencia relacionadas con los teléfonos.
     */
    public TelefonoServiceImpl(TelefonoRepository telefonoRepository) {
        this.telefonoRepository = telefonoRepository;
    }

    /**
     * Agrega un nuevo teléfono para el usuario actual. Este método se encarga de
     * validar el número de teléfono, crear una nueva entidad de teléfono y
     * asociarla al usuario actual.
     *
     * @param telefono  El DTO que contiene la información del teléfono que se desea
     *                  agregar
     * @param usuarioId El ID del usuario al que se asociará el teléfono
     * @return La entidad de teléfono creada y asociada al usuario
     */
    @Override
    public Telefono agregarTelefono(TelefonoCreacionCurrentUserDto telefono, Long usuarioId) {

        Optional<Telefono> telefonoExistente = telefonoRepository
                .findByNumeroAndUsuarioIdAndPaisId(telefono.numeroTelefono(), usuarioId, telefono.paisId());
        

        if (telefonoExistente.isPresent()) {
            logger.info("El número de teléfono {} ya está registrado para el usuario {}",
                    telefono.numeroTelefono(), usuarioId);
            return telefonoExistente.get();
        }

        Telefono nuevoTelefono = new Telefono();
        nuevoTelefono.setNumero(telefono.numeroTelefono());
        nuevoTelefono.setUsuarioId(usuarioId);
        nuevoTelefono.setPaisId(telefono.paisId());
        nuevoTelefono = telefonoRepository.save(nuevoTelefono);
        logger.info("Nuevo número de teléfono {} agregado para el usuario {}", nuevoTelefono.getNumero(), usuarioId);
        return nuevoTelefono;
    }

    /**
     * Obtiene la lista de teléfonos asociados a un usuario por su ID.
     *
     * @param usuarioId El ID del usuario al que están asociados los teléfonos
     * @return Una lista de teléfonos asociados al usuario, o una lista vacía si no
     *         se encuentran teléfonos asociados con ese ID de usuario
     */
    @Override
    public java.util.List<Telefono> obtenerTelefonos(Long usuarioId) {
        return telefonoRepository.findByUsuarioId(usuarioId);
    }

}
