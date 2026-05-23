package com.places.api.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.places.api.domain.Modulo;
import com.places.api.domain.dto.ModuloDto;
import com.places.api.repository.ModuloRepository;
import com.places.api.services.interfaces.ModuloService;

/**
 * Implementación de la interfaz ModuloService para manejar las operaciones
 * relacionadas con los módulos del sistema.
 */
@Service
public class ModuloServiceImpl implements ModuloService {

    /**
     * Repositorio para manejar las operaciones de persistencia relacionadas con los módulos del sistema.
     */
    private final ModuloRepository moduloRepository;

    
    public ModuloServiceImpl(ModuloRepository moduloRepository) {
        this.moduloRepository = moduloRepository;
    }

    /**
     * Obtiene la lista de módulos disponibles en el sistema.
     * 
     * @return Lista de módulos
     */
    @Override
    public List<Modulo> obtenerModulos() {
        return moduloRepository.findAll();
    }

    /**
     * Obtiene la lista de módulos disponibles para un usuario específico, junto con
     * los menús asociados a cada módulo.
     * 
     * @param usuarioId El ID del usuario para el cual se desea obtener
     * @return La lista de módulos disponibles para el usuario autenticado, junto
     *         con los menús asociados a cada módulo.
     */
    @Transactional(readOnly = true)
    @Override
    public List<ModuloDto> obtenerModulosUsuario(Long usuarioId) {
        List<Modulo> modulos = moduloRepository.obtenerModulosUsuario(usuarioId);
        List<ModuloDto> modulosConMenus = new ArrayList<>();
        for (int i = 0; i < modulos.size(); i++) {
            Modulo modulo = modulos.get(i);
            ModuloDto moduloDto = new ModuloDto();
            moduloDto.setModuloId(modulo.getModuloId());
            moduloDto.setNombre(modulo.getNombre());
            moduloDto.setDescripcion(modulo.getDescripcion());
            moduloDto.setRuta(modulo.getRuta());
            modulosConMenus.add(moduloDto);
        }

        return modulosConMenus;
    }

}
