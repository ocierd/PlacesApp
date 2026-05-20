package com.example.demo.services.interfaces;

import java.util.List;

import com.example.demo.domain.Modulo;
import com.example.demo.domain.dto.ModuloDto;

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
