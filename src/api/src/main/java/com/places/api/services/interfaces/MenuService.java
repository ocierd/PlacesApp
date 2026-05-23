package com.places.api.services.interfaces;

import java.util.List;

import com.places.api.domain.Menu;
import com.places.api.domain.dto.MenuDto;

/**
 * Interfaz que define los métodos para manejar las operaciones relacionadas con
 * el menú.
 */
public interface MenuService {

    /**
     * Obtiene la lista de todos los menús disponibles en el sistema.
     * 
     * @return La lista de menús disponibles en el sistema.
     */
    List<Menu> getMenus();

    /**
     * Obtiene la lista de menús disponibles para un usuario específico, organizados
     * en una estructura jerárquica (menú padre e hijos).
     * 
     * @param usuarioId El ID del usuario para el cual se desea obtener el menú.
     * @return La lista de menús disponibles para el usuario autenticado,
     *         organizados en una estructura jerárquica.
     */
    List<MenuDto> obtenerMenuUsuarioModulo(Long usuarioId, Short moduloId);

}
