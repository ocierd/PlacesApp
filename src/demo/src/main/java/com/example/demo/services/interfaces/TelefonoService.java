package com.example.demo.services.interfaces;

import java.util.List;

import com.example.demo.domain.Telefono;
import com.example.demo.domain.dto.TelefonoCreacionCurrentUserDto;
// import com.example.demo.domain.exceptions.ValidacionException;

/**
 * Interfaz para el servicio de manejo de teléfonos. Este servicio se encarga de
 * manejar la lógica de negocio relacionada con los teléfonos, como la creación
 * de nuevos teléfonos para los usuarios, la validación de los números de
 * teléfono y la asociación de los teléfonos a los usuarios correspondientes. La
 * implementación de esta interfaz se encargará de interactuar con el
 * repositorio de teléfonos para realizar las operaciones de persistencia
 * necesarias.
 */
public interface TelefonoService {

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
    Telefono agregarTelefono(TelefonoCreacionCurrentUserDto telefono, Long usuarioId);

    /**
     * Obtiene la lista de teléfonos asociados a un usuario por su ID.
     *
     * @param usuarioId El ID del usuario al que están asociados los teléfonos
     * @return Una lista de teléfonos asociados al usuario, o una lista vacía si no
     *         se encuentran teléfonos asociados con ese ID de usuario
     */
    List<Telefono> obtenerTelefonos(Long usuarioId);
}
