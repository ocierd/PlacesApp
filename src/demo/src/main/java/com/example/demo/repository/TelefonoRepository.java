package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Telefono;

/**
 * Repositorio para la entidad Telefono. Este repositorio proporciona métodos
 * para realizar operaciones CRUD y consultas específicas relacionadas con los
 * teléfonos de los usuarios. Incluye métodos para buscar teléfonos por número,
 * por ID y por ID de usuario, así como para contar la cantidad de teléfonos
 * asociados a un usuario.
 */
@Repository
public interface TelefonoRepository extends JpaRepository<Telefono, Long> {

    /**
     * Busca un teléfono activo por su número.
     * 
     * @param numero El número de teléfono a buscar
     * @return Una Optional que contiene el teléfono encontrado si existe y está
     *         activo, o una Optional vacía si no se encuentra ningún teléfono
     *         activo con ese número
     */
    Optional<Telefono> findByNumeroAndActivoTrue(String numero);

    /**
     * Busca todos los teléfonos asociados a un usuario por su ID.
     * @param usuarioId El ID del usuario al que están asociados los teléfonos
     * @return Una lista de teléfonos asociados al usuario, o una lista vacía si no se encuentran teléfonos asociados con ese ID de usuario
     */
    List<Telefono> findByUsuarioId(Long usuarioId);


    /**
     * Busca un teléfono por su número y el ID del usuario.
     * @param numero El número de teléfono a buscar
     * @param usuarioId El ID del usuario al que está asociado el teléfono
     * @param paisId El ID del país al que está asociado el teléfono
     * @return Una Optional que contiene el teléfono encontrado si existe, o una Optional vacía si no se encuentra ningún teléfono con ese número y usuario
     */
    Optional<Telefono> findByNumeroAndUsuarioIdAndPaisId(String numero, Long usuarioId, Short paisId);

}


