package com.example.demo.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.example.demo.domain.Usuario;

/**
 * Clase base para los controladores que proporciona métodos comunes
 * relacionados con la autenticación y el usuario actual.
 */
public class BaseController {

    /**
     * Obtiene el usuario actualmente autenticado a partir del contexto de
     * seguridad.
     * 
     * @return el usuario actualmente autenticado, o null si no hay ninguno
     *         autenticado o si el principal no es una instancia de Usuario.
     */
    protected Usuario getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return null;
        }
        Object principal = authentication.getPrincipal();
        if (principal instanceof Usuario) {
            return (Usuario) principal;
        }
        return null;
    }
}
