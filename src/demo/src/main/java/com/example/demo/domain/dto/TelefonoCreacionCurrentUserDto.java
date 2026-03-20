package com.example.demo.domain.dto;

/**
 * DTO para la creación de un nuevo número de teléfono para el usuario actual.
 * Este DTO se utiliza para recibir la información necesaria para agregar un
 * nuevo teléfono, incluyendo el número de teléfono y el ID del país al que
 * pertenece el número. La implementación de este DTO se encarga de validar los
 * datos recibidos y proporcionar una estructura clara para la creación de
 * nuevos teléfonos asociados al usuario actual.
 */
public record TelefonoCreacionCurrentUserDto(String numeroTelefono, Short paisId) {

}