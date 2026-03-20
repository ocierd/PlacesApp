package com.example.demo.services.interfaces;

import java.util.List;

import com.example.demo.domain.Pais;

/**
 * Interfaz que define los métodos para manejar las operaciones relacionadas con
 * los países.
 */
public interface PaisService {

    /**
     * Obtiene la lista de países disponibles en el sistema.
     * @return Lista de países
     */
    List<Pais> obtenerPaises();
}
