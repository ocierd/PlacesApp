package com.places.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.places.api.domain.Menu;

import jakarta.annotation.Nullable;

/**
 * Representa el repositorio de la entidad Menú.
 */
@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer> {

    /**
     * Obtiene la lista de menús disponibles para un usuario específico, organizados en una estructura jerárquica (menú padre e hijos).
     * @param usuarioId ID del usuario para el cual se desea obtener el menú.
     * @param moduloId ID del módulo para filtrar los menús (opcional).
     * @return La lista de menús disponibles para el usuario autenticado, organizados en una estructura jerárquica.
     */
    @Procedure(procedureName = "sp_menu_por_usuario_modulo")
    List<Menu> obtenerMenuUsuarioModulo(@Param("usuarioId") Long usuarioId,
            @Nullable @Param("moduloId") Short moduloId);

}
