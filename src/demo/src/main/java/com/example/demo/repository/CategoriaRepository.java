package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Categoria;

/**
 * CategoriaRepository es una interfaz que extiende JpaRepository para proporcionar métodos de acceso a datos para la entidad Categoria.
 */
@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Short> {    
    


    /**
     * Obtiene una lista de categorías que coinciden con el criterio de búsqueda proporcionado.
     * @param criteria El criterio de búsqueda para filtrar las categorías por nombre o descripción
     * @return Lista de categorías que coinciden con el criterio de búsqueda
     */
    @Query("SELECT c FROM Categoria c WHERE c.nombre LIKE %:criteria% OR c.descripcion LIKE %:criteria%")
    List<Categoria> findByCriteria(@Param("criteria") String criteria);

}
