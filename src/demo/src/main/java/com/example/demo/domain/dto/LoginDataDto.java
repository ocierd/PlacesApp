package com.example.demo.domain.dto;

/**
 * DTO para representar los datos de inicio de sesión.
 * Contiene el nombre de usuario y la contraseña proporcionados por el usuario
 * al intentar iniciar sesión.
 */
public record LoginDataDto(String username, String password) { }
