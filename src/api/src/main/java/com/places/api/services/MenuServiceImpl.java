package com.places.api.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.places.api.domain.Menu;
import com.places.api.domain.dto.MenuDto;
import com.places.api.repository.MenuRepository;
import com.places.api.services.interfaces.MenuService;

/**
 * Implementación de la interfaz MenuService para manejar las operaciones
 * relacionadas con el menú.
 */
@Service
public class MenuServiceImpl implements MenuService {

    /**
     * Repositorio para manejar las operaciones de persistencia relacionadas con el
     * menú.
     */
    private final MenuRepository menuRepository;

    public MenuServiceImpl(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    /**
     * Obtiene la lista de todos los menús disponibles en el sistema.
     */
    @Override
    public List<Menu> getMenus() {
        return menuRepository.findAll();
    }

    /**
     * Obtiene la lista de menús disponibles para un usuario específico, organizados
     * en una estructura jerárquica (menú padre e hijos).
     */
    @Transactional(readOnly = true)
    @Override
    public List<MenuDto> obtenerMenuUsuarioModulo(Long usuarioId, Short moduloId) {
        List<Menu> menuUsuario = menuRepository.obtenerMenuUsuarioModulo(usuarioId, moduloId);
        return obtenerMenuUsuario(menuUsuario, null);
    }

    /**
     * Construye la estructura jerárquica de menús para un usuario específico.
     *
     * @param menuUsuario Lista de menús disponibles para el usuario.
     * @param padreMenuId ID del menú padre para filtrar los menús hijos.
     * @return Lista de menús en estructura jerárquica.
     */
    private List<MenuDto> obtenerMenuUsuario(List<Menu> menuUsuario, Integer padreMenuId) {
        List<MenuDto> menus = new ArrayList<>();
        for (Menu menu : menuUsuario) {

            if ((padreMenuId == null && menu.getPadreMenuId() == null) ||
                    (padreMenuId != null && padreMenuId.equals(menu.getPadreMenuId()))) {
                MenuDto menuDto = new MenuDto();
                menuDto.setMenuId(menu.getMenuId());
                menuDto.setNombre(menu.getNombre());
                menuDto.setRuta(menu.getRuta());
                menuDto.setIcono(menu.getIcono());
                menuDto.setPadreMenuId(menu.getPadreMenuId());
                menuDto.setModuloId(menu.getModuloId());
                List<MenuDto> hijos = obtenerMenuUsuario(menuUsuario, menu.getMenuId());
                menuDto.setHijos(hijos);
                menus.add(menuDto);
            }
        }
        return menus;
    }

}
