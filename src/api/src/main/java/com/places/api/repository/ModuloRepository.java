package com.places.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import com.places.api.domain.Modulo;

/**
 * Representa el repositorio de la entidad Módulo, proporcionando métodos para
 * acceder a los datos relacionados con los módulos del sistema.
 */
@Repository
public interface ModuloRepository extends JpaRepository<Modulo, Short> {

    /**
     * Obtiene la lista de módulos disponibles para un usuario específico.
     * 
     * @param usuarioId ID del usuario para el cual se desea obtener los módulos.
     * @return La lista de módulos disponibles para el usuario autenticado.
     */
    @Procedure(procedureName = "sp_modulos_por_usuario")
    public List<Modulo> obtenerModulosUsuario(Long usuarioId);

}
