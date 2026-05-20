package com.example.demo.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Modulo;
import com.example.demo.domain.Usuario;
import com.example.demo.domain.dto.ModuloDto;
import com.example.demo.services.interfaces.ModuloService;

/**
 * Controlador para manejar las operaciones relacionadas con los módulos del
 * sistema.
 */
@RestController
@RequestMapping("/modulos")
public class ModuloController extends BaseController {

    /**
     * Servicio para manejar las operaciones relacionadas con los módulos del
     * sistema.
     */
    private final ModuloService moduloService;

    public ModuloController(ModuloService moduloService) {
        this.moduloService = moduloService;
    }

    /**
     * Obtiene la lista de módulos disponibles en el sistema.
     * 
     * @return Lista de módulos
     */
    @GetMapping
    public List<Modulo> obtenerModulos() {
        return moduloService.obtenerModulos();
    }

    /**
     * Obtiene la lista de módulos disponibles para el usuario autenticado.
     * 
     * @return Lista de módulos específicos para el usuario
     */
    @GetMapping("/usuario")
    public List<ModuloDto> obtenerModulosUsuario() {
        Usuario usuario = getCurrentUser();
        return moduloService.obtenerModulosUsuario(usuario.getUsuarioId());
    }

}
