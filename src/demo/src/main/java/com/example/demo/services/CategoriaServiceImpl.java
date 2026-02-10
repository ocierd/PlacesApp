package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.domain.Categoria;
import com.example.demo.repository.CategoriaRepository;
import com.example.demo.services.interfaces.CategoriaService;

/**
 * CategoriaServiceImpl es una clase que implementa la interfaz CategoriaService
 * y proporciona la lógica de negocio para manejar las categorías en la
 * aplicación.
 */
@Service
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaServiceImpl(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    /**
     * Obtiene una lista de todas las categorías disponibles.
     * 
     * @return Lista de categorías
     */
    @Override
    public List<Categoria> getAllCategorias() {
        return categoriaRepository.findAll();
    }

    /**
     * Obtiene una categoría por su ID.
     * 
     * @param id El ID de la categoría a obtener
     * @return La categoría correspondiente al ID proporcionado, o null si no se
     *         encuentra
     */
    @Override
    public Categoria getCategoriaById(short id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        return categoria.orElse(null);
    }

    /**
     * Obtiene una lista de categorías que coinciden con el criterio de búsqueda
     * proporcionado.
     * 
     * @param criteria El criterio de búsqueda para filtrar las categorías por
     *                 nombre o descripción
     * @return Lista de categorías que coinciden con el criterio de búsqueda
     */
    @Override
    public List<Categoria> getByCriteria(String criteria) {
        return categoriaRepository.findByCriteria(criteria);
    }

}
