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
     * 
     * @return Lista de países
     */
    List<Pais> obtenerPaises();

    /**
     * Busca países por nombre.
     * 
     * @param nombre El nombre o parte del nombre del país a buscar
     * @return La lista de países que coinciden con el criterio de búsqueda. Si no
     *         se encuentra ningún país, devuelve una lista vacía.
     */
    List<Pais> buscarPaises(String nombre);
}
