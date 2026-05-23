package com.places.api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.places.api.domain.Menu;
import com.places.api.domain.Usuario;
import com.places.api.domain.dto.MenuDto;
import com.places.api.services.interfaces.MenuService;

/**
 * Controlador para manejar las operaciones relacionadas con el menú, como
 * obtener la lista de menús disponibles y los menús específicos para un
 * usuario.
 * Provee endpoints para acceder a la información del menú, incluyendo la
 * estructura jerárquica de menús y submenús.
 * Permite a los clientes obtener la lista de menús disponibles en el sistema,
 * así como los menús específicos para un usuario autenticado.
 */
@RestController
@RequestMapping("/menu")
public class MenuController extends BaseController {

    /**
     * Servicio para manejar las operaciones relacionadas con el menú.
     */
    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    /***
     * Obtiene la lista de menús disponibles en el sistema.
     * 
     * @return La lista de menús disponibles en el sistema.
     */
    @GetMapping
    public List<Menu> getMenus() {
        return menuService.getMenus();
    }

    /**
     * Obtiene la lista de menús disponibles para el usuario autenticado y un módulo
     * específico (si se proporciona), organizados en una estructura jerárquica
     * (menú padre e hijos).
     * 
     * @param moduloId El ID del módulo para filtrar los menús (opcional).
     * @return La lista de menús disponibles para el usuario autenticado.
     */
    @GetMapping("/usuario")
    public List<MenuDto> obtenerMenuUsuarioModulo(@RequestParam(required = false) Short moduloId) {
        Usuario usuario = getCurrentUser();
        return menuService.obtenerMenuUsuarioModulo(usuario.getUsuarioId(), moduloId);
    }

}
