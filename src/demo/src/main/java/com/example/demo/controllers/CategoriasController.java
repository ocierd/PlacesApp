package com.example.demo.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Categoria;
import com.example.demo.services.interfaces.CategoriaService;

/**
 * CategoriasController es una clase que maneja las solicitudes HTTP relacionadas con las categorías en la aplicación.
 */
@RestController
@RequestMapping("/categorias")
public class CategoriasController {

    /**
     * CategoriaService es una interfaz que define los métodos para manejar las categorías en la aplicación.
     */
    private final CategoriaService categoriaService;

    /**
     * Constructor de CategoriasController
     * @param categoriaService El servicio de categorías que se inyectará en el controlador
     */
    public CategoriasController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    /**
     * Obtiene una lista de todas las categorías disponibles.
     * @return Lista de categorías
     */
    @GetMapping
    public List<Categoria> getAllCategorias() {
        return categoriaService.getAllCategorias();
    }


    /**
     * Obtiene una categoría por su ID.
     * @param id El ID de la categoría a obtener
     * @return La categoría correspondiente al ID proporcionado, o null si no se encuentra
     */
    @GetMapping("/{id}")
    public Categoria getCategoriaById(@PathVariable("id") short id) {
        return categoriaService.getCategoriaById(id);
    }


    /**
     * Obtiene una lista de categorías que coinciden con el criterio de búsqueda proporcionado.
     * @param criteria El criterio de búsqueda para filtrar las categorías por nombre o descripción
     * @return Lista de categorías que coinciden con el criterio de búsqueda
     */
    @GetMapping("/search-by-criteria")
    public List<Categoria> getByCriteria(@RequestParam("criteria") String criteria){
        return categoriaService.getByCriteria(criteria);
    }


}
