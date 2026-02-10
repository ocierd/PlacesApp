package com.example.demo.services.interfaces;

import java.util.List;

import com.example.demo.domain.Categoria;

/**
 * CategoriaService es una interfaz que define los métodos para manejar las categorías en la aplicación.
 */
public interface CategoriaService {



    /**
     * Obtiene una lista de todas las categorías disponibles.
     * @return Lista de categorías
     */    
    List<Categoria> getAllCategorias();

    /**
     * Obtiene una categoría por su ID.
     * @param id El ID de la categoría a obtener
     * @return La categoría correspondiente al ID proporcionado, o null si no se encuentra
     */
    Categoria getCategoriaById(short id);

    /**
     * Obtiene una lista de categorías que coinciden con el criterio de búsqueda proporcionado.
     * @param criteria El criterio de búsqueda para filtrar las categorías por nombre o descripción
     * @return Lista de categorías que coinciden con el criterio de búsqueda
     */
    List<Categoria> getByCriteria(String criteria);

}
