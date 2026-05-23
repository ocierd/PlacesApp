package com.places.api.services.interfaces;

import java.util.List;

import com.places.api.domain.Modulo;
import com.places.api.domain.dto.ModuloDto;

/**
 * Interfaz que define los métodos para manejar las operaciones relacionadas con
 * los módulos del sistema.
 */
public interface ModuloService {
    
    /**
     * Obtiene la lista de módulos disponibles en el sistema.
     * @return Lista de módulos
     */
    List<Modulo> obtenerModulos();


    List<ModuloDto> obtenerModulosUsuario(Long usuarioId);
}
