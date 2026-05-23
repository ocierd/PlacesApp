package com.places.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import com.places.api.domain.Pais;

/**
 * Representa el repositorio de la entidad País.
 */
@Repository
public interface PaisRepository extends JpaRepository<Pais, Short> {

    /**
     * Busca países por nombre utilizando el procedimiento almacenado
     * sp_pais_buscar.
     * 
     * @param nombre El nombre o parte del nombre del país a buscar
     * @return La lista de países que coinciden con el criterio de búsqueda. Si no
     *         se encuentra ningún país, devuelve una lista vacía.
     */
    @Procedure(procedureName = "sp_pais_buscar")
    List<Pais> buscarPaises(String nombre);

}
